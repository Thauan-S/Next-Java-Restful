package com.tropical.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tropical.data.vo.v1.ContatoVO;
import com.tropical.services.ContatoServices;
import com.tropical.utils.MediaType;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/contatos/v1")
@Tag(name = "Contatos" ,description = "Endpoint para Gerenciar Contatos")
public class ContatoController {
@Autowired
ContatoServices services;
	
@PostMapping( 
		produces ={ MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML},
		consumes = { MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML})
@Operation(
		summary = "Cria um contato",
		description = "Cria um contato através de JSON,XML ou YML",
		tags = {"Contatos"},
		responses = {
			@ApiResponse(description="Success",responseCode ="200",
					content=@Content(schema = @Schema(implementation = ContatoVO.class))
			),
			
			@ApiResponse(description="Bad Request",responseCode ="400",content = @Content ),
			@ApiResponse(description="Unauthorized ",responseCode ="401",content = @Content ),
			@ApiResponse(description="Internal Server Error",responseCode ="500",content = @Content )
			}
		)
public ContatoVO create(@RequestBody ContatoVO contato) {
	return services.create(contato);
}
@PutMapping(
		produces = { MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML},
		consumes = { MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML})
@Operation(
		summary = "Atualiza um contato",
		description = "Atualiza um contato através de JSON,XML ou YML",
		tags = {"Contatos"},
		responses = {
			@ApiResponse(description="Updated",responseCode ="200",
					content=@Content(schema = @Schema(implementation = ContatoVO.class))
			),
			
			@ApiResponse(description="Bad Request",responseCode ="400",content = @Content ),
			@ApiResponse(description="Unauthorized ",responseCode ="401",content = @Content ),
			@ApiResponse(description="Not Found",responseCode ="404",content = @Content),
			@ApiResponse(description="Internal Server Error",responseCode ="500",content = @Content )
			}
		)
public ContatoVO update (@RequestBody ContatoVO contato) {
	return services.update(contato);
}
@GetMapping(produces = { MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML})
@Operation(
		summary = "Busca todos contatos",
		description = "Busca todos contatos",
		tags = {"Contatos"},
		responses = {
			@ApiResponse(description="Success",responseCode ="200",content = {
					@Content(
							mediaType = "application/json",
							array = @ArraySchema(schema = @Schema(implementation = ContatoVO.class))
					)
			}),
			@ApiResponse(description="Bad Request",responseCode ="400",content = @Content ),
			@ApiResponse(description="Unauthorized ",responseCode ="401",content = @Content ),
			@ApiResponse(description="Not Found",responseCode ="404",content = @Content),
			@ApiResponse(description="Internal Server Error",responseCode ="500",content = @Content )
			}
		)
public  ResponseEntity<PagedModel<EntityModel<ContatoVO>>> findAll(
	@RequestParam(value="page",defaultValue ="0")int page, 
	@RequestParam(value="size",defaultValue ="12")int size,
	@RequestParam(value="direction",defaultValue ="asc")String direction) {
	
	var sortDirection="desc".equalsIgnoreCase(direction)? Direction.DESC:Direction.ASC;
	Pageable pageable=PageRequest.of(page, size,Sort.by(sortDirection,"cliente.nome"));
	return ResponseEntity.ok(services.findAll(pageable));
}
@GetMapping(value="/findContatosByAssunto/{assunto}",produces = { MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML})
@Operation(
		summary = "Busca os contatos pelo assunto",
		description = "Busca os contatos pelo assunto",
		tags = {"Contatos"},
		responses = {
				@ApiResponse(description="Success",responseCode ="200",content = {
						@Content(
								mediaType = "application/json",
								array = @ArraySchema(schema = @Schema(implementation = ContatoVO.class))
								)
				}),
				@ApiResponse(description="Bad Request",responseCode ="400",content = @Content ),
				@ApiResponse(description="Unauthorized ",responseCode ="401",content = @Content ),
				@ApiResponse(description="Not Found",responseCode ="404",content = @Content),
				@ApiResponse(description="Internal Server Error",responseCode ="500",content = @Content )
		}
		)
public  ResponseEntity<PagedModel<EntityModel<ContatoVO>>> findContatosByAssunto(
		@PathVariable(value="assunto")String assunto,
		@RequestParam(value="page",defaultValue ="0")int page, 
		@RequestParam(value="size",defaultValue ="12")int size,
		@RequestParam(value="direction",defaultValue ="asc")String direction) {
	
	var sortDirection="desc".equalsIgnoreCase(direction)? Direction.DESC:Direction.ASC;
	Pageable pageable=PageRequest.of(page, size,Sort.by(sortDirection,"assunto"));
	return ResponseEntity.ok(services.findContatosByAssunto(assunto,pageable));
}
@GetMapping(value="/{id}", produces = { MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML})
public ContatoVO findById(@PathVariable Long id) {
	return services.findById(id);
}
@DeleteMapping("/{id}")
@Operation(
		summary = "Deleta um contato",
		description = "Deleta um contato",
		tags = {"Contatos"},
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
public ResponseEntity<?> delete(@PathVariable Long id) {
	services.delete(id);
	return ResponseEntity.noContent().build();
}
}
