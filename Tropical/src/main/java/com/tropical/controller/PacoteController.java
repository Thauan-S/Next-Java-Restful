package com.tropical.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tropical.data.vo.v1.PacoteDeViagemVO;
import com.tropical.services.PacoteServices;
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
	PacoteServices services;
	
	@GetMapping(produces = { MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML})
	@Operation(
			summary = "Busca todos pacotes de viagem",
			description = "Busca todos pacotes de viagem",
			tags = {"Pacotes"},
			responses = {
				@ApiResponse(description="Success",responseCode ="200",content = {
						@Content(
								mediaType = "application/json",
								array = @ArraySchema(schema = @Schema(implementation = PacoteDeViagemVO.class))
						)
				}),
				@ApiResponse(description="Bad Request",responseCode ="400",content = @Content ),
				@ApiResponse(description="Unauthorized ",responseCode ="401",content = @Content ),
				@ApiResponse(description="Not Found",responseCode ="404",content = @Content),
				@ApiResponse(description="Internal Server Error",responseCode ="500",content = @Content )
				}
			)
	public List<PacoteDeViagemVO> findAll() {
		return services.findAll();
	}

	
	@PostMapping(produces = { MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML},
				consumes = { MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML})
	@Operation(
			summary = "Cria um pacote de viagem",
			description = "Cria um pacote de viagem através de JSON,XML ou YML",
			tags = {"Pacotes"},
			responses = {
				@ApiResponse(description="Success",responseCode ="200",
						content=@Content(schema = @Schema(implementation = PacoteDeViagemVO.class))
				),
				
				@ApiResponse(description="Bad Request",responseCode ="400",content = @Content ),
				@ApiResponse(description="Unauthorized ",responseCode ="401",content = @Content ),
				@ApiResponse(description="Internal Server Error",responseCode ="500",content = @Content )
				}
			)
	public PacoteDeViagemVO create(@RequestBody PacoteDeViagemVO pacote) {
		return services.create(pacote);
	}

	@GetMapping(value="/{id}",produces ={ MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML})
	@Operation(
			summary = "Busca um pacote de viagem",
			description = "Busca um pacote de viagem",
			tags = {"Pacotes"},
			responses = {
				@ApiResponse(description="Success",responseCode ="200",
						content=@Content(schema = @Schema(implementation = PacoteDeViagemVO.class))
				),
				@ApiResponse(description="No Content",responseCode ="204",content = @Content ),
				@ApiResponse(description="Bad Request",responseCode ="400",content = @Content ),
				@ApiResponse(description="Unauthorized ",responseCode ="401",content = @Content ),
				@ApiResponse(description="Not Found",responseCode ="404",content = @Content),
				@ApiResponse(description="Internal Server Error",responseCode ="500",content = @Content )
				}
			)
	public PacoteDeViagemVO findById(@PathVariable Long id) {
		return services.findById(id);
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
						content=@Content(schema = @Schema(implementation = PacoteDeViagemVO.class))
				),
				
				@ApiResponse(description="Bad Request",responseCode ="400",content = @Content ),
				@ApiResponse(description="Unauthorized ",responseCode ="401",content = @Content ),
				@ApiResponse(description="Not Found",responseCode ="404",content = @Content),
				@ApiResponse(description="Internal Server Error",responseCode ="500",content = @Content )
				}
			)
	public PacoteDeViagemVO update( @RequestBody PacoteDeViagemVO pacote) {
		return services.update(pacote);
	}

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
	public ResponseEntity<?> delete(@PathVariable Long id) {
		services.delete(id);
		return ResponseEntity.noContent().build();
	}
}
