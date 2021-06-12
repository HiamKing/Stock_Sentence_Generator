package modelingdata.gensentence;

import java.text.MessageFormat;
//import java.util.ListIterator;
//import java.util.Scanner;
//
//import readFileCSV.StockInfo;
import java.util.ListIterator;

import modelingdata.stockinfo.StockInfo;

public class Sentence6 extends Sentence {
	private StockInfo temp;
	private StockInfo tempYTD;
	private double percent;
	private double ceil, floor;
	
	public Sentence6() {
		temp = new StockInfo();
		tempYTD = new StockInfo();
		ceil = 0;
		floor = 0;
		percent = 0.0;
	}
	
	@Override
	public void process(String codeStock, String date) {
		ListIterator<StockInfo> iter;
		
		for (iter = st.stockInfoList.listIterator(); iter.hasNext(); iter.next()) {	
			int iterIdx = iter.nextIndex();
			
			if(iterIdx >= st.stockInfoList.size() - 1) 
				break;
			
			StockInfo temp = st.stockInfoList.get(iterIdx);     
			
			if(temp.getCodeStock().equals(codeStock) && temp.getDate().equals(date)) {
				
				this.temp = temp;
				this.tempYTD = st.stockInfoList.get(iterIdx + 1); 
				this.ceil = st.stockInfoList.get(iterIdx + 1).getClosePrice() * 1.07;
				this.floor = st.stockInfoList.get(iterIdx + 1).getClosePrice() * 0.93;
				break;
			}
	    }
		
		if(temp.getClosePrice() > tempYTD.getClosePrice()) {
			if((ceil <= 10.00 && ceil - temp.getClosePrice() < 0.01)
					|| (ceil > 10 && ceil < 10.05 && temp.getClosePrice() == 10)
					|| (ceil >= 10.05 && ceil <= 50 && ceil - temp.getClosePrice() < 0.05)
					|| (ceil > 50 && ceil < 50.10 && temp.getClosePrice() == 50)
					|| (ceil >= 50.10 && ceil - temp.getClosePrice() < 0.1)) {
				getSentence("6up");
				percent = -100 + temp.getClosePrice() / tempYTD.getClosePrice() * 100;
				String result = MessageFormat.format(str, date, codeStock, temp.getClosePrice(), Math.round(temp.getVolume() * 100.0) / 100.0, percent);
				saveSentence(result, "sentences");
			}
		} else if(temp.getClosePrice() < tempYTD.getClosePrice()) {
			if((floor < 10 && - floor + temp.getClosePrice() < 0.01)
					|| (floor >= 10 && floor < 50 && - floor + temp.getClosePrice() < 0.05)
					|| (floor >= 50 && - floor + temp.getClosePrice() < 0.1)) {
				getSentence("6down");
				percent = 100 - temp.getClosePrice() / tempYTD.getClosePrice() * 100;
				String result = MessageFormat.format(str, date, codeStock, temp.getClosePrice(), Math.round(temp.getVolume() * 100.0) / 100.0, percent);
				saveSentence(result, "sentences");
			}
		}
	}
}


