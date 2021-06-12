package modelingdata.gensentence;

import java.text.MessageFormat;
//import java.util.ListIterator;
//import java.util.Scanner;
//
//import readFileCSV.StockInfo;
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
import java.util.ListIterator;

import modelingdata.stockinfo.StockInfo;

public class Sentence5 extends Sentence {
	private double percent;
	private int days;
	
	public Sentence5() {
		percent = 0.0;
		days = 0;
	}
	
	@Override
	public void process(String codeStock, String date) {
		int i = 0;
		days = 0;
		
		ListIterator<StockInfo> iter;
		
		for(iter = st.stockInfoList.listIterator(); iter.hasNext(); iter.next()) {
			int iterIdx = iter.nextIndex();
			
			if (iterIdx >= st.stockInfoList.size() - 1) 
				break;
			
			StockInfo temp = st.stockInfoList.get(iterIdx);     
			
			if (temp.getCodeStock().equals(codeStock) && temp.getDate().equals(date)) {
				i = iterIdx;
				break;
			}
	    }
		
		if(st.stockInfoList.get(i).getClosePrice() > st.stockInfoList.get(i + 1).getClosePrice() && 
				i < st.stockInfoList.size() - 1) {
			getSentence("5up");
			percent = -100 + st.stockInfoList.get(i).getClosePrice()/st.stockInfoList.get(i + 1).getClosePrice() * 100;
			while (st.stockInfoList.get(i).getClosePrice() > st.stockInfoList.get(i + 1).getClosePrice() && 
					i < st.stockInfoList.size() - 1) {
				days++;
				i++;
			}
		} else if(st.stockInfoList.get(i).getClosePrice() < st.stockInfoList.get(i + 1).getClosePrice() &&
				i < st.stockInfoList.size() - 1) {
			getSentence("5down");
			percent = 100 - st.stockInfoList.get(i).getClosePrice()/st.stockInfoList.get(i + 1).getClosePrice() * 100;
			while (st.stockInfoList.get(i).getClosePrice() < st.stockInfoList.get(i + 1).getClosePrice() && 
					i < st.stockInfoList.size() - 1) {
				days += 1;
				i++;
			}
		}
		
		if (days > 3) {
			String result = MessageFormat.format(str, date, codeStock, days, percent);
		    saveSentence(result, "sentences");
		}
	}
}