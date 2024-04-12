package com.tropical.data.dto;

import java.util.Date;
import java.util.List;

import com.tropical.model.User;

public record ClienteDTO( String nome, String telefone, Date dataNascimento,String cep,User user) {

	

}
