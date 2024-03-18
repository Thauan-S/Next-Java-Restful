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

import com.tropical.data.vo.v1.ReservaVO;
import com.tropical.services.ReservaServices;
import com.tropical.utils.MediaType;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RequestMapping("/api/reservas/v1")
@RestController
@Tag(name = "Reservas" ,description = "Endpoint para Gerenciar Reservas ")
public class ReservaController {
	@Autowired
	ReservaServices services;
	
	@GetMapping(produces = { MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML})
	@Operation(
			summary = "Busca todas reservas",
			description = "Busca todas reservas",
			tags = {"Reservas"},
			responses = {
				@ApiResponse(description="Success",responseCode ="200",content = {
						@Content(
								mediaType = "application/json",
								array = @ArraySchema(schema = @Schema(implementation = ReservaVO.class))
						)
				}),
				@ApiResponse(description="Bad Request",responseCode ="400",content = @Content ),
				@ApiResponse(description="Unauthorized ",responseCode ="401",content = @Content ),
				@ApiResponse(description="Not Found",responseCode ="404",content = @Content),
				@ApiResponse(description="Internal Server Error",responseCode ="500",content = @Content )
				}
			)
	public List<ReservaVO> findAll() {
		return services.findAll();
	}

	@PostMapping(
			produces = { MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML},
			consumes = { MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML})
	@Operation(
			summary = "Cria uma reserva",
			description = "Cria uma reserva através de JSON,XML ou YML",
			tags = {"Reservas"},
			responses = {
				@ApiResponse(description="Success",responseCode ="200",
						content=@Content(schema = @Schema(implementation = ReservaVO.class))
				),
				
				@ApiResponse(description="Bad Request",responseCode ="400",content = @Content ),
				@ApiResponse(description="Unauthorized ",responseCode ="401",content = @Content ),
				@ApiResponse(description="Internal Server Error",responseCode ="500",content = @Content )
				}
			)
	public ReservaVO create(@RequestBody ReservaVO pacote) {
		return services.create(pacote);
	}

	@GetMapping(value="/{id}",produces = { MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML})
	@Operation(
			summary = "Busca uma reserva",
			description = "Busca uma reserva",
			tags = {"Reservas"},
			responses = {
				@ApiResponse(description="Success",responseCode ="200",
						content=@Content(schema = @Schema(implementation = ReservaVO.class))
				),
				@ApiResponse(description="No Content",responseCode ="204",content = @Content ),
				@ApiResponse(description="Bad Request",responseCode ="400",content = @Content ),
				@ApiResponse(description="Unauthorized ",responseCode ="401",content = @Content ),
				@ApiResponse(description="Not Found",responseCode ="404",content = @Content),
				@ApiResponse(description="Internal Server Error",responseCode ="500",content = @Content )
				}
			)
	public ReservaVO findById(@PathVariable Long id) {
		return services.findById(id);
	}

	@PutMapping(
			produces = { MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML},
			consumes = { MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML})
	@Operation(
			summary = "Atualiza uma reserva",
			description = "Atualiza uma reserva através de JSON,XML ou YML",
			tags = {"Reservas"},
			responses = {
				@ApiResponse(description="Updated",responseCode ="200",
						content=@Content(schema = @Schema(implementation = ReservaVO.class))
				),
				
				@ApiResponse(description="Bad Request",responseCode ="400",content = @Content ),
				@ApiResponse(description="Unauthorized ",responseCode ="401",content = @Content ),
				@ApiResponse(description="Not Found",responseCode ="404",content = @Content),
				@ApiResponse(description="Internal Server Error",responseCode ="500",content = @Content )
				}
			)
	public ReservaVO update( @RequestBody ReservaVO pacote) {
		return services.update(pacote);
	}

	@DeleteMapping("/{id}")
	@Operation(
			summary = "Deleta uma reserva",
			description = "Deleta uma reserva",
			tags = {"Reservas"},
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
