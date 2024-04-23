package com.tropical.data.dto;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tropical.model.Empresa;
import com.tropical.model.PacoteDeViagem;


public class PacoteDeViagemDto {
	private Long id;

	private String destino;

	private String descricao;

	private String categoria;

	private int duracaoEmDias;

	private String imagem;

	private BigDecimal preco;
	
	 
	private Empresa empresa;
	


	public PacoteDeViagemDto() {

	}

	
	
	
	public PacoteDeViagemDto(Long id, String destino, String descricao, String categoria, int duracaoEmDias,
			String imagem, BigDecimal preco, Empresa empresa) {
		this.id = id;
		this.destino = destino;
		this.descricao = descricao;
		this.categoria = categoria;
		this.duracaoEmDias = duracaoEmDias;
		this.imagem = imagem;
		this.preco = preco;
		this.empresa = empresa;
	}
	

    public PacoteDeViagemDto(PacoteDeViagem pacote) {
		this.id = pacote.getId();
		this.destino = pacote.getDestino();
		this.descricao = pacote.getDescricao();
		this.categoria = pacote.getCategoria();
		this.duracaoEmDias = pacote.getDuracaoEmDias();
		this.imagem = pacote.getImagem();
		this.preco = pacote.getPreco();
		this.empresa = pacote.getEmpresa();
	}
    
	public Long getId() {
		return id;
	}




	public void setId(Long id) {
		this.id = id;
	}




	public String getDestino() {
		return destino;
	}




	public void setDestino(String destino) {
		this.destino = destino;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public String getCategoria() {
		return categoria;
	}


	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}


	public int getDuracaoEmDias() {
		return duracaoEmDias;
	}


	public void setDuracaoEmDias(int duracaoEmDias) {
		this.duracaoEmDias = duracaoEmDias;
	}


	public String getImagem() {
		return imagem;
	}


	public void setImagem(String imagem) {
		this.imagem = imagem;
	}


	public BigDecimal getPreco() {
		return preco;
	}


	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	 
	public Empresa getEmpresa() {
		return empresa;
	}

	
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}




	public static Page<PacoteDeViagemDto> listaPacotes(Page<PacoteDeViagem> pacotes) {
		
		return pacotes.map(PacoteDeViagemDto::new);
	}




	

	
}
