package com.tropical.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "tb_travel_packages")
public class TravelPackage {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "travel_package_id")
	private Long id;

	@Column(length = 45, nullable = false)
	private String destiny;

	@Column(length = 150, nullable = false)
	private String description;

	@Column(length = 150, nullable = false)
	private String category;

	@Column( nullable = false)
	private int days;

	@Column(nullable = false)
	private String image;

	@Column(nullable = false)
	@NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
	private BigDecimal price;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "enterprise_id", nullable = false)
	@JsonBackReference
	private Enterprise enterprise;

	@OneToMany(mappedBy = "travelPackage",cascade = CascadeType.ALL)
	@JsonIgnore
	List<Reserve> reserves=new ArrayList<>();

	public TravelPackage() {

	}

	public TravelPackage(Long id, String destiny, String description, String category, int days, String image, BigDecimal price, Enterprise enterprise, List<Reserve> reserves) {
		this.id = id;
		this.destiny = destiny;
		this.description = description;
		this.category = category;
		this.days = days;
		this.image = image;
		this.price = price;
		this.enterprise = enterprise;
		this.reserves = reserves;
	}

	public TravelPackage(TravelPackage travelPackage) {
		this.id = travelPackage.getId();
		this.destiny = travelPackage.destiny;
		this.description = travelPackage.getDescription();
		this.category = travelPackage.getCategory();
		this.days = travelPackage.getDays();
		this.image = travelPackage.getImage();
		this.price = travelPackage.getPrice();
		this.enterprise = travelPackage.getEnterprise();
		this.reserves = travelPackage.getReserves();
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

	public List<Reserve> getReserves() {
		return reserves;
	}

	public void setReserves(List<Reserve> reserves) {
		this.reserves = reserves;
	}
}
