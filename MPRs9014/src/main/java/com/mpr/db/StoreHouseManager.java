package com.mpr.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mpr.entities.Product;
import com.mpr.entities.StoreHouse;

public class StoreHouseManager {
	
	
	
	private PreparedStatement addStorehouseStmt;
	private PreparedStatement getAllStorehousesStmt;
	private PreparedStatement getStorehouseByNameStmt;
	private PreparedStatement getStoreHouseProductsStmt;
	private PreparedStatement setProductToStoreHouseStmt;
	private PreparedStatement deleteStoreHouseStmt;
	
	
	public StoreHouseManager(Connection connection) throws SQLException {
		addStorehouseStmt=connection.prepareStatement("INSERT INTO storehouse (name) VALUES (?)");
		getAllStorehousesStmt=connection.prepareStatement("SELECT * FROM storehouse");
		getStorehouseByNameStmt=connection.prepareStatement("SELECT * FROM storehouse WHERE (name) = (?) LIMIT 1");
		getStoreHouseProductsStmt = connection.prepareStatement("SELECT * FROM product WHERE storehouse_id=(?)");
		setProductToStoreHouseStmt=connection.prepareStatement("UPDATE product SET storehouse_id=(?) WHERE (name) =(?)");
		deleteStoreHouseStmt=connection.prepareStatement("DELETE FROM storehouse WHERE name =(?)");
		
	}
	
	public void addStoreHouse(StoreHouse storeHouse) throws SQLException{
				
			addStorehouseStmt.setString(1, storeHouse.getName());
			addStorehouseStmt.executeUpdate();	
	}
	
	public List<StoreHouse> getAllStorehouses() throws SQLException{
		List<StoreHouse> list = new ArrayList<StoreHouse>();
				
			ResultSet result = getAllStorehousesStmt.executeQuery();			
			while(result.next()){
				StoreHouse storeHouse = new StoreHouse(result.getString("name"));
				storeHouse.setId(result.getInt("id"));
				getStoreHouseProductsStmt.setLong(1, storeHouse.getId());
				ResultSet rs= getStoreHouseProductsStmt.executeQuery();
				while(rs.next()){
					
					Product product = new Product(rs.getString("name"), rs.getBigDecimal("price"));
					product.setStoreHouseId(storeHouse.getId());
					storeHouse.getProducts().add(product);
				}
				list.add(storeHouse);			
			}		
		return list;
	}
	
	public StoreHouse getStorehouseByName(String name) throws SQLException{
		StoreHouse storeHouse = null;
		getStorehouseByNameStmt.setString(1, name);
		ResultSet result = getStorehouseByNameStmt.executeQuery();
		if(result.next()){			
			storeHouse = new StoreHouse(result.getString("name"));
			storeHouse.setId(result.getInt("id"));
			getStoreHouseProductsStmt.setLong(1, storeHouse.getId());
			ResultSet rs= getStoreHouseProductsStmt.executeQuery();
			while(rs.next()){
				Product product = new Product(rs.getString("name"), rs.getBigDecimal("price"));
				product.setStoreHouseId(storeHouse.getId());
				storeHouse.getProducts().add(product);
			}
			
		}	
		return storeHouse;
	}
	
	public void setProductToStoreHouse(Product product, StoreHouse storeHouse) throws SQLException{
		storeHouse.getProducts().add(product);
		setProductToStoreHouseStmt.setLong(1, storeHouse.getId());
		setProductToStoreHouseStmt.setString(2, product.getName());
		setProductToStoreHouseStmt.executeUpdate();
	}
	
	public void deleteStoreHouse(StoreHouse storeHouse) throws SQLException{
		deleteStoreHouseStmt.setString(1, storeHouse.getName());
		deleteStoreHouseStmt.executeUpdate();
	}

}
