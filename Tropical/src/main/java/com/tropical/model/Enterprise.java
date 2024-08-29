package com.tropical.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "tb_companies")
public class Enterprise {
	@Id
	@Column(name="enterprise_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long enterpriseId;
	@Column(name = "name")
	private String name;
	@Column(length = 14 ,unique = true)
	private String cnpj;
	
	private String address;
	@OneToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "enterprise" ,fetch = FetchType.LAZY)
	private List<TravelPackage> travelPackage;

	


	public Enterprise() {
	}

	public Enterprise(Long enterpriseId, String name, String cnpj, String address, User user, List<TravelPackage> travelPackage) {
		this.enterpriseId = enterpriseId;
		this.name = name;
		this.cnpj = cnpj;
		this.address = address;
		this.user = user;
		this.travelPackage = travelPackage;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCnpj() {
		return cnpj;
	}

	public List<TravelPackage> getTravelPackage() {
		return travelPackage;
	}

	public void setTravelPackage(List<TravelPackage> travelPackage) {
		this.travelPackage = travelPackage;
	}
}
