package com.jee.web;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;

import com.jee.db.StoreHouseManager;
import com.jee.entities.StoreHouse;

@SessionScoped
@Named("storehouseBean")
public class StoreHouseFormBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private StoreHouse storeHouse = new StoreHouse();
	private ListDataModel<StoreHouse> storehouses = new ListDataModel<StoreHouse>();

	@Inject
	private StoreHouseManager sm;

	public StoreHouse getStoreHouse() {
		return storeHouse;
	}

	public void setStoreHouse(StoreHouse storeHouse) {
		this.storeHouse = storeHouse;
	}

	public ListDataModel<StoreHouse> getStorehouses() {

		storehouses.setWrappedData(sm.getAllStoreHouses());

		return storehouses;
	}

	public String addStorehouse() {

		sm.addStoreHouse(storeHouse);

		return "addStorehouse";
	}

	public String deleteStorehouse() {

		sm.deleteStoreHouse(storeHouse);

		return "showStorehouses";
	}

}
