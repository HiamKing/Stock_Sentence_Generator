package application;

import java.util.ArrayList;
import java.util.List;

import database.DataBase;
import modelingdata.ModelingData;

public class Administrator {
	private DataBase database;
	
	private ModelingData modelingData;
	
	public Administrator() {
		modelingData = new ModelingData();
		loadData();
		database = new DataBase();
	}
	
	private void loadData() {
		modelingData.initData();
	}
	
    public ArrayList<String> getStockNameTag() {
    	return DataBase.getStockNameTag();
    }
    
    public ArrayList<String> getOthersTag() { 
    	return DataBase.getOthersTag();
    }
    
    public ArrayList<String> searchFor(List<String> listTag) {
        return database.searchFor(listTag);
    }
}
