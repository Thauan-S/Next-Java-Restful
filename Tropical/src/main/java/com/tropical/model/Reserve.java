package com.tropical.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.ZonedDateTime;
import java.util.Date;

@Entity
@Table(name = "tb_reserves")
public class Reserve {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reserve_id")
    private Long reserveId;
    @Column(name = "creation_date")
    private ZonedDateTime creationDate;
    @Column(name = "travel_date", nullable = false)
    private Date travelDate;

    @ManyToOne
    @JoinColumn(name = "client_id_fk")
    @JsonBackReference
    private Client client;
    @ManyToOne
    @JoinColumn(name = "travel_package_id_fk")
    private TravelPackage travelPackage;

    public Reserve() {
    }

    public Reserve(Long reserveId, ZonedDateTime creationDate, Date travelDate, Client client, TravelPackage travelPackage) {
        this.reserveId = reserveId;
        this.creationDate = creationDate;
        this.travelDate = travelDate;
        this.client = client;
        this.travelPackage = travelPackage;
    }

    public Long getReserveId() {
        return reserveId;
    }

    public void setReserveId(Long reserveId) {
        this.reserveId = reserveId;
    }

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(ZonedDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Date getTravelDate() {
        return travelDate;
    }

    public void setTravelDate(Date travelDate) {
        this.travelDate = travelDate;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public TravelPackage getTravelPackage() {
        return travelPackage;
    }

    public void setTravelPackage(TravelPackage travelPackage) {
        this.travelPackage = travelPackage;
    }
}
