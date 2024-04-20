package com.tropical.data.dto;

import java.util.Date;

import com.tropical.model.Cliente;
import com.tropical.model.User;

public class ClienteDTOO {
	
	Long clienteId;
	
	String nome;
	
	String telefone;
	
	Date dataNascimento;
	
	String cep;
	
	User user;


	

//	public ClienteDTOO(Cliente cliente) {
//		this.clienteId = cliente.getClienteId();
//		this.nome = cliente.getNome();
//		this.telefone = cliente.getTelefone();
//		this.dataNascimento = cliente.getDataNascimento();
//		this.cep = cliente.getCep();
//		this.user = cliente.getUser();
//	}

	public ClienteDTOO(Cliente cliente) {
		this.clienteId = cliente.getClienteId();
		this.nome = cliente.getNome();
		this.telefone = cliente.getTelefone();
		this.dataNascimento = cliente.getDataNascimento();
		this.cep = cliente.getCep();
		this.user = cliente.getUser();
	}

	public ClienteDTOO() {
		
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getClienteId() {
		return clienteId;
	}

	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}

	
	
	
	
}
