package com.tropical.services;

import com.tropical.data.dto.ReserveDto;
import com.tropical.exceptions.ForbiddenAccesException;
import com.tropical.exceptions.ResourceNotFoundException;
import com.tropical.model.Reserve;
import com.tropical.model.Role;
import com.tropical.repository.ClientRepository;
import com.tropical.repository.ReserveRepository;
import com.tropical.repository.TravelPackageRepository;
import com.tropical.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.Instant;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;
@Service
public class ReserveService {

	private final ReserveRepository reserveRepository;
	private final UserRepository userRepository;
	private final ClientRepository clientRepository;
	private final TravelPackageRepository travelPackageRepository;

	public ReserveService(ReserveRepository reserveRepository, UserRepository userRepository, ClientRepository clientRepository, TravelPackageRepository travelPackageRepository) {
		this.reserveRepository = reserveRepository;
		this.userRepository = userRepository;
		this.clientRepository = clientRepository;
		this.travelPackageRepository = travelPackageRepository;
	}

	public ReserveDto findById(@PathVariable Long id) {
		var reserve = reserveRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Reserve id" + id + "does not exists in data base"));
		return new ReserveDto(reserve);
	}
	public List<ReserveDto> findReserveByClientName(@PathVariable String username, JwtAuthenticationToken token){
		var reservesOfClient=reserveRepository.findByClient_User_Username(username).stream()
				.map(reserve -> new ReserveDto(reserve.getReserveId(),reserve.getCreationDate(),reserve.getTravelDate(),reserve.getClient(),reserve.getTravelPackage()))
				.toList();
		return reservesOfClient;
	}

	public Page<ReserveDto> findAll(@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "size", defaultValue = "12") int size,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {

		var reserves = reserveRepository.findAll(PageRequest.of(page, size, Direction.valueOf(direction), "reserveId"))
				.map(reserve -> new Reserve(reserve.getReserveId(), reserve.getCreationDate(), reserve.getTravelDate(),
						reserve.getClient(), reserve.getTravelPackage()));
		return ReserveDto.listReserves(reserves);
	}


	public ReserveDto create(@RequestBody ReserveDto dto, JwtAuthenticationToken token) {

		var client = clientRepository.findByUser_username(dto.getClient().getUser().getUsername());

		var travelPackage = travelPackageRepository.findById(dto.getTravelPackage().getId());

		if (client.isPresent() && travelPackage.isPresent()) {
			Reserve reserve = new Reserve();
			reserve.setClient(client.get());
			reserve.setCreationDate(Instant.now().atZone(ZoneId.of("America/Sao_Paulo")));
			reserve.setTravelDate(dto.getTravelDate());
			reserve.setTravelPackage(travelPackage.get());
			reserveRepository.save(reserve);
			return new ReserveDto(reserve);
		} else {
			throw new ResourceNotFoundException("Customer or travel package is not found in the database");
		}
	}

	public ReserveDto update(@RequestBody ReserveDto reserveDto, JwtAuthenticationToken token) {

		var user = userRepository.findById(UUID.fromString(token.getName()));
		var reserveBd = reserveRepository.findById(reserveDto.getReserveId()).orElseThrow(() -> new ResourceNotFoundException(
						"Reserve by id" + reserveDto.getReserveId() + " does not found in the data base"));
		var travelPackage = travelPackageRepository.findById(reserveDto.getTravelPackage().getId())
				.orElseThrow(() -> new ResourceNotFoundException(
						"Travel package by id " + reserveDto.getTravelPackage().getId() + " does not found in the data base"));
		var isAdmin = user.get().getRoles().stream()
				.anyMatch(role -> role.getName().equalsIgnoreCase(Role.Values.ADMIN.name()));
		if (isAdmin || reserveBd.getClient().getUser().getUserId().equals(UUID.fromString(token.getName()))) {
			reserveBd.setTravelPackage(travelPackage);
			reserveBd.setTravelDate(reserveDto.getTravelDate());
			reserveRepository.save(reserveBd);
			return reserveDto;
		} else {
			throw new ForbiddenAccesException();
		}

	}
	
	public ResponseEntity<?> delete(@PathVariable Long id, JwtAuthenticationToken token) {
		var user = userRepository.findById(UUID.fromString(token.getName()));
		var isAdmin = user.get().getRoles().stream()
				.anyMatch(role -> role.getName().equalsIgnoreCase(Role.Values.ADMIN.name()));
		var reserveBd=reserveRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("The reserve by id: "+id+" does not found in the data base"));
		if (isAdmin || reserveBd.getClient().getUser().getUserId().equals((user.get().getUserId()))) {
			reserveRepository.deleteById(id);
		} else {
			throw new ForbiddenAccesException(
					"The user: " + user.get().getUsername() + " does not have permission to perform this operation");
		}
		return ResponseEntity.noContent().build();
	}
}
