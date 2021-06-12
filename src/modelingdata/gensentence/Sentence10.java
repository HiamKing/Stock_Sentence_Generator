package modelingdata.gensentence;

import java.text.MessageFormat;
import java.util.ListIterator;

import modelingdata.stockinfo.StockInfo;

public class Sentence10 extends Sentence{
	
	StockInfo maxVolume;
	double totalVolume, totalVolLastMonth, aveVolume;
	String change1Month;
	double percentChange1, days;

	public Sentence10() {
		maxVolume = new StockInfo();
		totalVolume = totalVolLastMonth = aveVolume = 0;
		change1Month = null;
		percentChange1 = days = 0;
	}

	@Override
	public void process(int month) {
		getSentence("sentence10");

		ListIterator<StockInfo> iter;

		String str1 = "Cụ thể, tổng <khối lượng giao dịch| chứng khoán trong <tháng {5}| trên sàn HOSE đạt {2} tỷ đơn vị, <{0}| {3}% so với tháng {6} và <{1}|";
		String str2 = "Theo đó, tổng <khối lượng giao dịch| trong tháng qua đạt {2} tỉ cổ phiếu, bình quân khối lượng giao dịch đạt {8} triệu CP/phiên,  <{0}| {3}% so với tháng trước.";
		String str3 = "Như vậy là <khối lượng giao dịch| trong <tháng {5}| đã <{0}| tới {3}% so với tháng trước đó.";
		
		if (((str1.equals(str) || str2.equals(str) || str3.equals(str)) && month >= 3) || ((!str1.equals(str) && !str2.equals(str) && !str3.equals(str)))){
			
		for (iter = st.stockInfoList.listIterator(); iter.hasNext(); ) {
			StockInfo temp = iter.next();
			String month1 = temp.getDate().substring(3, 5);
			
			if (Integer.parseInt(month1)  == month - 1)
				totalVolLastMonth += temp.getVolume();
			
			if (Integer.parseInt(month1)  == month) {
				totalVolume += temp.getVolume();
				days += 1;
				if (maxVolume.getVolume() < temp.getVolume()) {
					maxVolume = temp;
				}
			}
		}
		
		aveVolume = totalVolume / days;
		
		if (totalVolume < totalVolLastMonth) {
			change1Month = "giảm";
			percentChange1 = (1 - totalVolume / totalVolLastMonth) * 100; 
		} else {
			change1Month = "tăng";
			percentChange1 = (totalVolume / totalVolLastMonth - 1) * 100; 
		}

		String result = MessageFormat.format(str, 
				change1Month, // 0
				totalVolume / 1000000000,// 1
				percentChange1, // 2
				month, // 3
				month - 1,// 4 
				aveVolume / 1000000,// 5 
				maxVolume.getDate(), // 6
				maxVolume.getVolume() / 1000000,// 7 
				maxVolume.getCodeStock());// 8
		saveSentence(result, "sentences");
		}
	}
}
