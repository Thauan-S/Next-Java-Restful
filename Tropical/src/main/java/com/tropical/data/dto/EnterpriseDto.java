package com.tropical.data.dto;

import java.util.List;

import com.tropical.model.Enterprise;
import org.springframework.data.domain.Page;

import com.tropical.model.TravelPackage;
import com.tropical.model.User;



public class EnterpriseDto {
	
	private Long enterpriseId;
	private String name;
	private String cnpj;
	
	private String address;
	
	private User user;
	
	private List<TravelPackage> travelPackage;

	public EnterpriseDto(Long enterpriseId, String name, String cnpj, String address, User user,
						 List<TravelPackage> travelPackage) {
		this.enterpriseId = enterpriseId;
		this.name = name;
		this.cnpj = cnpj;
		this.address = address;
		this.user = user;
		this.travelPackage = travelPackage;
	}
	public EnterpriseDto() {

	}
	public EnterpriseDto(Enterprise enterprise) {
		this.enterpriseId = enterprise.getEnterpriseId();
		this.name = enterprise.getName();
		this.cnpj = enterprise.getCnpj();
		this.address = enterprise.getAddress();
		this.user = enterprise.getUser();
		this.travelPackage = enterprise.getTravelPackage();
	}


	

	public static Page<EnterpriseDto> enterpriseList(Page<Enterprise> empresas) {
	  return  empresas.map(EnterpriseDto::new);
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

	public List<TravelPackage> getTravelPackage() {
		return travelPackage;
	}

	public void setTravelPackage(List<TravelPackage> travelPackage) {
		this.travelPackage = travelPackage;
	}
}
