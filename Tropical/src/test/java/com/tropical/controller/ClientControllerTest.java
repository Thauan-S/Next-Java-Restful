package com.tropical.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tropical.data.dto.ClientDto;
import com.tropical.data.dto.ClientItemDto;
import com.tropical.data.dto.ClientListDto;
import com.tropical.model.User;
import com.tropical.services.ClientService;
import com.tropical.utils.MediaType;
import com.tropical.utils.MockClient;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;


import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.tropical.utils.MockClient.CLIENT;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ClientController.class)
class ClientControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private ClientService clientService;
    @Test
    @WithMockUser(authorities = "SCOPE_ADMIN")
    void findById() throws Exception {
        when(clientService.findById(1L))
                .thenReturn(ResponseEntity.ok(new ClientDto()));
        mockMvc.perform(get("/api/clients/v1/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @WithMockUser(authorities = "SCOPE_ADMIN")
    void findAll() throws Exception {
        when(clientService.findAll(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyString()))
                .thenReturn(ResponseEntity.ok(new ClientListDto(List.of(new ClientItemDto(1L,"thau","123",new Date(2024-9-17),"45330000",new User())),0,3,10,10)));

        mockMvc.perform(get("/api/clients/v1")
                        .param("page", "0")
                        .param("size", "12")
                        .param("direction", "ASC")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @WithMockUser(authorities = {"SCOPE_ADMIN","SCOPE_BASIC"})
    void update() throws Exception {
        ClientDto clientDto = new ClientDto(); // Configure o ClientDto conforme necessário
        String clientDtoJson = objectMapper.writeValueAsString(clientDto);

        mockMvc.perform(put("/api/clients/v1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(clientDtoJson)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @WithMockUser(authorities = {"SCOPE_ADMIN","SCOPE_BASIC"})
    void delete() {

        // when(clientService.findCategoryById(CATEGORY.getId())).thenReturn(MockClient.ID);
        // when(clientService.findById(CLIENT.getClientId())).thenReturn(ResponseEntity.of(CLIENT));
//        mockMvc
//                .perform(delete("/api/categories/{id}")
//                        .accept(MediaType.APPLICATION_JSON)
//                        .with(csrf()))
//                .andExpect(status().isNoContent());

    }
}