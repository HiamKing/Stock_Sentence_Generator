package modelingdata.gensentence;

import java.text.MessageFormat;
import java.util.ListIterator;

import modelingdata.stockinfo.StockInfo;

//import java.util.ListIterator;
//import java.util.Scanner;
//
//import readFileCSV.StockInfo;
import java.lang.Math;

public class Sentence7 extends Sentence {
	private StockInfo temp;
	private StockInfo tempYTD;
	private double percentDiff, percentFinal ; 
	
	public Sentence7() {
		temp = new StockInfo();
		tempYTD = new StockInfo();
		percentDiff = 0;
		percentFinal = 0;
	}
	
	@Override
	public void process(String codeStock, String date) {
		ListIterator<StockInfo> iter;
		
		for(iter = st.stockInfoList.listIterator(); iter.hasNext(); iter.next()) {
			int iterIdx = iter.nextIndex();
			
			if (iterIdx >= st.stockInfoList.size() - 1) 
				break;
			
			StockInfo temp = st.stockInfoList.get(iterIdx);     
			
			if (temp.getCodeStock().equals(codeStock) && temp.getDate().equals(date)) {
				
				this.temp = temp;
				this.tempYTD = st.stockInfoList.get(iterIdx + 1); 
				break;
			}
	    }
		
		if(temp.getClosePrice() > tempYTD.getClosePrice() && 
			temp.getLowPrice() < tempYTD.getClosePrice()) {
			
			getSentence("7toGreen");
			percentDiff = 100 - temp.getLowPrice() / tempYTD.getClosePrice() * 100;
			percentFinal = -100 + temp.getClosePrice() / tempYTD.getClosePrice() * 100;
			String result = MessageFormat.format(str, date, codeStock, percentDiff, percentFinal, Math.round(temp.getVolume() / 100000.0 * 100.0) / 100.0, 1000 * (temp.getClosePrice() - tempYTD.getClosePrice()));
			saveSentence(result, "sentences");
		} else if(temp.getClosePrice() < tempYTD.getClosePrice() && 
					temp.getHighPrice() > tempYTD.getClosePrice()) {
			getSentence("7toRed");
			percentDiff = -100 + temp.getHighPrice() / tempYTD.getClosePrice() * 100;
			percentFinal = 100 - temp.getClosePrice() / tempYTD.getClosePrice() * 100;
			String result = MessageFormat.format(str, date, codeStock, percentDiff, percentFinal, Math.round(temp.getVolume() / 100000.0 * 100.0) / 100.0, 1000 * (- temp.getClosePrice() + tempYTD.getClosePrice()));
			saveSentence(result, "sentences");
		}
	}
}


