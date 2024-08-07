package com.tropical.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import com.tropical.services.PacoteService;
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
	@Autowired
	PacoteService pacoteService;


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
	//@PreAuthorize("hasAnyAuthority('SCOPE_ADMIN', 'SCOPE_BASIC')")
	public Page<PacoteDeViagemDto> findAll(
			@RequestParam(name = "page",defaultValue = "0" )int page,
			@RequestParam(name = "size",defaultValue = "12" )int size,
			@RequestParam(name = "direction",defaultValue = "asc" )String direction){
		
		
		return pacoteService.findAll(page, size, direction);
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
		return pacoteService.create(pacoteDTO, token);
		
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
		return pacoteService.findById(id, token);
			
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
		return  pacoteService.update(pacote);
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
	return pacoteService.delete(id, token);	
		
	}
}