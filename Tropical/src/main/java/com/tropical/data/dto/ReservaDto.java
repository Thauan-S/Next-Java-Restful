package com.tropical.data.dto;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;

import com.tropical.model.Cliente;
import com.tropical.model.PacoteDeViagem;
import com.tropical.model.Reserva;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


public class ReservaDto {
	
	private Long reservaId;
	private Instant dataReserva;
	private Date dataViagem;
	
	private Cliente cliente;
	private PacoteDeViagem pacote;
	
	public ReservaDto(Long reservaId, Instant dataReserva, Date dataViagem, Cliente cliente, PacoteDeViagem pacote) {
		this.reservaId = reservaId;
		this.dataReserva = dataReserva;
		this.dataViagem = dataViagem;
		this.cliente = cliente;
		this.pacote = pacote;
	}
	public ReservaDto(Reserva reserva) {
		this.reservaId = reserva.getReservaId();
		this.dataReserva = reserva.getDataReserva();
		this.dataViagem = reserva.getDataViagem();
		this.cliente = reserva.getCliente();
		this.pacote = reserva.getPacote();
	}
	public static Page<ReservaDto> listaReservas(Page<Reserva> reservas) {
		return reservas.map(ReservaDto::new);
	}
	public Long getReservaId() {
		return reservaId;
	}
	public void setReservaId(Long reservaId) {
		this.reservaId = reservaId;
	}
	public Instant getDataReserva() {
		return dataReserva;
	}
	public void setDataReserva(Instant dataReserva) {
		this.dataReserva = dataReserva;
	}
	public Date getDataViagem() {
		return dataViagem;
	}
	public void setDataViagem(Date dataViagem) {
		this.dataViagem = dataViagem;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public PacoteDeViagem getPacote() {
		return pacote;
	}
	public void setPacote(PacoteDeViagem pacote) {
		this.pacote = pacote;
	}
}
