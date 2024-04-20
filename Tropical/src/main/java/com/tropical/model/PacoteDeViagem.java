package com.tropical.model;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity(name = "pacote_de_viagem")
public class PacoteDeViagem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pacote")
	private Long id;

	@Column(length = 45, nullable = false)
	private String destino;

	@Column(length = 150, nullable = false)
	private String descricao;

	@Column(length = 150, nullable = false)
	private String categoria;

	@Column(name = "duracao_dias", nullable = false)
	private int duracaoEmDias;

	@Column(nullable = false, length = 300)
	private String imagem;

	@Column(nullable = false)
	@NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
	private BigDecimal preco;
	
	@ManyToOne
	@JoinColumn(name="empresa_id")
	private Empresa empresa;
	
	@OneToMany
	private List<Reserva>reservas;
	

	public PacoteDeViagem() {

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

	
}
