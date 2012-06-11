package com.mpr.web;

import java.io.Serializable;
import java.sql.SQLException;

import javax.enterprise.context.SessionScoped;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;

import com.mpr.db.StoreHouseManager;
import com.mpr.entities.StoreHouse;

@SessionScoped
@Named("storehouseBean")
public class StoreHouseFormBean implements Serializable{
	
private static final long serialVersionUID = 1L;
	
	private StoreHouse storeHouse= new StoreHouse();
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
		try {
			storehouses.setWrappedData(sm.getAllStorehouses());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return storehouses;
	}

	public String addStorehouse(){
		try {
			sm.addStoreHouse(storeHouse);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "showStorehouses";
	}
	
	public String deleteStorehouse(){
		try {
			sm.deleteStoreHouse(storeHouse);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "showStorehouses";
	}

}
