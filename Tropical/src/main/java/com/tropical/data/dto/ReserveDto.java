package com.tropical.data.dto;


import com.tropical.model.Client;
import com.tropical.model.Reserve;
import com.tropical.model.TravelPackage;
import org.springframework.data.domain.Page;

import java.time.ZonedDateTime;
import java.util.Date;


public class ReserveDto {

    private Long reserveId;

    private ZonedDateTime creationDate;

    private Date travelDate;

    private Client client;

    private TravelPackage travelPackage;

    public ReserveDto(Long reserveId, ZonedDateTime creationDate, Date travelDate, Client client, TravelPackage travelPackage) {
        this.reserveId = reserveId;
        this.creationDate = creationDate;
        this.travelDate = travelDate;
        this.client = client;
        this.travelPackage = travelPackage;
    }

    public ReserveDto(Reserve reserve) {
        this.reserveId = reserve.getReserveId();
        this.creationDate = reserve.getCreationDate();
        this.travelDate = reserve.getTravelDate();
        this.client = reserve.getClient();
        this.travelPackage = reserve.getTravelPackage();
    }

    public static Page<ReserveDto> listReserves(Page<Reserve> reserves) {
        return reserves.map(ReserveDto::new);
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
