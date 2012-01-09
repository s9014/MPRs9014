package com.mpr.program;

import java.util.ArrayList;
import java.util.List;

public class StoreHouse {
	
	private int id;
	private String name;
	private List<Product> products;
	
	public StoreHouse(String name) {
		products = new ArrayList<Product>();
		this.name = name;
		
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	

}
