package com.tropical.data.vo.v1;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
@JsonPropertyOrder({"id","cliente","dataReserva","dataViagem","pacoteDeViagem"})
public class ReservaVO extends RepresentationModel<ReservaVO> implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@JsonProperty("id")
	private Long key;
	private LocalDateTime dataReserva;

	private Date dataViagem;

	private ClienteVO cliente;

	private PacoteDeViagemVO pacoteDeViagem;

	public Long getKey() {
		return key;
	}

	public void setKey(Long key) {
		this.key = key;
	}

	public LocalDateTime getDataReserva() {
		return dataReserva;
	}

	public void setDataReserva(LocalDateTime dataReserva) {
		this.dataReserva = dataReserva;
	}

	public Date getDataViagem() {
		return dataViagem;
	}

	public void setDataViagem(Date dataViagem) {
		this.dataViagem = dataViagem;
	}

	public ClienteVO getCliente() {
		return cliente;
	}

	public void setCliente(ClienteVO cliente) {
		this.cliente = cliente;
	}

	public PacoteDeViagemVO getPacoteDeViagem() {
		return pacoteDeViagem;
	}

	public void setPacoteDeViagem(PacoteDeViagemVO pacoteDeViagem) {
		this.pacoteDeViagem = pacoteDeViagem;
	}

}
