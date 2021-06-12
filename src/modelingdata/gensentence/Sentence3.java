package modelingdata.gensentence;
// 4 cổ phiếu cao nhất ngày
import java.text.MessageFormat;
import java.util.ListIterator;
import java.util.Vector;

import modelingdata.stockinfo.StockInfo;

public class Sentence3 extends Sentence {
	private double percentAllVolume;
	private Count a;
	private Vector<StockInfo> listStock;
	
	public void findData(String date) {
		getSentence("sentences3");
    	Vector<StockInfo> listStock =new Vector<StockInfo>();
		ListIterator<StockInfo> iter;
		
		for(iter = st.stockInfoList.listIterator(); iter.hasNext(); iter.next()) {
			int iterIdx = iter.nextIndex();
			
			if (iterIdx >= st.stockInfoList.size() - 1) 
				break;
			
			StockInfo temp = st.stockInfoList.get(iterIdx);     
			
			if ( temp.getDate().equals(date)) {
				listStock.add(temp);
			}
		}
		
		this.listStock = listStock;
	}
	
	public Vector<StockInfo> byVolume(Vector<StockInfo> listStock) {
		Vector<StockInfo> a = listStock;
		int n = a.size();
		   
		for (int i = 0 ; i < n - 1; i++) 
			for (int j = i + 1; j < n; j++)
				if (a.get(i).getVolume() < a.get(j).getVolume()) {
	                  StockInfo container = a.get(i);
	                  a.set(i , a.get(j)); 
	                  a.set(j , container);
	            }
		   
		return a;
	}
    
	public Vector<StockInfo> byPrice(Vector<StockInfo> listStock2) {
		Vector<StockInfo> b = listStock2;
		int n = b.size();
		   
		for (int i = 0 ; i < n - 1; i++) 
			for (int j = i + 1; j < n; j++)
				if (b.get(i).getClosePrice() < b.get(j).getClosePrice()) {
	                   StockInfo container = b.get(i);
	                   b.set(i , b.get(j)); 
	                   b.set(j , container);
	            }
		   
		return b;
   }
  
   public double getTotal(Vector<StockInfo> a) {
	    double total = 0;
	   
	    for(int i = 0; i <= 29; i++) {
		    total += a.get(i).getVolume();
	    }
	   
	    return total;
   }
   
   public void getMaxVolume (String date) {
	   getSentence("sentences3");
	   ListIterator<StockInfo> iter;
		
	   for(iter = st.stockInfoList.listIterator(); iter.hasNext(); iter.next()) {	
	 		int iterIdx = iter.nextIndex();
			
			if (iterIdx >= st.stockInfoList.size() - 1) 
				break;
			
			StockInfo temp = st.stockInfoList.get(iterIdx); 
			StockInfo tempYTD = st.stockInfoList.get(iterIdx+1); 
			 
			if(temp.getDate().equals(date) && temp.getCodeStock().equals(byVolume(listStock).get(0).getCodeStock())) {
				break;
			}
			
			this.a = new Count(temp, tempYTD);
	   }
   }
 
   @Override
   public void process(String date) {
		try {
			findData(date);
			
			getMaxVolume (date);
			percentAllVolume=(byVolume(listStock).get(0).getVolume()+byVolume(listStock).get(1).getVolume()+byVolume(listStock).get(2).getVolume())/getTotal(byVolume(listStock));
		    String result = MessageFormat.format(str,
		    		byVolume(listStock).get(0).getCodeStock(),//{0}
					Math.round((byVolume(listStock).get(0).getVolume() / 1000000.0) * 100.0) / 100.0,//{1}
					byVolume(listStock).get(1).getCodeStock(),//{2}
					Math.round((byVolume(listStock).get(1).getVolume() / 1000000.0) * 100.0) / 100.0,//{3}
					byVolume(listStock).get(2).getCodeStock(),//{4}
					Math.round((byVolume(listStock).get(2).getVolume() / 1000000.0) * 100.0) / 100.0,//{5}
					byVolume(listStock).get(3).getCodeStock(),//{6} 
					Math.round((byVolume(listStock).get(3).getVolume() / 1000000.0) * 100.0) / 100.0,//{7}
					byVolume(listStock).get(4).getCodeStock(),//{8}
					Math.round((byVolume(listStock).get(4).getVolume() / 1000000.0) * 100.0) / 100.0,//{9}
					a.AugDecPrice(),//{10}
					a.getPriceDiff()*1000,//{11}
					percentAllVolume*100);//{12}
    
            saveSentence( result, "sentences");
	    } catch (Exception e) {
		System.out.println(e.getMessage());
        }
   }
}
