package com.mpr.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.math.MathContext;
import java.sql.SQLException;

import org.junit.BeforeClass;
import org.junit.Test;

import com.mpr.db.DBManager;
import com.mpr.db.ProductManager;
import com.mpr.db.StoreHouseManager;
import com.mpr.entities.Product;
import com.mpr.entities.StoreHouse;

public class DBTest {

	private static DBManager dbm;
	private static StoreHouseManager sm;
	private static ProductManager pm;
	private static StoreHouse sh1;
	private static StoreHouse sh2;
	private static StoreHouse sh3;
	private static StoreHouse sh4;
	private static Product p1;
	private static Product p2;
	private static Product p3;
	private static Product p4;
	private static Product p5;
	private static Product p6;
	private static Product p7;
	private static Product p8;

	@BeforeClass
	public static void setup() throws SQLException {
		sh1 = new StoreHouse("First StoreHouse");
		sh2 = new StoreHouse("Second StoreHouse");
		sh3 = new StoreHouse("Third StoreHouse");
		sh4 = new StoreHouse("Fourth StoreHouse");
		
		p1 = new Product("Processor", new BigDecimal(324.11));
		p2 = new Product("Motherboard", new BigDecimal(222.22));
		p3 = new Product("Graphics Card", new BigDecimal(554.43));
		p4 = new Product("HDD", new BigDecimal(222.22));
		p5 = new Product("Sound Card", new BigDecimal(243.00));
		p6 = new Product("RAM", new BigDecimal(124.33));
		p7 = new Product("PSU", new BigDecimal(366.63));
		p8 = new Product("Case", new BigDecimal(178.45));
		
		
		
		
		
		dbm = new DBManager();
		sm = new StoreHouseManager(dbm.getConnection());
		pm = new ProductManager(dbm.getConnection());

	}

	@Test
	public void TestAddStorehouse() throws SQLException{
		sm.addStoreHouse(sh1);
		sm.addStoreHouse(sh2);
		sm.addStoreHouse(sh3);
		sm.addStoreHouse(sh4);
		assertNotNull(sm.getStorehouseByName(sh1.getName()));
		assertNotNull(sm.getStorehouseByName(sh2.getName()));
		assertNotNull(sm.getStorehouseByName(sh3.getName()));
		assertNotNull(sm.getStorehouseByName(sh4.getName()));
	}
	
	@Test
	public void TestGetAllStorehouses() throws SQLException{
		assertEquals(4,sm.getAllStorehouses().size());
	}
	
	@Test
	public void TestGetStoreHouseByName() throws SQLException{
		String name= "First StoreHouse";
		assertSame(name, sm.getStorehouseByName(name).getName());
	}

	@Test
	public void TestAddProduct() throws SQLException{
		pm.addProduct(p1);
		pm.addProduct(p2);
		pm.addProduct(p3);
		pm.addProduct(p4);
		pm.addProduct(p5);
		pm.addProduct(p6);
		pm.addProduct(p7);
		pm.addProduct(p8);
		
		assertNotNull(pm.getProductByName(p1.getName()));
		assertNotNull(pm.getProductByName(p2.getName()));
		assertNotNull(pm.getProductByName(p3.getName()));
		assertNotNull(pm.getProductByName(p4.getName()));
		assertNotNull(pm.getProductByName(p5.getName()));
		assertNotNull(pm.getProductByName(p6.getName()));
		assertNotNull(pm.getProductByName(p7.getName()));
		assertNotNull(pm.getProductByName(p8.getName()));
		
	}
	
	@Test
	public void TestGetAllProducts() throws SQLException{
		assertEquals(8,pm.getAllProducts().size());
	}
	
	@Test
	public void TestGetProductByName() throws SQLException{
		String name = "Processor";
		assertSame(name,pm.getProductByName(name).getName());	
	}
	
	@Test
	public void TestGetProductsByPrice() throws SQLException{
		BigDecimal price = new BigDecimal(222.22);
		
		/*round aby zgadzala sie liczba miejsc po przecinku z ta w tabeli
		 * w przeciwnym razie assert zwroci falsz
		 * dlatego dla 222.22 ustawiamy 5
		 */
		price=price.round(new MathContext(5));
		assertEquals(2,pm.getProductsByPrice(price).size());
		
	}
	
	@Test
	public void TestSetPrice() throws SQLException{
		BigDecimal price = new BigDecimal(1.20);
		
		/*round aby zgadzala sie liczba miejsc po przecinku z ta w tabeli
		 * w przeciwnym razie assert zwroci falsz
		 * dlatego dla 1.20 ustawiamy 3
		 */
		price=price.round(new MathContext(3));
		pm.setProductPrice(p1.getName(), price);
		assertTrue(price == pm.getProductByName(p1.getName()).getPrice());
		
	}
	
	@Test
	public void TestSetProductStoreHouse() throws SQLException{
		sm.setProductToStoreHouse(p1, sh1);
		assertSame(sh1.getProducts().get(0).getName(),
				sm.getAllStorehouses().get(0).getProducts().get(0).getName());
	}
	
	@Test
	public void TestDeleteStoreHouseStmt() throws SQLException{
		sm.deleteStoreHouse(sh2);
		assertNull(sm.getStorehouseByName(sh2.getName()));
	}
	
	@Test
	public void TestDeleteProduct() throws SQLException{
		pm.deleteProduct(p1);
		assertNull(pm.getProductByName(p1.getName()));
	}
	

}
