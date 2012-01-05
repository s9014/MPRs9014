package com.mpr.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mpr.program.StoreHouse;

public class StoreHouseManager {
	
	
	private PreparedStatement addStorehouseStmt;
	private PreparedStatement getAllStorehousesStmt;
	private PreparedStatement getStorehouseByNameStmt;
	
	public StoreHouseManager(Connection connection) throws SQLException {
		addStorehouseStmt=connection.prepareStatement("INSERT INTO storehouse (name) VALUES (?)");
		getAllStorehousesStmt=connection.prepareStatement("SELECT * FROM storehouse");
		getStorehouseByNameStmt=connection.prepareStatement("SELECT * FROM storehouse WHERE (name) = (?) LIMIT 1");
	}
	
	public void addStoreHouse(StoreHouse storeHouse) throws SQLException{
				
			addStorehouseStmt.setString(1, storeHouse.getName());
			addStorehouseStmt.executeUpdate();	
	}
	
	public List<StoreHouse> getAllStorehouses() throws SQLException{
		List<StoreHouse> list = new ArrayList<StoreHouse>();
				
			ResultSet result = getAllStorehousesStmt.executeQuery();			
			while(result.next()){			
				list.add(new StoreHouse(result.getString("name")));			
			}		
		return list;
	}
	
	public StoreHouse getStorehouseByName(String name) throws SQLException{
		StoreHouse storeHouse = null;
		getStorehouseByNameStmt.setString(1, name);
		ResultSet result = getStorehouseByNameStmt.executeQuery();
		if(result.next()){			
			storeHouse = new StoreHouse(result.getString("name"));			
		}	
		return storeHouse;
	}

}
