package modelingdata.gensentence;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
//import java.util.Scanner;

import modelingdata.stockinfo.StockInfo;

public class Sentence8 extends Sentence {
	
	private StockInfo decreaseMax, increaseMax;
	private int increaseStock, decreaseStock, equalStock, increasePercentStock, decreasePercentStock;
	private double decreaseMaxPercent, increaseMaxPercent;

	public Sentence8() {
		decreaseMax = new StockInfo();
		increaseMax = new StockInfo();
		increaseStock = decreaseStock = equalStock = increasePercentStock = decreasePercentStock = 0;
		decreaseMaxPercent = increaseMaxPercent = 0;
	}
	
	@Override
	public void process(String date) {
		getSentence("sentence12");

		List<StockInfo> StockTodayList = new ArrayList<>();
		ListIterator<StockInfo> iter;
		
		for (iter = st.stockInfoList.listIterator(); iter.hasNext();){
			StockInfo temp = iter.next();
			
			if (temp.getDate().equals(date)){
				StockTodayList.add(temp);
			}
		}
		
		decreaseMax = StockTodayList.get(0);
		increaseMax = StockTodayList.get(0);
		
		for(iter = StockTodayList.listIterator(); iter.hasNext(); iter.next()) {
			StockInfo temp = StockTodayList.get(iter.nextIndex());
			
			StockInfo tempYTD = st.getYesterday(temp);

			if(temp.getClosePrice() < tempYTD.getClosePrice()) {
				decreaseStock += 1;
				if((tempYTD.getClosePrice() - temp.getClosePrice()) > (st.getYesterday(decreaseMax).getClosePrice() - decreaseMax.getClosePrice())) {
					decreaseMax = temp;
					decreaseMaxPercent = (1 - tempYTD.getClosePrice() / temp.getClosePrice()) * 100;
				}
			}
				
			if (temp.getClosePrice() > tempYTD.getClosePrice()) {
				increaseStock += 1;
				if ((temp.getClosePrice() - tempYTD.getClosePrice()) > (increaseMax.getClosePrice() - st.getYesterday(increaseMax).getClosePrice())) {
					increaseMax = temp;
					increaseMaxPercent = (temp.getClosePrice() / tempYTD.getClosePrice() - 1) * 100;
				}
			}
				
			if (temp.getClosePrice() == tempYTD.getClosePrice())
				equalStock += 1;
			
			if ((temp.getClosePrice() / tempYTD.getClosePrice()) > 1.05)
				increasePercentStock += 1;
			
			if ((temp.getClosePrice() / tempYTD.getClosePrice()) < 0.95)
				decreasePercentStock += 1;
		}
		
		String result = MessageFormat.format(str, 
				decreaseStock, //0
				equalStock, //1
				increaseStock,//2 
				date, //3
				decreaseMax.getCodeStock(),//4 
				increaseMax.getCodeStock(), //5
				increasePercentStock, //6
				decreasePercentStock, //7
				decreaseMaxPercent, //8
				increaseMaxPercent);//9
		saveSentence(result, "sentences");
	}
}
