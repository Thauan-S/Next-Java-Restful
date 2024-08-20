package com.tropical.controller;

import com.tropical.data.dto.ClientDto;
import com.tropical.data.dto.ClientListDto;
import com.tropical.services.ClientService;
import com.tropical.utils.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;

@RestController
@RequestMapping("/api/clients/v1")
@Tag(name = "Clients", description = "Endpoint to manage Clients")
public class ClientController {

    @Autowired
    ClientService clientService;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON)
    @Operation(summary = "Find a client by id ", description = "Find a client by id ", tags = {"Clients"}, responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = ClientDto.class))),
            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized ", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)})
    @PreAuthorize(value = "hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<ClientDto> findById(@PathVariable Long id) {
        return clientService.findById(id);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary = "Find all clients", description = "Find all clients", tags = {
            "Clients"}, responses = {@ApiResponse(description = "Success", responseCode = "200", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ClientDto.class)))}),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized ", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)})
    @PreAuthorize(value = "hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<ClientListDto> findAll(@RequestParam(value = "page", defaultValue = "0") int page,
                                                 @RequestParam(value = "size", defaultValue = "12") int size,
                                                 @RequestParam(value = "direction", defaultValue = "ASC") String direction) {

        return clientService.findAll(page, size, direction);
    }

    @PutMapping(produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
            MediaType.APPLICATION_YML}, consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
            MediaType.APPLICATION_YML})
    @Operation(summary = "Update a client", description = "Update a client", tags = {
            "Clients"}, responses = {
            @ApiResponse(description = "Updated", responseCode = "200", content = @Content(schema = @Schema(implementation = ClientDto.class))),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized ", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)})
    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN', 'SCOPE_BASIC')")
    public ClientDto update(@RequestBody ClientDto customerDto, JwtAuthenticationToken token)
            throws AccessDeniedException {

        return clientService.update(customerDto, token);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a client by id", description = "Delete a client by id", tags = {"Clients"}, responses = {
            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized ", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)})
    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN', 'SCOPE_BASIC')")
    public ResponseEntity<?> delete(@PathVariable Long id, JwtAuthenticationToken token) {
        return clientService.delete(id, token);
    }
}
