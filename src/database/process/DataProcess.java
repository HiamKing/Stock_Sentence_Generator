package database.process;

import java.util.ArrayList;

import database.DataBase;
import database.Sentence;

public class DataProcess {
	private HashFunction hf;
    
    public DataProcess() {
    	hf = new HashFunction();
    }
    
	public Sentence toSentence(String str) {
		StringBuilder tempStr = new StringBuilder();
		ArrayList<Integer> listHashTag = new ArrayList<Integer>();
		StringBuilder curTag = null;
		
		boolean isTag = false;
		for( int i = 0; i < str.length(); ++ i ) {
			if(str.charAt(i) == '<') {
				curTag = new StringBuilder();
				isTag = true;
			} else if( str.charAt(i) == '>' || str.charAt(i) == ']' || str.charAt(i) == '|' ) {
				listHashTag.add(hf.getHashCode(curTag.toString()));
				if(str.charAt(i) == '>') addStockNameTag(curTag.toString());
				if(str.charAt(i) == '|') addOthersTag(curTag.toString());
				isTag = false;
			} else {
				tempStr.append(str.charAt(i));
				if( isTag ) curTag.append(str.charAt(i));
			}
		}
         
		return new Sentence(listHashTag, tempStr.toString());
	}
    
    private void addStockNameTag(String tag) {
        if(!DataBase.getStockNameTag().contains(tag)){
        	DataBase.getStockNameTag().add(tag);
        }
    }
    
    private void addOthersTag(String tag) {
        if(!DataBase.getOthersTag().contains(tag)){
        	DataBase.getOthersTag().add(tag);
        }
    }
}
