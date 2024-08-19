package com.tropical.data.dto;

import com.tropical.model.Enterprise;
import com.tropical.model.TravelPackage;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;


public class TravelPackageDto {

	private Long id;

	private String destiny;

	private String description;

	private String category;

	private int days;

	private String image;

	private BigDecimal price;
	
	 
	private Enterprise enterprise;
	


	public TravelPackageDto() {

	}

	public TravelPackageDto(Long id, String destiny, String description, String category, int days, String image, BigDecimal price, Enterprise enterprise) {
		this.id = id;
		this.destiny = destiny;
		this.description = description;
		this.category = category;
		this.days = days;
		this.image = image;
		this.price = price;
		this.enterprise = enterprise;
	}

	public TravelPackageDto(TravelPackage travelPackage) {
		this.id = travelPackage.getId();
		this.destiny = travelPackage.getDestiny();
		this.description = travelPackage.getDescription();
		this.category = travelPackage.getCategory();
		this.days = travelPackage.getDays();
		this.image = travelPackage.getImage();
		this.price = travelPackage.getPrice();
		this.enterprise = travelPackage.getEnterprise();
	}


	public static Page<TravelPackageDto> packagesList(Page<TravelPackage> travelPackages) {
		
		return travelPackages.map(TravelPackageDto::new);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDestiny() {
		return destiny;
	}

	public void setDestiny(String destiny) {
		this.destiny = destiny;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Enterprise getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}
}
