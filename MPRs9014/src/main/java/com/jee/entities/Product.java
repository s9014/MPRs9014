package com.jee.entities;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Size(min=1,max=200)
	private String name;
	private BigDecimal price;
	private long storeHouseId;

	public Product() {
	}

	public Product(String name, BigDecimal price) {
		this.name = name;
		this.price = price;
		this.storeHouseId = 0;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public long getStoreHouseId() {
		return storeHouseId;
	}

	public void setStoreHouseId(long l) {
		this.storeHouseId = l;
	}

}
