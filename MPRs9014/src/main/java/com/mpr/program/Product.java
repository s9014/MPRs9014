package com.mpr.program;

import java.math.BigDecimal;

public class Product {
	
	private String name;
	private BigDecimal price;
	private int storeHouseId;
	
	
	public Product(String name, BigDecimal price) {
		this.name=name;
		this.price=price;
		this.storeHouseId=0;
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

	public int getStoreHouseId() {
		return storeHouseId;
	}

	public void setStoreHouseId(int storeHouseId) {
		this.storeHouseId = storeHouseId;
	}
	
	



}
