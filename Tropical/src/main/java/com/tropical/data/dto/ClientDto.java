package com.tropical.data.dto;

import com.tropical.model.Client;
import com.tropical.model.User;

import java.util.Date;

public class ClientDto {

    Long clientId;

    String name;

    String phone;

    Date birthday;

    String zipCode;

    User user;

    public ClientDto() {
    }

    public ClientDto(Long clientId, String name, String phone, Date birthday, String zipCode, User user) {
        this.clientId = clientId;
        this.name = name;
        this.phone = phone;
        this.birthday = birthday;
        this.zipCode = zipCode;
        this.user = user;
    }
    public ClientDto(Client client) {
        this.clientId = client.getClientId();
        this.name = client.getName();
        this.phone = client.getPhone();
        this.birthday = client.getBirthday();
        this.zipCode = client.getZipCode();
        this.user = client.getUser();

    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
