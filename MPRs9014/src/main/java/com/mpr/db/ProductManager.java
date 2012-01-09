package com.mpr.db;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mpr.program.Product;

public class ProductManager {


	private PreparedStatement addProductStmt;
	private PreparedStatement getAllProductsStmt;
	private PreparedStatement getProductByNameStmt;
	private PreparedStatement getProductsByPriceStmt;
	private PreparedStatement setProductPriceStmt;
	private PreparedStatement deleteProductStmt;
	

	public ProductManager(Connection connection) throws SQLException {

		addProductStmt = connection
				.prepareStatement("INSERT INTO product (name,price) VALUES (?,?)");
		
		getAllProductsStmt=connection.prepareStatement("SELECT * FROM product");
		
		getProductByNameStmt=connection.prepareStatement("SELECT * FROM product WHERE (name) = (?) LIMIT 1");
		
		getProductsByPriceStmt=connection.prepareStatement("SELECT * FROM product WHERE (price) = (?)");
		
		setProductPriceStmt=connection.prepareStatement("UPDATE product SET price=(?) WHERE (name) =(?)");
		
		deleteProductStmt=connection.prepareStatement("DELETE FROM product WHERE name = (?)");
	}

	public void addProduct(Product product) throws SQLException {
		addProductStmt.setString(1, product.getName());
		addProductStmt.setBigDecimal(2, product.getPrice());
		addProductStmt.executeUpdate();
	}
	
	public List<Product> getAllProducts() throws SQLException {
		List<Product> list = new ArrayList<Product>();
		ResultSet result=getAllProductsStmt.executeQuery();
		while(result.next()){
			Product product = new Product(result.getString("name"), result.getBigDecimal("price"));
			product.setStoreHouseId(result.getInt("storehouse_id"));
			list.add(product);
		}
		
		
		return list;
	}
	
	public Product getProductByName(String name) throws SQLException {
		Product product = null;
		getProductByNameStmt.setString(1, name);
		ResultSet result=getProductByNameStmt.executeQuery();
		if(result.next()){		
			product = new Product(name, result.getBigDecimal("price"));
			product.setStoreHouseId(result.getInt("storehouse_id"));
		}
		
		return product;
	}

	public List<Product> getProductsByPrice(BigDecimal price) throws SQLException {
		List<Product> list = new ArrayList<Product>();
		getProductsByPriceStmt.setBigDecimal(1, price);
		ResultSet result=getProductsByPriceStmt.executeQuery();
		while(result.next()){
			Product product = new Product(result.getString("name"), price);
			product.setStoreHouseId(result.getInt("storehouse_id"));
			list.add(product);
		}
		
		
		return list;
	}

	public void setProductPrice(String name, BigDecimal price) throws SQLException {
		setProductPriceStmt.setBigDecimal(1, price);
		setProductPriceStmt.setString(2, name);
		setProductPriceStmt.executeUpdate();
		
	}
	
	public void deleteProduct(Product product) throws SQLException{
		deleteProductStmt.setString(1, product.getName());
		deleteProductStmt.executeUpdate();
	}
	

}
