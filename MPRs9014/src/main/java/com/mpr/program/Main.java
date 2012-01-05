package com.mpr.program;

import java.sql.SQLException;

import com.mpr.db.DBManager;
import com.mpr.db.ProductManager;
import com.mpr.db.StoreHouseManager;

public class Main {

	/**
	 * @param args
	 * @throws SQLException 
	 */
	
	public Main() throws SQLException {
		DBManager db = new DBManager();
		StoreHouseManager sm = new StoreHouseManager(db.getConnection());
		ProductManager pm = new ProductManager(db.getConnection());
	}
	
	public static void main(String[] args) {
		try {
			new Main();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	

}
