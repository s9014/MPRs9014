package com.jee.db;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.jee.entities.StoreHouse;

public class StoreHouseManager {
	@PersistenceContext
	EntityManager em;
	
	public void addStoreHouse(StoreHouse storeHouse){
		em.persist(storeHouse);
	}

	public void deleteStoreHouse(StoreHouse storeHouse) {
		storeHouse=em.find(StoreHouse.class, storeHouse.getId());
		em.remove(storeHouse);
		
	}

	@SuppressWarnings("unchecked")
	public List<StoreHouse> getAllStoreHouses() {
		return em.createQuery("from storehouses").getResultList();
	}

}
