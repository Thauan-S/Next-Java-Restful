package com.tropical.controller;


import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tropical.data.dto.PacoteDeViagemDto;
import com.tropical.exceptions.ForbiddenAccesException;
import com.tropical.exceptions.ResourceNotFoundException;
import com.tropical.model.PacoteDeViagem;
import com.tropical.model.Role;
import com.tropical.repository.EmpresaRepository;
import com.tropical.repository.PacoteRepository;
import com.tropical.repository.UserRepository;
import com.tropical.utils.MediaType;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/api/pacotes/v1")
@Tag(name = "Pacotes" ,description = "Endpoint para Gerenciar Pacotes de Viagem")
public class PacoteController {
	private final PacoteRepository pacoteRepository;
	private final UserRepository userRepository;
	private final EmpresaRepository empresaRepository;
	
	public PacoteController(PacoteRepository pacoteRepository, UserRepository userRepository, EmpresaRepository empresaRepository) {
		this.pacoteRepository = pacoteRepository;
		this.userRepository = userRepository;
		this.empresaRepository = empresaRepository;
	}


	@GetMapping(produces = { MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML})
	@Operation(
			summary = "Busca todos pacotes de viagem",
			description = "Busca todos pacotes de viagem",
			tags = {"Pacotes"},
			responses = {
				@ApiResponse(description="Success",responseCode ="200",content = {
						@Content(
								mediaType = "application/json",
								array = @ArraySchema(schema = @Schema(implementation = PacoteDeViagemDto.class))
						)
				}),
				@ApiResponse(description="Bad Request",responseCode ="400",content = @Content ),
				@ApiResponse(description="Unauthorized ",responseCode ="401",content = @Content ),
				@ApiResponse(description="Not Found",responseCode ="404",content = @Content),
				@ApiResponse(description="Internal Server Error",responseCode ="500",content = @Content )
				}
			)
	@PreAuthorize("hasAuthority('SCOPE_ADMIN')")
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

	
	@PostMapping(produces = { MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML},
				consumes = { MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML})
	@Operation(
			summary = "Cria um pacote de viagem",
			description = "Cria um pacote de viagem através de JSON,XML ou YML",
			tags = {"Pacotes"},
			responses = {
				@ApiResponse(description="Success",responseCode ="200",
						content=@Content(schema = @Schema(implementation = PacoteDeViagemDto.class))
				),
				
				@ApiResponse(description="Bad Request",responseCode ="400",content = @Content ),
				@ApiResponse(description="Unauthorized ",responseCode ="401",content = @Content ),
				@ApiResponse(description="Internal Server Error",responseCode ="500",content = @Content )
				}
			)
	@PreAuthorize("hasAnyAuthority('SCOPE_ADMIN', 'SCOPE_EMPRESA')")
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
	
	@GetMapping(value="/{id}",produces ={ MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML})
	@Operation(
			summary = "Busca um pacote de viagem",
			description = "Busca um pacote de viagem",
			tags = {"Pacotes"},
			responses = {
				@ApiResponse(description="Success",responseCode ="200",
						content=@Content(schema = @Schema(implementation = PacoteDeViagemDto.class))
				),
				@ApiResponse(description="No Content",responseCode ="204",content = @Content ),
				@ApiResponse(description="Bad Request",responseCode ="400",content = @Content ),
				@ApiResponse(description="Unauthorized ",responseCode ="401",content = @Content ),
				@ApiResponse(description="Not Found",responseCode ="404",content = @Content),
				@ApiResponse(description="Internal Server Error",responseCode ="500",content = @Content )
				}
			)
	@PreAuthorize("hasAnyAuthority('SCOPE_ADMIN', 'SCOPE_EMPRESA')")
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

	@PutMapping(
			produces = { MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML},
			consumes ={ MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML})
	@Operation(
			summary = "Atualiza um pacote de viagem",
			description = "Atualiza um pacote de viagem através de JSON,XML ou YML",
			tags = {"Pacotes"},
			responses = {
				@ApiResponse(description="Updated",responseCode ="200",
						content=@Content(schema = @Schema(implementation = PacoteDeViagemDto.class))
				),
				
				@ApiResponse(description="Bad Request",responseCode ="400",content = @Content ),
				@ApiResponse(description="Unauthorized ",responseCode ="401",content = @Content ),
				@ApiResponse(description="Not Found",responseCode ="404",content = @Content),
				@ApiResponse(description="Internal Server Error",responseCode ="500",content = @Content )
				}
			)
	public PacoteDeViagemDto update( @RequestBody PacoteDeViagemDto pacote) {
		return null;// services.update(pacote);
	}
	@PreAuthorize("hasAnyAuthority('SCOPE_ADMIN', 'SCOPE_EMPRESA')")
	@DeleteMapping("/{id}")
	@Operation(
			summary = "Deleta um pacote de viagem",
			description = "Deleta um pacote de viagem",
			tags = {"Pacotes"},
			responses = {
				@ApiResponse(description="No Content",responseCode ="204",
					content=@Content
				),
				@ApiResponse(description="Bad Request",responseCode ="400",content = @Content ),
				@ApiResponse(description="Unauthorized ",responseCode ="401",content = @Content ),
				@ApiResponse(description="Not Found",responseCode ="404",content = @Content),
				@ApiResponse(description="Internal Server Error",responseCode ="500",content = @Content )
				}
			)
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