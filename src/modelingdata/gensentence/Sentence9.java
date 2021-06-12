package modelingdata.gensentence;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
//import java.util.Scanner;

import modelingdata.stockinfo.StockInfo;

public class Sentence9 extends Sentence{
	private StockInfo maxVolume;
	private double maxAverageVol, minAverageVol, smallVol;
	private String minAverageCode, maxAverageCode, smallCode;
	
	public Sentence9() {
		maxVolume = new StockInfo();
		maxAverageVol = Double.MIN_VALUE;
		minAverageVol = Double.MAX_VALUE;
		smallVol = 0;
		minAverageCode = maxAverageCode = smallCode = null;
	}
	
	@Override
	public void process(String Date) {
		double percent = 0, time = 0;
		getSentence("sentence9");
		
		List<StockInfo> StockTodayList = new ArrayList<>();
		ListIterator<StockInfo> iter;
		
		for(iter = st.stockInfoList.listIterator(); iter.hasNext();) {
			StockInfo temp = iter.next();
			
			if (temp.getDate().equals(Date)){
				StockTodayList.add(temp);
			}
		}
		
		for(iter = StockTodayList.listIterator(); iter.hasNext();) {
			StockInfo stockTemp = iter.next();
			double totalVolume = 0;
			
			for(int i = 0; i < 10; i++) {
				totalVolume += st.getKDaysBefore(stockTemp, i).getVolume();
			}
			
			if(maxVolume.getVolume() < stockTemp.getVolume()) {
				maxVolume = stockTemp;
				time = stockTemp.getVolume() * 10 / totalVolume; // khối lượng giao dịch nhi�?u nhất gấp bao nhiêu lần khối lượng tb trong 10 ngày trở lại
			}
			
			if(minAverageVol > (totalVolume / 10)) {
				minAverageVol = totalVolume / 10;
				minAverageCode = stockTemp.getCodeStock();
			}
			
			if(maxAverageVol < (totalVolume / 10)) {
				maxAverageVol = totalVolume / 10;
				maxAverageCode = stockTemp.getCodeStock();
			}
			
			if(((totalVolume / 10) < 300000)) {
				smallVol = totalVolume / 10;
				smallCode =  stockTemp.getCodeStock();
			}
		}
		
		percent = (minAverageVol / maxAverageVol) * 100;
		
		String result = MessageFormat.format(str, 
				maxAverageCode, //0
				Math.round(maxAverageVol),//1 
				minAverageCode, //2
				Math.round(minAverageVol),//3 
				percent,//4
				smallCode, //5
				Math.round(smallVol),//6 
				maxVolume.getCodeStock(), // 7 
				Math.round(maxVolume.getVolume() / 1000000),//8 
				time,
				Date);//9
		saveSentence(result, "sentences");
	}
}
