package com.tropical.data.vo.v1;

import java.io.Serializable;
import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
@JsonPropertyOrder({"id","cliente","assunto","mensagem"})
public class ContatoVO extends RepresentationModel<ContatoVO> implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@JsonProperty("id")
	private Long key;
	private String assunto;
	private String mensagem;
	private ClienteVO cliente;
	

	public ContatoVO() {
		
	}

	public Long getKey() {
		return key;
	}

	public void setKey(Long key) {
		this.key = key;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public ClienteVO getCliente() {
		return cliente;
	}

	public void setCliente(ClienteVO cliente) {
		this.cliente = cliente;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(assunto, cliente, key, mensagem);
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
		ContatoVO other = (ContatoVO) obj;
		return Objects.equals(assunto, other.assunto) && Objects.equals(cliente, other.cliente)
				&& Objects.equals(key, other.key) && Objects.equals(mensagem, other.mensagem);
	}
	
}
