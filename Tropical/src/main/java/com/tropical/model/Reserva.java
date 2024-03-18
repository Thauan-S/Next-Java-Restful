package com.tropical.model;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity(name = "reservas")
public class Reserva {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_reserva")
	private Long id;
	@Column(nullable = false)
	private LocalDateTime dataReserva;

	@Column(nullable = false)
	@DateTimeFormat(iso = ISO.DATE )
	private Date dataViagem;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cliente_id_fk", nullable = false)
	private Cliente cliente;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "pacote_id_fk", nullable = false)
	private PacoteDeViagem pacoteDeViagem;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public PacoteDeViagem getPacoteDeViagem() {
		return pacoteDeViagem;
	}

	public void setPacoteDeViagem(PacoteDeViagem pacoteDeViagem) {
		this.pacoteDeViagem = pacoteDeViagem;
	}

}
