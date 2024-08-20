package com.tropical.controller;

import com.tropical.data.dto.EnterpriseDto;
import com.tropical.services.EnterpriseService;
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


@RestController
@RequestMapping("/api/enterprise/v1")
@Tag(name = "Enterprises", description = "Endpoint to manage Enterprises")
public class EnterpriseController {
    @Autowired
    EnterpriseService enterpriseService;

    @GetMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON)

    @Operation(
            summary = "Find a enterprise by id",
            description = "Find a enterprise by id",
            tags = {"Enterprises"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = EnterpriseDto.class))
                    ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized ", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            }
    )
    @PreAuthorize(value = "hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<EnterpriseDto> findById(@PathVariable Long id) {
        return enterpriseService.findById(id);
    }

    @GetMapping(
            produces = MediaType.APPLICATION_JSON)
    @Operation(
            summary = "Find all enterprises",
            description = "Find all enterprises",
            tags = {"Enterprises"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = EnterpriseDto.class))
                            )
                    }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized ", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            }
    )//ok
    @PreAuthorize(value = "hasAuthority('SCOPE_ADMIN')")
    public Page<EnterpriseDto> findAll(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "12") int size,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        return enterpriseService.findAll(page, size, direction);
    }

    @PutMapping(
            produces = MediaType.APPLICATION_JSON,
            consumes = MediaType.APPLICATION_JSON)
    @Operation(
            summary = "Update a  enterprise",
            description = "Update a  enterprise",
            tags = {"Enterprises"},
            responses = {
                    @ApiResponse(description = "Updated", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = EnterpriseDto.class))
                    ),

                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized ", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            }
    )

    @PreAuthorize(value = "hasAnyAuthority('SCOPE_ADMIN', 'SCOPE_EMPRESA')")
    public EnterpriseDto update(@RequestBody EnterpriseDto enterpriseDto, JwtAuthenticationToken token) {
        return enterpriseService.update(enterpriseDto, token);
    }

    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN', 'SCOPE_EMPRESA')")
    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete a enterprise  ",
            description = "Delete a enterprise by id",
            tags = {"Enterprises"},
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
        return enterpriseService.delete(id, token);
    }
}
