package com.tropical.data.dto;

import java.util.Date;

import com.tropical.model.User;

public record ClientItemDto(Long customerId, String name, String phone, Date birthday, String cep, User user) {

}
