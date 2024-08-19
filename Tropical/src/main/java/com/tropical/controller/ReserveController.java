package com.tropical.controller;

import com.tropical.data.dto.ReserveDto;
import com.tropical.services.ReserveService;
import com.tropical.utils.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reserves/v1")
@Tag(name = "Reserves", description = "Endpoint to manage reserves ")
public class ReserveController {

    @Autowired
    ReserveService reserveService;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON)
    @Operation(summary = "Find a reserve", description = "Find a reserve by id", tags = {"Reserves"}, responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = ReserveDto.class))),
            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized ", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)})
    @PreAuthorize(value = "hasAuthority('SCOPE_ADMIN')")
    public ReserveDto findById(@PathVariable Long id) {
        return reserveService.findById(id);
    }

    @GetMapping(value = "/client/{username}")
    @Operation(summary = "Find a  reserve ", description = "Find a reserve by client name", tags = {"Reserves"}, responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = ReserveDto.class))),
            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized ", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)})
    @PreAuthorize("hasAnyAuthority('SCOPE_BASIC','SCOPE_ADMIN','SCOPE_EMPRESA')")
    public List<ReserveDto> findReserveByClientName(@PathVariable String username, JwtAuthenticationToken token) {
        return reserveService.findReserveByClientName(username, token);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary = "Find all reserves", description = "Find all reserves", tags = {
            "Reserves"}, responses = {@ApiResponse(description = "Success", responseCode = "200", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ReserveDto.class)))}),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized ", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)})
    @PreAuthorize(value = "hasAuthority('SCOPE_ADMIN')")
    public Page<ReserveDto> findAll(@RequestParam(value = "page", defaultValue = "0") int page,
                                    @RequestParam(value = "size", defaultValue = "12") int size,
                                    @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        return reserveService.findAll(page, size, direction);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    @Operation(summary = "Create a  reserve", description = "Create a  reserve", tags = {
            "Reserves"}, responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = ReserveDto.class))),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized ", responseCode = "401", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)})
    @PreAuthorize("hasAuthority('SCOPE_BASIC')")
    public ReserveDto create(@RequestBody ReserveDto reserveDto, JwtAuthenticationToken token) {
        return reserveService.create(reserveDto, token);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    @Operation(summary = "Update a reserve", description = "Update a reserve", tags = {
            "Reserves"}, responses = {
            @ApiResponse(description = "Updated", responseCode = "200", content = @Content(schema = @Schema(implementation = ReserveDto.class))),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized ", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)})
    public ReserveDto update(@RequestBody ReserveDto reserveDto, JwtAuthenticationToken token) {
        return reserveService.update(reserveDto, token);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a reserve", description = "Delete a reserve by id", tags = {"Reserves"}, responses = {
            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized ", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)})
    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN', 'SCOPE_BASIC')")
    public ResponseEntity<?> delete(@PathVariable Long id, JwtAuthenticationToken token) {
        return reserveService.delete(id, token);
    }
}
