package com.tropical.data.vo.v1;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
@JsonPropertyOrder({"id","destino","descricao","categoria","duracaoEmDias","imagem","preco"})
public class PacoteDeViagemVO extends RepresentationModel<PacoteDeViagemVO> implements Serializable {

	private static final long serialVersionUID = 1L;
	@JsonProperty("id")
	private Long key;
	private String destino;
	private String descricao;
	private String categoria;
	private int duracaoEmDias;
	private String imagem;
	private BigDecimal preco;
	

	public PacoteDeViagemVO() {

	}

	public Long getKey() {
		return key;
	}

	public void setKey(Long key) {
		this.key = key;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getDuracaoEmDias() {
		return duracaoEmDias;
	}

	public void setDuracaoEmDias(int duracaoEmDias) {
		this.duracaoEmDias = duracaoEmDias;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(categoria, descricao, destino, duracaoEmDias, imagem, key, preco);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		PacoteDeViagemVO other = (PacoteDeViagemVO) obj;
		return Objects.equals(categoria, other.categoria) && Objects.equals(descricao, other.descricao)
				&& Objects.equals(destino, other.destino) && duracaoEmDias == other.duracaoEmDias
				&& Objects.equals(imagem, other.imagem) && Objects.equals(key, other.key)
				&& Objects.equals(preco, other.preco);
	}

	
	

}
