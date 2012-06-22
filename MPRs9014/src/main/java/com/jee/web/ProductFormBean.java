package com.jee.web;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;

import com.jee.db.ProductManager;
import com.jee.entities.Product;

@SessionScoped
@Named("productBean")
public class ProductFormBean implements Serializable {

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

		products.setWrappedData(pm.getAllProducts());

		return products;
	}

	public String addProduct() {

		pm.addProduct(product);

		return "showProducts";
	}

	public String deleteProduct() {
		Product p = products.getRowData();
		pm.deleteProduct(p);

		return "showProducts";
	}

}
