package com.tropical.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "tb_empresa")
public class Empresa {
	@Id
	@Column(name="empresa_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long empresaId;
	@Column(name = "nome_empresa")
	private String nomeEmpresa;
	@Column(length = 14 ,unique = true)
	private String cnpj;
	
	private String endereco;
	@OneToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@OneToMany(mappedBy = "empresa")
	private List<PacoteDeViagem> pacoteDeViagem;

	
	
	
	
	
	public Empresa(Long empresaId, String nomeEmpresa, String cnpj, String endereco, User user,
			List<PacoteDeViagem> pacoteDeViagem) {
		this.empresaId = empresaId;
		this.nomeEmpresa = nomeEmpresa;
		this.cnpj = cnpj;
		this.endereco = endereco;
		this.user = user;
		this.pacoteDeViagem = pacoteDeViagem;
	}

	public Empresa() {
	}


	public Long getEmpresaId() {
		return empresaId;
	}

	public void setEmpresaId(Long empresaId) {
		this.empresaId = empresaId;
	}

	public String getNomeEmpresa() {
		return nomeEmpresa;
	}

	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<PacoteDeViagem> getPacoteDeViagem() {
		return pacoteDeViagem;
	}

	public void setPacoteDeViagem(List<PacoteDeViagem> pacoteDeViagem) {
		this.pacoteDeViagem = pacoteDeViagem;
	}
	
	
}
