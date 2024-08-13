package com.tropical.model;

import java.time.ZonedDateTime;
import java.util.Date;

import jakarta.persistence.*;

@Entity
@Table(name="tb_reservas")
public class Reserva {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "reserva_id")
	private Long reservaId;
	@Column(name="data_reserva")
	private ZonedDateTime dataReserva;
	@Column(name="data_viagem",nullable = false)
	private Date dataViagem;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="cliente_id_fk",nullable = false)
	private Cliente cliente;
	@ManyToOne
	@JoinColumn(name = "pacote_id_fk",nullable = false)
	private PacoteDeViagem pacote;
	
	
	
	
	public Reserva(Long reservaId, ZonedDateTime dataReserva, Date dataViagem, Cliente cliente, PacoteDeViagem pacote) {
		this.reservaId = reservaId;
		this.dataReserva = dataReserva;
		this.dataViagem = dataViagem;
		this.cliente = cliente;
		this.pacote = pacote;
	}
	
	public Reserva() {
		
	}

	public Long getReservaId() {
		return reservaId;
	}
	public void setReservaId(Long reservaId) {
		this.reservaId = reservaId;
	}

	public ZonedDateTime getDataReserva() {
		return dataReserva;
	}

	public void setDataReserva(ZonedDateTime dataReserva) {
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
