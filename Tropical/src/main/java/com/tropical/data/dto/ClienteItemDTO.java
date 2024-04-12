package com.tropical.data.dto;

import java.util.Date;

import com.tropical.model.User;

public record ClienteItemDTO(Long clienteId, String nome, String telefone,Date dataNascimento, String cep, User user) {

}
