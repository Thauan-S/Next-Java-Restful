package com.tropical.integrationtests.vo;

import java.io.Serializable;
import java.util.Objects;
public class ContatoVO  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Long id;
	private String assunto;
	private String mensagem;
	private ClienteVO cliente;
	

	
	public ContatoVO() {}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
		result = prime * result + Objects.hash(assunto, cliente, id, mensagem);
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
				&& Objects.equals(id, other.id) && Objects.equals(mensagem, other.mensagem);
	}

	
	
	
}
