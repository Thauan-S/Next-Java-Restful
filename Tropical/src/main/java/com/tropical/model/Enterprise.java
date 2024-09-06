package com.tropical.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


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
	

	@OneToMany(mappedBy = "enterprise")
	@JsonManagedReference
	private List<TravelPackage> travelPackage= new ArrayList<>();

	


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
