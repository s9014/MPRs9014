package com.jee.db;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.jee.entities.Product;

@Stateless
public class ProductManager {
	
	@PersistenceContext
	EntityManager em;
	
	public void addProduct(Product product){
		em.persist(product);
	}

	public void deleteProduct(Product p) {
		p=em.find(Product.class, p.getId());
		em.remove(p);
		
	}

	@SuppressWarnings("unchecked")
	public List<Product> getAllProducts() {
		return em.createQuery("from products").getResultList();
	}
	
	

}
