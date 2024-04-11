package com.tropical.controller;

import org.hibernate.query.SortDirection;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tropical.data.dto.ClienteDTO;
import com.tropical.model.Cliente;
import com.tropical.repository.ClienteRepository;
import com.tropical.repository.UserRepository;
import com.tropical.utils.MediaType;

import io.swagger.v3.oas.annotations.Operation;
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
	
	public ClienteDTO findById(@PathVariable Long id) {
		var cliente= clienteRepository.findById(id).get();
		return new ClienteDTO(cliente.getNome(), cliente.getTelefone(), cliente.getDataNascimento(), cliente.getCep(), cliente.getUser());
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
			)
	public ResponseEntity<ClienteDTO> findAll(
			@RequestParam (value="page",defaultValue="0")int page,
			@RequestParam (value="size",defaultValue="12")int size,
			@RequestParam(value="direction",defaultValue="asc")String direction){
		var clientes =clienteRepository.findAll(PageRequest.of(page, size,Sort.Direction.ASC ,"nome"))
				.map(cliItem-> new ClienteItemDTO(cliItem.getNome(), cliItem.getTelefone(),cliItem.getDataNascimento(), cliItem.getCep(),cliItem.getUser()));
		return ResponseEntity.ok().build();
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
//	public ResponseEntity<PagedModel<EntityModel<ClienteDTO>>> findClientesByName(
//			@PathVariable(value="nome")String nome,
//			@RequestParam (value="page",defaultValue="0")int page,
//			@RequestParam (value="size",defaultValue="12")int size,
//			@RequestParam(value="direction",defaultValue="asc")String direction){
//		
//		var sortDirection="desc".equalsIgnoreCase(direction)? Direction.DESC : Direction.ASC;
//		Pageable pageable= PageRequest.of(page, size,Sort.by(sortDirection,"nome"));
//		return ResponseEntity.ok(services.findClientesByName(nome,pageable));
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
//	@PutMapping(
//			produces ={ MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML},
//			consumes = { MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML})
//	@Operation(
//			summary = "Atualiza um cliente",
//			description = "Atualiza um cliente através de JSON,XML ou YML",
//			tags = {"Clientes"},
//			responses = {
//				@ApiResponse(description="Updated",responseCode ="200",
//						content=@Content(schema = @Schema(implementation = ClienteDTO.class))
//				),
//				
//				@ApiResponse(description="Bad Request",responseCode ="400",content = @Content ),
//				@ApiResponse(description="Unauthorized ",responseCode ="401",content = @Content ),
//				@ApiResponse(description="Not Found",responseCode ="404",content = @Content),
//				@ApiResponse(description="Internal Server Error",responseCode ="500",content = @Content )
//				}
//			)
//	public ClienteDTO update(@RequestBody ClienteDTO cliente) {
//		return services.update(cliente);
//	}
	
//	@DeleteMapping("/{id}")
//	@Operation(
//			summary = "Deleta um cliente",
//			description = "Deleta um cliente",
//			tags = {"Clientes"},
//			responses = {
//				@ApiResponse(description="No Content",responseCode ="204",
//					content=@Content
//				),
//				@ApiResponse(description="Bad Request",responseCode ="400",content = @Content ),
//				@ApiResponse(description="Unauthorized ",responseCode ="401",content = @Content ),
//				@ApiResponse(description="Not Found",responseCode ="404",content = @Content),
//				@ApiResponse(description="Internal Server Error",responseCode ="500",content = @Content )
//				}
//			)
//	public ResponseEntity<?> delete(@PathVariable Long id) {
//		 services.delete(id);
//	return ResponseEntity.noContent().build();
//	}
}
