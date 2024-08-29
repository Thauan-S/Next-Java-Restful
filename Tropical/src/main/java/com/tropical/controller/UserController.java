package com.tropical.controller;

import com.tropical.data.dto.ClientDto;
import com.tropical.data.dto.CreateUserDto;
import com.tropical.data.dto.EnterpriseDto;
import com.tropical.data.dto.UserDto;
import com.tropical.model.User;
import com.tropical.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/register")
@Tag(name = "Users", description = "Endpoint for managing users")
public class UserController {

    @Autowired
    UserService userService;


    @PostMapping("/user")
    @Operation(
            summary = "Registers a  user",
            description = "Registers a  user",
            tags = {"Users"},
            responses = {
                    @ApiResponse(description = "Created", responseCode = "200", content = @Content),
                    @ApiResponse(description = "No Content", responseCode = "204",
                            content = @Content
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            }
    )
    public ResponseEntity<Void> registerUser(@RequestBody CreateUserDto createUserDto) {
        return userService.newUser(createUserDto);
    }

    @Transactional
    @PostMapping("/admin")
    @Operation(
            summary = "Create an administrator user",
            description = "Create an administrator user",
            tags = {"Users"},
            responses = {
                    @ApiResponse(description = "Created", responseCode = "200", content = @Content),
                    @ApiResponse(description = "No Content", responseCode = "204",
                            content = @Content
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            }
    )
    //remover
    public ResponseEntity<Void> createAdmin(@RequestBody CreateUserDto createUserDto) {
        return userService.createNewAdmin(createUserDto);
    }

    @PostMapping("/enterprise")
    @Operation(
            summary = "Registers  user of type  Enterprise",
            description = "Registers  user of type  Enterprise",
            tags = {"Users"},
            responses = {
                    @ApiResponse(description = "Created", responseCode = "200", content = @Content),
                    @ApiResponse(description = "No Content", responseCode = "204",
                            content = @Content
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            }
    )
    public ResponseEntity<Void> registerEnterprise(@RequestBody EnterpriseDto enterpriseDto) {
        return userService.createNewEnterprise(enterpriseDto);
    }

    @PostMapping("/client")
    @Operation(
            summary = "Register a user of type client",
            description = "Register a user of type client",
            tags = {"Users"},
            responses = {
                    @ApiResponse(description = "Created", responseCode = "200", content = @Content),
                    @ApiResponse(description = "No Content", responseCode = "204",
                            content = @Content
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            }
    )
    public ResponseEntity<Void> registerCustomer(@RequestBody ClientDto clientDto) {
        return userService.newClient(clientDto);
    }

    @GetMapping("/users")
    @Operation(
            summary = "Searches all users only if you have administrator permission",
            description = "Searches all users only if you have administrator permission",
            tags = {"Users"},
            responses = {
                    @ApiResponse(description = "Created", responseCode = "200", content = @Content),
                    @ApiResponse(description = "No Content", responseCode = "204",
                            content = @Content
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            }
    )
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<List<UserDto>> listAllUsers() {
        return userService.listUsers();
    }
}
