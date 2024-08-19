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

import com.tropical.data.dto.TravelPackageDto;
import com.tropical.services.TravelPackageService;
import com.tropical.utils.MediaType;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/api/travelPackages/v1")
@Tag(name = "TravelPackages", description = "Endpoint para manage travel packages")
public class TravelPackageController {
    @Autowired
    TravelPackageService travelPackageService;


    @GetMapping(produces = MediaType.APPLICATION_JSON)
    @Operation(
            summary = "Find all travel packages",
            description = "Find all travel packages",
            tags = {"TravelPackages"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = TravelPackageDto.class))
                            )
                    }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized ", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            }
    )
    public Page<TravelPackageDto> findAll(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "12") int size,
            @RequestParam(name = "direction", defaultValue = "asc") String direction) {
        return travelPackageService.findAll(page, size, direction);
    }


    @PostMapping(produces = MediaType.APPLICATION_JSON,
            consumes = MediaType.APPLICATION_JSON)
    @Operation(
            summary = "Create a travel package",
            description = "Create a travel package",
            tags = {"TravelPackages"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = TravelPackageDto.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized ", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            }
    )
    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN', 'SCOPE_EMPRESA')")
    public TravelPackageDto create(@RequestBody TravelPackageDto travelPackageDto, JwtAuthenticationToken token) {
        return travelPackageService.create(travelPackageDto, token);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON)
    @Operation(
            summary = "Find a travel package",
            description = "Find a travel package by id",
            tags = {"TravelPackages"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = TravelPackageDto.class))
                    ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized ", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            }
    )

    public TravelPackageDto findById(@PathVariable Long id, JwtAuthenticationToken token) {
        return travelPackageService.findById(id, token);
    }

    @PutMapping(
            produces = MediaType.APPLICATION_JSON,
            consumes = MediaType.APPLICATION_JSON)
    @Operation(
            summary = "Update a travel package ",
            description = "Update a travel package",
            tags = {"TravelPackages"},
            responses = {
                    @ApiResponse(description = "Updated", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = TravelPackageDto.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized ", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            }
    )
    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN', 'SCOPE_EMPRESA')")
    public TravelPackageDto update(@RequestBody TravelPackageDto travelPackageDto, JwtAuthenticationToken token) {
        return travelPackageService.update(travelPackageDto, token);
    }

    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN', 'SCOPE_EMPRESA')")
    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete a travel package",
            description = "Delete a travel package by id",
            tags = {"TravelPackages"},
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204",
                            content = @Content
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized ", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            }
    )
    public ResponseEntity<?> delete(@PathVariable Long id, JwtAuthenticationToken token) {
        return travelPackageService.delete(id, token);
    }
}