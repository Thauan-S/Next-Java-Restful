package com.tropical.controller;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
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
import org.webjars.NotFoundException;

import com.tropical.data.dto.ReservaDto;
import com.tropical.exceptions.ForbiddenAccesException;
import com.tropical.exceptions.ResourceNotFoundException;
import com.tropical.model.Reserva;
import com.tropical.model.Role;
import com.tropical.repository.ClienteRepository;
import com.tropical.repository.PacoteRepository;
import com.tropical.repository.ReservaRepository;
import com.tropical.repository.UserRepository;
import com.tropical.utils.MediaType;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/reservas/v1")
@Tag(name = "Reservas", description = "Endpoint para Gerenciar Clientes")
public class ReservaController {

	private final ReservaRepository reservaRepository;
	private final UserRepository userRepository;
	private final ClienteRepository clienteRepository;
	private final PacoteRepository pacoteRepository;

	public ReservaController(ReservaRepository reservaRepository, UserRepository userRepository,
			PacoteRepository pacoteRepository, ClienteRepository clienteRepository) {
		this.reservaRepository = reservaRepository;
		this.userRepository = userRepository;
		this.clienteRepository = clienteRepository;
		this.pacoteRepository = pacoteRepository;
	}

	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
			MediaType.APPLICATION_YML })

	@Operation(summary = "Busca uma reserva", description = "Busca uma reserva", tags = { "Reservas" }, responses = {
			@ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = ReservaDto.class))),
			@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized ", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content) })
	@PreAuthorize(value = "hasAuthority('SCOPE_ADMIN')")
	public ReservaDto findById(@PathVariable Long id) {
		var reserva = reservaRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("A reserva de id" + id + "não existe na base de dados"));
		return new ReservaDto(reserva);
	}

	@GetMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML })
	@Operation(summary = "Busca todos clientes", description = "Busca todos clientes", tags = {
			"Clientes" }, responses = { @ApiResponse(description = "Success", responseCode = "200", content = {
					@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ReservaDto.class))) }),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Unauthorized ", responseCode = "401", content = @Content),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content) })
	@PreAuthorize(value = "hasAuthority('SCOPE_ADMIN')")
	public Page<ReservaDto> findAll(@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "size", defaultValue = "12") int size,
			@RequestParam(value = "direction", defaultValue = "asc") String direction) {

		var reservas = reservaRepository.findAll(PageRequest.of(page, size, Direction.valueOf(direction), "reservaId"))
				.map(reserva -> new Reserva(reserva.getReservaId(), reserva.getDataReserva(), reserva.getDataViagem(),
						reserva.getCliente(), reserva.getPacote()));

		return ReservaDto.listaReservas(reservas);
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

	@PostMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
			MediaType.APPLICATION_YML }, consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
					MediaType.APPLICATION_YML })
	@Operation(summary = "Cria uma reserva", description = "Cria uma reserva através de JSON,XML ou YML", tags = {
			"Reservas" }, responses = {
					@ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = ReservaDto.class))),

					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Unauthorized ", responseCode = "401", content = @Content),
					@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content) })
	@PreAuthorize("hasAuthority('SCOPE_BASIC')")
	public ReservaDto create(@RequestBody ReservaDto dto, JwtAuthenticationToken token) {
		var cliente = clienteRepository.findById(dto.getCliente().getClienteId());

		var pacote = pacoteRepository.findById(dto.getPacote().getId());

		if (cliente.isPresent() && pacote.isPresent()) {
			Reserva reserva = new Reserva();
			reserva.setCliente(cliente.get());
			reserva.setDataReserva(Instant.now().atZone(ZoneId.of("America/Sao_Paulo")));
			reserva.setDataViagem(dto.getDataViagem());
			reserva.setPacote(pacote.get());
			reservaRepository.save(reserva);
			return new ReservaDto(reserva);
		} else {
			throw new ResourceNotFoundException("Cliente ou pacote não se encontram na base de dados");
		}
	}

	@PutMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
			MediaType.APPLICATION_YML }, consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
					MediaType.APPLICATION_YML })
	@Operation(summary = "Atualiza uma reserva", description = "Atualiza uma reserva através de JSON,XML ou YML", tags = {
			"Reservas" }, responses = {
					@ApiResponse(description = "Updated", responseCode = "200", content = @Content(schema = @Schema(implementation = ReservaDto.class))),

					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Unauthorized ", responseCode = "401", content = @Content),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content) })
	public ReservaDto update(@RequestBody ReservaDto reservaDto, JwtAuthenticationToken token) {
		var user = userRepository.findById(UUID.fromString(token.getName()));
		var reservaBd = reservaRepository.findById(reservaDto.getReservaId())
				.orElseThrow(() -> new ResourceNotFoundException(
						"Reserva de id" + reservaDto.getReservaId() + " não encontrada na base de dados"));
		var pacote = pacoteRepository.findById(reservaDto.getPacote().getId())
				.orElseThrow(() -> new ResourceNotFoundException(
						"Pacote de id " + reservaDto.getPacote().getId() + " não encontrado na base de dados"));
		var isAdmin = user.get().getRoles().stream()
				.anyMatch(role -> role.getName().equalsIgnoreCase(Role.Values.ADMIN.name()));
		if (isAdmin || reservaBd.getCliente().getUser().getUserId().equals(UUID.fromString(token.getName()))) {
			reservaBd.setPacote(pacote);
			reservaBd.setDataViagem(reservaDto.getDataViagem());
			reservaRepository.save(reservaBd);
			return reservaDto;
		} else {
			throw new ForbiddenAccesException();
		}

	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Deleta uma reserva", description = "Deleta uma reserva", tags = { "Reservas" }, responses = {
			@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized ", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content) })
	public ResponseEntity<?> delete(@PathVariable Long id, JwtAuthenticationToken token) {
//		var user = userRepository.findById(UUID.fromString(token.getName()));
//		var isAdmin = user.get().getRoles().stream()
//				.anyMatch(role -> role.getName().equalsIgnoreCase(Role.Values.ADMIN.name()));
//		var cliBd = clienteRepository.findById(id);
//		if (isAdmin || cliBd.get().getUser().getUserId().equals(user.get().getUserId())) {
//			reservaRepository.deleteById(id);
//		} else {
//			throw new ForbiddenAccesException(
//					"O usuário " + user.get().getUsername() + " Não tem permissão para realizar esta operação");
//		}
		// Para visualizar no fuso horário do sistema:
		Instant now= Instant.now();
		ZonedDateTime zdt = now.atZone(ZoneId.systemDefault());
		System.out.println("Local system time zone: " + zdt);
		System.out.println(Instant.now().atZone(ZoneId.of("America/Sao_Paulo")));
		return ResponseEntity.noContent().build();
	}
}
