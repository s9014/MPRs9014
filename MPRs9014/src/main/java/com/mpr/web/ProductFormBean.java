package com.mpr.web;

import java.io.Serializable;
import java.sql.SQLException;

import javax.enterprise.context.SessionScoped;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;

import com.mpr.db.ProductManager;
import com.mpr.entities.Product;


@SessionScoped
@Named("productBean")
public class ProductFormBean implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	private Product product = new Product();
	private ListDataModel<Product> products = new ListDataModel<Product>();
	
	@Inject
	private ProductManager pm;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public ListDataModel<Product> getProducts() {
		try {
			products.setWrappedData(pm.getAllProducts());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return products;
	}
	
	
	public String addProduct()  {
		try {
			pm.addProduct(product);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "showProducts";
	}

	public String deleteProduct() {
		Product p = products.getRowData();
		try {
			pm.deleteProduct(p);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "showProducts";
	}

}
