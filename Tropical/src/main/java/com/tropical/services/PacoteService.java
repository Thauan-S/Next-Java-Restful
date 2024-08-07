package com.tropical.services;


import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.tropical.data.dto.PacoteDeViagemDto;
import com.tropical.exceptions.ForbiddenAccesException;
import com.tropical.exceptions.ResourceNotFoundException;
import com.tropical.model.PacoteDeViagem;
import com.tropical.model.Role;
import com.tropical.repository.EmpresaRepository;
import com.tropical.repository.PacoteRepository;
import com.tropical.repository.UserRepository;


@Service
public class PacoteService {
	private final PacoteRepository pacoteRepository;
	private final UserRepository userRepository;
	private final EmpresaRepository empresaRepository;
	
	public PacoteService(PacoteRepository pacoteRepository, UserRepository userRepository, EmpresaRepository empresaRepository) {
		this.pacoteRepository = pacoteRepository;
		this.userRepository = userRepository;
		this.empresaRepository = empresaRepository;
	}


	public Page<PacoteDeViagemDto> findAll(
			@RequestParam(name = "page",defaultValue = "0" )int page,
			@RequestParam(name = "size",defaultValue = "12" )int size,
			@RequestParam(name = "direction",defaultValue = "asc" )String direction){
		
		var pacotes =pacoteRepository.findAll(PageRequest.of(page, size, Direction.valueOf(direction),"destino"))
		.map(pacote-> new PacoteDeViagem(
				pacote.getId(),
				pacote.getDestino(),
				pacote.getDescricao(),
				pacote.getCategoria(),
				pacote.getDuracaoEmDias(),
				pacote.getImagem(),
				pacote.getPreco(),
				pacote.getEmpresa()));
		
		return PacoteDeViagemDto.listaPacotes(pacotes);
	}

	
	public PacoteDeViagemDto create(@RequestBody PacoteDeViagemDto pacoteDTO,JwtAuthenticationToken token) {
		var user= userRepository.findById(UUID.fromString(token.getName())).orElseThrow(()-> new ResourceNotFoundException("O usuário de id "+token.getName()+" não se encontra na base de dados"));
		var empresa=empresaRepository.findById(pacoteDTO.getEmpresa().getEmpresaId()).orElseThrow(()-> new ResourceNotFoundException("A empresa de id "+pacoteDTO.getEmpresa().getEmpresaId()+" não se encontra na base de dados"));;
		var isAdmin=user.getRoles().stream()
				.anyMatch(role-> role.getName().equalsIgnoreCase(Role.Values.ADMIN.name()));
		var isEmpresa=user.getRoles().stream()
				.anyMatch( role -> role.getName().equalsIgnoreCase(Role.Values.EMPRESA.name()));
		if(isAdmin || isEmpresa) {
		PacoteDeViagem pacote=new PacoteDeViagem();
		pacote.setDescricao(pacoteDTO.getDescricao());
		pacote.setDestino(pacoteDTO.getDestino());
		pacote.setDuracaoEmDias(pacoteDTO.getDuracaoEmDias());
		pacote.setCategoria(pacoteDTO.getCategoria());
		pacote.setEmpresa(empresa);
		pacote.setImagem(pacoteDTO.getImagem());
		pacote.setPreco(pacoteDTO.getPreco());
		 pacoteRepository.save(pacote);
		 return pacoteDTO;
		 }else {
			 throw new ForbiddenAccesException("você não tem permissão");
		 }
		
	}
	
	public PacoteDeViagemDto findById(@PathVariable Long id,JwtAuthenticationToken token) {
		var user=userRepository.findById(UUID.fromString(token.getName())).orElseThrow(()-> new ResourceNotFoundException("O pacote de id "+id+" não foi encontrado na base de dados"));
		var isAdmin=user.getRoles().stream()
				.anyMatch(role-> role.getName().equalsIgnoreCase(Role.Values.ADMIN.name()));
		var pacote=pacoteRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("O pacote de id "+id+" não foi encontrado na base de dados"));
		if(isAdmin || pacote.getEmpresa().getUser().getUserId().equals(UUID.fromString(token.getName()))) {
			return new PacoteDeViagemDto(pacote);
		}else {
			throw new ForbiddenAccesException();
		}
			
	}

	public PacoteDeViagemDto update( @RequestBody PacoteDeViagemDto pacote) {
		return null;// services.update(pacote);
	}
	public ResponseEntity<?> delete(@PathVariable Long id,JwtAuthenticationToken token) {
		var user=userRepository.findById(UUID.fromString(token.getName())).orElseThrow(()-> new ResourceNotFoundException("O pacote de id "+id+" não foi encontrado na base de dados"));
		var isAdmin=user.getRoles().stream()
				.anyMatch(role-> role.getName().equalsIgnoreCase(Role.Values.ADMIN.name()));
		var pacote=pacoteRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("O pacote de id "+id+" não foi encontrado na base de dados"));
		if(isAdmin || pacote.getEmpresa().getUser().getUserId().equals(UUID.fromString(token.getName()))) {
			pacoteRepository.delete(pacote);
			return ResponseEntity.noContent().build();
		}else {
			throw new ForbiddenAccesException();
		}
		
	}
}