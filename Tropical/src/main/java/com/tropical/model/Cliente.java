package com.tropical.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "clientes")
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cliente")
	private Long id;
	@Column(length = 50, nullable = false,unique = true)
	private String nome;
	@Column(length = 50, nullable = false, unique = true)
	private String email;
	@Column( length= 255, nullable = false)
	private String senha;
	@Column(length = 50, nullable = false)
	private String telefone;
	@Column(length = 50, nullable = false)
	private String cep;
	@Column(name = "data_nascimento", length = 50)
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate dataNascimento;
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	   private List<Contato> contatos;
	@OneToMany(mappedBy = "cliente",cascade =CascadeType.ALL,fetch = FetchType.LAZY)
	private List<Reserva>reservas;

	public List<Contato> getContatos() {
		return contatos;
	}

	public void setContatos(List<Contato> contatos) {
		this.contatos = contatos;
	}

	public String getNome() {
		return nome;
	}

	public Cliente() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public List<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cep, contatos, dataNascimento, email, id, nome, reservas, senha, telefone);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(cep, other.cep) && Objects.equals(contatos, other.contatos)
				&& Objects.equals(dataNascimento, other.dataNascimento) && Objects.equals(email, other.email)
				&& Objects.equals(id, other.id) && Objects.equals(nome, other.nome)
				&& Objects.equals(reservas, other.reservas) && Objects.equals(senha, other.senha)
				&& Objects.equals(telefone, other.telefone);
	}




	
	
}
