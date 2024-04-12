package com.tropical.controller;

import java.nio.file.AccessDeniedException;
import java.util.UUID;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
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
import org.webjars.NotFoundException;

import com.tropical.data.dto.ClientListDto;
import com.tropical.data.dto.ClienteDTO;
import com.tropical.data.dto.ClienteDTOO;
import com.tropical.data.dto.ClienteItemDTO;
import com.tropical.exceptions.ForbiddenAccesException;
import com.tropical.model.Cliente;
import com.tropical.model.Role;
import com.tropical.repository.ClienteRepository;
import com.tropical.repository.UserRepository;
import com.tropical.utils.MediaType;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/api/clientes/v1")
@Tag(name = "Clientes" ,description = "Endpoint para Gerenciar Clientes")
public class ClienteController {
	
private final ClienteRepository clienteRepository;
private final UserRepository userRepository;
public ClienteController(ClienteRepository clienteRepository,UserRepository userRepository) {
this.clienteRepository = clienteRepository;
this.userRepository = userRepository;
}
	
	@GetMapping(value="/{id}",
			produces = { 	MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML})
	
	@Operation(
			summary = "Busca um cliente",
			description = "Busca um cliente",
			tags = {"Clientes"},
			responses = {
				@ApiResponse(description="Success",responseCode ="200",
						content=@Content(schema = @Schema(implementation = ClienteDTO.class))
				),
				@ApiResponse(description="No Content",responseCode ="204",content = @Content ),
				@ApiResponse(description="Bad Request",responseCode ="400",content = @Content ),
				@ApiResponse(description="Unauthorized ",responseCode ="401",content = @Content ),
				@ApiResponse(description="Not Found",responseCode ="404",content = @Content),
				@ApiResponse(description="Internal Server Error",responseCode ="500",content = @Content )
				}
			)
	@PreAuthorize(value ="hasAuthority('SCOPE_ADMIN')")
	public ResponseEntity<ClienteDTOO> findById(@PathVariable Long id) {
		var cliente= clienteRepository.findById(id).orElseThrow(()-> new NotFoundException("O cliente de id"+id+"não existe na base de dados"));
		return ResponseEntity.ok(new ClienteDTOO(cliente));
		//return new ClienteDTO(cliente.getNome(), cliente.getTelefone(), cliente.getDataNascimento(), cliente.getCep(), cliente.getUser());
	}
	@GetMapping(
			produces = { MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML})
	@Operation(
			summary = "Busca todos clientes",
			description = "Busca todos clientes",
			tags = {"Clientes"},
			responses = {
				@ApiResponse(description="Success",responseCode ="200",content = {
						@Content(
								mediaType = "application/json",
								array = @ArraySchema(schema = @Schema(implementation = ClienteDTO.class))
						)
				}),
				@ApiResponse(description="Bad Request",responseCode ="400",content = @Content ),
				@ApiResponse(description="Unauthorized ",responseCode ="401",content = @Content ),
				@ApiResponse(description="Not Found",responseCode ="404",content = @Content),
				@ApiResponse(description="Internal Server Error",responseCode ="500",content = @Content )
				}
			)//ok
	@PreAuthorize(value ="hasAuthority('SCOPE_ADMIN')")
	public ResponseEntity<ClientListDto> findAll(
			@RequestParam (value="page",defaultValue="0")int page,
			@RequestParam (value="size",defaultValue="12")int size,
			@RequestParam(value="direction",defaultValue="asc")String direction){
		var clientes =clienteRepository.findAll(PageRequest.of(page, size,Direction.valueOf(direction) ,"nome"))
				.map(cliente -> new ClienteItemDTO(
						cliente.getClienteId(),
						cliente.getNome(),
						cliente.getTelefone(),
						cliente.getDataNascimento(),
						cliente.getCep(),
						cliente.getUser()));	
		
		return ResponseEntity.ok(new ClientListDto(clientes.getContent(), page,size,clientes.getTotalPages(),clientes.getTotalElements()));
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
	


	@PostMapping ( 
			produces = { MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML},
			consumes ={ MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML})
	@Operation(
			summary = "Cria um cliente",
			description = "Cria um cliente através de JSON,XML ou YML",
			tags = {"Clientes"},
			responses = {
				@ApiResponse(description="Success",responseCode ="200",
						content=@Content(schema = @Schema(implementation = ClienteDTO.class))
				),
				
				@ApiResponse(description="Bad Request",responseCode ="400",content = @Content ),
				@ApiResponse(description="Unauthorized ",responseCode ="401",content = @Content ),
				@ApiResponse(description="Internal Server Error",responseCode ="500",content = @Content )
				}
			)
	public ResponseEntity<Void> create(@RequestBody ClienteDTO dto) {
		 var cliente= new Cliente();
		 if(!clienteRepository.findByUser_UserId(dto.user().getUserId()).isPresent()) {
		 var user= userRepository.findById(dto.user().getUserId());
		 cliente.setNome(dto.nome());
		 cliente.setCep(dto.cep());
		 cliente.setTelefone(dto.telefone());
		 cliente.setDataNascimento(dto.dataNascimento());
		 cliente.setUser(user.get());
		 clienteRepository.save(cliente);
		 
	}else {
		return ResponseEntity.status(HttpStatus.CONFLICT).build();
	}
		return ResponseEntity.ok().build();
	}
	@PutMapping(
			produces ={ MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML},
			consumes = { MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML})
	@Operation(
			summary = "Atualiza um cliente",
			description = "Atualiza um cliente através de JSON,XML ou YML",
			tags = {"Clientes"},
			responses = {
				@ApiResponse(description="Updated",responseCode ="200",
						content=@Content(schema = @Schema(implementation = ClienteDTO.class))
				),
				
				@ApiResponse(description="Bad Request",responseCode ="400",content = @Content ),
				@ApiResponse(description="Unauthorized ",responseCode ="401",content = @Content ),
				@ApiResponse(description="Not Found",responseCode ="404",content = @Content),
				@ApiResponse(description="Internal Server Error",responseCode ="500",content = @Content )
				}
			)
	public ClienteDTOO update(@RequestBody ClienteDTOO clientedto,JwtAuthenticationToken token) throws AccessDeniedException {
		var user= userRepository.findById(UUID.fromString(token.getName()));
		var cliBd=clienteRepository.findById(clientedto.getClienteId()).orElseThrow(()-> new NotFoundException("Cliente não encontrado na base de dados"));
		var isAdmin=user.get().getRoles()
			.stream()
			.anyMatch(role-> role.getName().equalsIgnoreCase(Role.Values.ADMIN.name()));
		if(isAdmin || cliBd.getUser().getUserId().equals( UUID.fromString(token.getName())) ) {
		
		cliBd.setNome(clientedto.getNome());
		cliBd.setTelefone(clientedto.getTelefone());
		cliBd.setDataNascimento(clientedto.getDataNascimento());
		cliBd.setCep(clientedto.getCep());
		clienteRepository.save(cliBd);
		return clientedto ;
		}
		else {
			throw new ForbiddenAccesException();
		}
			
	}
	@DeleteMapping("/{id}")
	@Operation(
			summary = "Deleta um cliente",
			description = "Deleta um cliente",
			tags = {"Clientes"},
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
	public ResponseEntity<?> delete(@PathVariable Long id,JwtAuthenticationToken token ) {
		
		var user= userRepository.findById( UUID.fromString(token.getName()));
		System.out.println("usuário"+user.get().getUserId());
		var isAdmin= user.get().getRoles()
				.stream()
				.anyMatch(role -> role.getName().equalsIgnoreCase(Role.Values.ADMIN.name()));
		var cliBd= clienteRepository.findById(id);
		if(isAdmin ||cliBd.get().getUser().getUserId().equals(user.get().getUserId())) {
			clienteRepository.deleteById(id);
		}else {
			throw new ForbiddenAccesException("O usuário " +user.get().getUsername()+" Não tem permissão para realizar esta operação");
		}
		return ResponseEntity.noContent().build();
	}
}
