package com.tropical.services;

import java.lang.reflect.Type;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.webjars.NotFoundException;

import com.tropical.data.dto.EmpresaDTO;
import com.tropical.exceptions.ForbiddenAccesException;
import com.tropical.exceptions.ResourceNotFoundException;
import com.tropical.model.Empresa;
import com.tropical.model.Role;
import com.tropical.repository.EmpresaRepository;
import com.tropical.repository.UserRepository;
import com.tropical.utils.MediaType;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@Service
public class EmpresaService {
	
 private final EmpresaRepository empresaRepository;
 private final UserRepository userRepository;
public EmpresaService(EmpresaRepository empresaRepository,UserRepository userRepository) {
	this.empresaRepository = empresaRepository;
	this.userRepository = userRepository;
}

	public ResponseEntity<EmpresaDTO> findById(@PathVariable Long id) {
		var empresa= empresaRepository.findById(id).orElseThrow(()-> new NotFoundException("O cliente de id"+id+"não existe na base de dados"));
		return ResponseEntity.ok(new EmpresaDTO(empresa));
	}
	

	public Page<EmpresaDTO> findAll(
			@RequestParam (value="page",defaultValue="0")int page,
			@RequestParam (value="size",defaultValue="12")int size,
			@RequestParam(value="direction",defaultValue="ASC")String direction){
		var  empresas =empresaRepository.findAll(PageRequest.of(page, size,Direction.valueOf(direction) ,"nomeEmpresa"))
				.map(empresa -> new Empresa(
						empresa.getEmpresaId(),
						empresa.getNomeEmpresa(),
						empresa.getCnpj(),
						empresa.getEndereco(),
						empresa.getUser(),
						empresa.getPacoteDeViagem()
						));
		return EmpresaDTO.listaEmpresas(empresas);
	}
//	@GetMapping(
//			value="/findClientesByName/{nome}",
//			produces = { MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML})
//	@Operation(
//			summary = "Busca clientes  por nome",
//			description = "Busca clientes  por nome",
//			tags = {"Clientes"},
//			responses = {
//					@ApiResponse(description="Success",responseCode ="200",content = {
//							@Content(
//									mediaType = "application/json",
//									array = @ArraySchema(schema = @Schema(implementation = ClienteDTO.class))
//									)
//					}),
//					@ApiResponse(description="Bad Request",responseCode ="400",content = @Content ),
//					@ApiResponse(description="Unauthorized ",responseCode ="401",content = @Content ),
//					@ApiResponse(description="Not Found",responseCode ="404",content = @Content),
//					@ApiResponse(description="Internal Server Error",responseCode ="500",content = @Content )
//			}
//			)
//	public ResponseEntity<ClientListDto> findClientesByName(
//			@PathVariable(value="nome")String nome,
//			@RequestParam (value="page",defaultValue="0")int page,
//			@RequestParam (value="size",defaultValue="12")int size,
//			@RequestParam(value="direction",defaultValue="asc")String direction){
//		var pageable=PageRequest.of(page, size,Sort.Direction.valueOf(direction));
//		var clientes=clienteRepository.findClientesByName(nome, pageable)
//			.map(cliente -> new ClienteItemDTO(cliente.getClienteId(),cliente.ge
//	
//	}
	


//	@PostMapping ( 
//			produces = { MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML},
//			consumes ={ MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML})
//	@Operation(
//			summary = "Cria uma empresa",
//			description = "Cria uma empresa através de JSON,XML ou YML",
//			tags = {"Empresas"},
//			responses = {
//				@ApiResponse(description="Success",responseCode ="200",
//						content=@Content(schema = @Schema(implementation = ClienteDTO.class))
//				),
//				
//				@ApiResponse(description="Bad Request",responseCode ="400",content = @Content ),
//				@ApiResponse(description="Unauthorized ",responseCode ="401",content = @Content ),
//				@ApiResponse(description="Internal Server Error",responseCode ="500",content = @Content )
//				}
//			)
//	public ResponseEntity<EmpresaDTO> create(@RequestBody EmpresaDTO dto) {
//		Empresa empresa= new Empresa();
//		
//		if(empresaRepository.findByUser_UserId(dto.getUser().getUserId()).isEmpty()) {
//			empresa.setUser(dto.getUser());
//			empresa.setNomeEmpresa(dto.getNomeEmpresa());
//			empresa.setCnpj(dto.getCnpj());
//			empresa.setEndereco(dto.getEndereco());
//		}
//		else {
//			throw new UserAlreadyUsedException();
//		}
//		return ResponseEntity.ok(dto);
//	}

	public EmpresaDTO update(@RequestBody EmpresaDTO empresaDTO,JwtAuthenticationToken token) {
		var user= userRepository.findById(UUID.fromString(token.getName()));
		System.out.println(user.get().getUsername());
	
		var empresabd=empresaRepository.findById(empresaDTO.getEmpresaId()).orElseThrow(()-> new NotFoundException("Empresa não encontrada na base de dados"));
		System.out.println(token.getName() +"==="+ empresabd.getUser().getUserId());
		var isAdmin=user.get().getRoles()
			.stream()
			.anyMatch(role-> role.getName().equalsIgnoreCase(Role.Values.ADMIN.name()));
		if(isAdmin || empresabd.getUser().getUserId().equals(UUID.fromString(token.getName())) ) {
		
			empresabd.setNomeEmpresa(empresaDTO.getNomeEmpresa());
			empresabd.setEndereco(empresaDTO.getEndereco());
			empresabd.setCnpj(empresaDTO.getCnpj());
			//empresabd.setUser();
			//empresabd.setPacoteDeViagem(empresaDTO.getPacoteDeViagem());
			
		empresaRepository.save(empresabd);
		return new EmpresaDTO(empresabd) ;
		}
		else {
			throw new ForbiddenAccesException("o usuário não possui permissão");
		}
			
	}

	public ResponseEntity<?> delete(@PathVariable Long id,JwtAuthenticationToken token ) {

		var user= userRepository.findById( UUID.fromString(token.getName()));
		System.out.println("usuário"+user.get().getUserId());
		var isAdmin= user.get().getRoles()
				.stream()
				.anyMatch(role -> role.getName().equalsIgnoreCase(Role.Values.ADMIN.name()));
		var empresaBd= empresaRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("a empresa não se encontra  no banco de dados"));
		if(isAdmin ||empresaBd.getUser().getUserId().equals(user.get().getUserId())) {
			empresaRepository.deleteById(id);
		}else {
			throw new ForbiddenAccesException("O usuário " +user.get().getUsername()+" Não tem permissão para realizar esta operação");
		}
		return ResponseEntity.noContent().build();
	}
}
