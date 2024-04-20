package com.tropical.data.dto;

import java.util.List;

import org.springframework.data.domain.Page;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tropical.model.Empresa;
import com.tropical.model.PacoteDeViagem;
import com.tropical.model.User;



public class EmpresaDTO {
	
	private Long empresaId;
	private String nomeEmpresa;
	private String cnpj;
	
	private String endereco;
	
	private User user;
	
	private List<PacoteDeViagem> pacoteDeViagem;

	public EmpresaDTO(Long empresaId, String nomeEmpresa, String cnpj, String endereco, User user,
			List<PacoteDeViagem> pacoteDeViagem) {
		this.empresaId = empresaId;
		this.nomeEmpresa = nomeEmpresa;
		this.cnpj = cnpj;
		this.endereco = endereco;
		this.user = user;
		this.pacoteDeViagem = pacoteDeViagem;
	}

	public EmpresaDTO(Empresa empresa) {
		this.empresaId = empresa.getEmpresaId();
		this.nomeEmpresa = empresa.getNomeEmpresa();
		this.cnpj = empresa.getCnpj();
		this.endereco = empresa.getEndereco();
		this.user = empresa.getUser();
		this.pacoteDeViagem = empresa.getPacoteDeViagem();
	}


	

	public static Page<EmpresaDTO> listaEmpresas(Page<Empresa> empresas) {
	  return  empresas.map(EmpresaDTO::new);
	    		
	    		
	    		
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
