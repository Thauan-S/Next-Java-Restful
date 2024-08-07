package com.tropical.services;

import java.nio.file.AccessDeniedException;
import java.util.UUID;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.webjars.NotFoundException;

import com.tropical.data.dto.ClientListDto;
import com.tropical.data.dto.ClienteDto;
import com.tropical.data.dto.ClienteItemDTO;
import com.tropical.exceptions.ForbiddenAccesException;
import com.tropical.exceptions.ResourceNotFoundException;
import com.tropical.model.Role;
import com.tropical.repository.ClienteRepository;
import com.tropical.repository.UserRepository;

@Service
public class ClienteService {

	private final ClienteRepository clienteRepository;
	private final UserRepository userRepository;

	public ClienteService(ClienteRepository clienteRepository, UserRepository userRepository) {
		this.clienteRepository = clienteRepository;
		this.userRepository = userRepository;
	}


	public ResponseEntity<ClienteDto> findById(@PathVariable Long id) {
		var cliente = clienteRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("O cliente de id" + id + "não existe na base de dados"));
		return ResponseEntity.ok(new ClienteDto(cliente));
	}


	public ResponseEntity<ClientListDto> findAll(@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "size", defaultValue = "12") int size,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {

		var clientes = clienteRepository.findAll(PageRequest.of(page, size, Direction.valueOf(direction), "nome"))
				.map(cliente -> new ClienteItemDTO(cliente.getClienteId(), cliente.getNome(), cliente.getTelefone(),
						cliente.getDataNascimento(), cliente.getCep(), cliente.getUser()));

		return ResponseEntity.ok(new ClientListDto(clientes.getContent(), page, size, clientes.getTotalPages(),
				clientes.getTotalElements()));
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

	
	public ClienteDto update(@RequestBody ClienteDto clientedto, JwtAuthenticationToken token)
			throws AccessDeniedException {
		System.out.println("nome de usuário e"+ clientedto.getUser().getUsername());
		var user = userRepository.findById(UUID.fromString(token.getName()));
		var clibd = clienteRepository.findById(clientedto.getClienteId())
				.orElseThrow(() -> new ResourceNotFoundException(
						"Cliente de id " + clientedto.getClienteId() + " Não encontrado na base de dados"));
		var isAdmin = user.get().getRoles().stream()
				.anyMatch(role -> role.getName().equalsIgnoreCase(Role.Values.ADMIN.name()));
		if (isAdmin || clibd.getUser().getUserId().equals(UUID.fromString(token.getName()))) {

			//user.get().setUsername(clientedto.getUser().getUsername());
			//user.get().setPassword(clientedto.getUser().getPassword());
			clibd.setNome(clientedto.getNome());
			clibd.setDataNascimento(clientedto.getDataNascimento());
			clibd.setTelefone(clientedto.getTelefone());
			clibd.setCep(clientedto.getCep());
			clienteRepository.save(clibd);
			return new ClienteDto(clibd);
		} else {
			throw new ForbiddenAccesException("O usuário não tem permissão para realizar esta ação");
		}
	}

	public ResponseEntity<?> delete(@PathVariable Long id, JwtAuthenticationToken token) {
		var user = userRepository.findById(UUID.fromString(token.getName()));
		var clibd = clienteRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Cliente de id " + id + "não encontrado na base de dados"));
		var isAdmin = user.get().getRoles().stream()
				.anyMatch(role -> role.getName().equalsIgnoreCase(Role.Values.ADMIN.name()));
		if (isAdmin || clibd.getUser().getUserId().equals(UUID.fromString(token.getName()))) {
			clienteRepository.deleteById(id);
		} else {
			throw new ForbiddenAccesException("O usuário não tem permissão para realizar esta ação");
		}

		return ResponseEntity.noContent().build();
	}
}
