package com.tropical.model;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.*;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "tb_pacote_de_viagem")
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

	@Column(nullable = false)
	private String imagem;

	@Column(nullable = false)
	@NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
	private BigDecimal preco;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "empresa_id", nullable = false)
	@JsonBackReference// geralmente do lado many
	private Empresa empresa;

	@OneToMany(mappedBy = "pacote",cascade = CascadeType.ALL)
	List<Reserva> reservas;

	public PacoteDeViagem() {

	}

	public PacoteDeViagem(Long id, String destino, String descricao, String categoria, int duracaoEmDias, String imagem,
			BigDecimal preco, Empresa empresa) {
		this.id = id;
		this.destino = destino;
		this.descricao = descricao;
		this.categoria = categoria;
		this.duracaoEmDias = duracaoEmDias;
		this.imagem = imagem;
		this.preco = preco;
		this.empresa = empresa;
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
