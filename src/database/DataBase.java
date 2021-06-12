package database;

import java.util.ArrayList;
import java.util.List;

import database.process.Reader;
import database.process.Search;

public class DataBase {
    private ArrayList<Sentence> data;
    
    private static ArrayList<String> stockNameTag = new ArrayList<String>();
    
    private static ArrayList<String> othersTag = new ArrayList<String>();
    
    private Reader rd;
    private Search sch;
    
    public DataBase() {
        rd = new Reader();
        sch = new Search();
        data = rd.readInput();
    }
    
    public static ArrayList<String> getStockNameTag() {
    	return stockNameTag;
    }
    
    public static ArrayList<String> getOthersTag() { 
    	return othersTag;
    }
    
    public ArrayList<String> searchFor(List<String> listTag) {
        return sch.searchFor(data, listTag);
    }
}
