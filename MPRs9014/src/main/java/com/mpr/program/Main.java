package com.mpr.program;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import com.mpr.db.DBManager;
import com.mpr.db.ProductManager;
import com.mpr.db.StoreHouseManager;
import com.mpr.entities.Product;
import com.mpr.entities.StoreHouse;

public class Main {
	
	private Main() throws SQLException {
		
		DBManager db = new DBManager();
		StoreHouseManager sm = new StoreHouseManager(db.getConnection());
		ProductManager pm = new ProductManager(db.getConnection());
		
		System.out.println("Creating Storehouses");
		
		StoreHouse sh1 = new StoreHouse("First StoreHouse");
		StoreHouse sh2 = new StoreHouse("Second StoreHouse");
		StoreHouse sh3 = new StoreHouse("Third StoreHouse");
		StoreHouse sh4 = new StoreHouse("Fourth StoreHouse");
		
		System.out.println("Creating Products");
		
		Product p1 = new Product("Processor", new BigDecimal(324.11));
		Product p2 = new Product("Motherboard", new BigDecimal(222.22));
		Product p3 = new Product("Graphics Card", new BigDecimal(554.43));
		Product p4 = new Product("HDD", new BigDecimal(222.22));
		Product p5 = new Product("Sound Card", new BigDecimal(243.00));
		Product p6 = new Product("RAM", new BigDecimal(124.33));
		Product p7 = new Product("PSU", new BigDecimal(366.63));
		Product p8 = new Product("Case", new BigDecimal(178.45));
		
		pm.addProduct(p1);
		pm.addProduct(p2);
		pm.addProduct(p3);
		pm.addProduct(p4);
		pm.addProduct(p5);
		pm.addProduct(p6);
		pm.addProduct(p7);
		pm.addProduct(p8);
		
		sm.addStoreHouse(sh1);
		sh1=sm.getStorehouseByName(sh1.getName());
		sm.setProductToStoreHouse(p1, sh1);
		sm.setProductToStoreHouse(p2, sh1);
		
		sm.addStoreHouse(sh2);
		sh2=sm.getStorehouseByName(sh2.getName());
		sm.setProductToStoreHouse(p3, sh2);
		sm.setProductToStoreHouse(p4, sh2);
		sm.setProductToStoreHouse(p5, sh2);
		
		sm.addStoreHouse(sh3);
		sh3=sm.getStorehouseByName(sh3.getName());
		sm.setProductToStoreHouse(p6, sh3);
		sm.setProductToStoreHouse(p7, sh3);
		
		sm.addStoreHouse(sh4);
		sh4=sm.getStorehouseByName(sh4.getName());
		sm.setProductToStoreHouse(p8, sh4);
		
		System.out.println();
		

		printAll(sm.getAllStorehouses());	
		
		System.out.println("Removed last product from storehouse id=2 and added to storehouse id=3");
		p5=pm.getProductByName(p5.getName());
		sm.setProductToStoreHouse(p5, sh4);
		
		printAll(sm.getAllStorehouses());
	}
	
	public static void main(String[] args) {
		try {
			new Main();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void printAll(List<StoreHouse> storeHouses){
		for(StoreHouse s: storeHouses){
			System.out.println("------StoreHouse: id: "+s.getId()+"\t name: "+s.getName());
			for(Product p: s.getProducts()){
				System.out.println("--Product: name: "+p.getName()+"\t price: "+p.getPrice());
			}
			System.out.println();
		}
	}
	
	

}
