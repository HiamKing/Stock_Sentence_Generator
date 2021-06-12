package modelingdata.gensentence;

import modelingdata.stockinfo.StockInfo;

// 
public class Count {
	StockInfo a,b;

	private double priceDiff;
	private int pricePercent;
	private double volumeDiff;
	private int volumePercent;
	 
	public Count(StockInfo temp, StockInfo tempYTD) {
		this.a = temp;
		this.b = tempYTD;
	}

	public String AugDecPrice() {
		if (a.getClosePrice() < b.getClosePrice()) {
			this.priceDiff = b.getClosePrice() - a.getClosePrice();
			
			if (priceDiff * 100 / b.getClosePrice() == 0) this.pricePercent = 1;
			else {
				this.pricePercent = (int) (priceDiff * 100 / b.getClosePrice());
			}
			
			if (1 <= pricePercent && pricePercent <= 2) return "giảm nhẹ";
			else return "giảm ";
		} else if (a.getClosePrice() > b.getClosePrice()) {
			this.priceDiff = a.getClosePrice() - b.getClosePrice();
			
			if (priceDiff * 100 / b.getClosePrice() == 0) this.pricePercent = 1;
			else {	
				this.pricePercent = (int)(priceDiff * 100 / b.getClosePrice());
			}
			
			if(1 <= pricePercent && pricePercent <= 2) return "tăng nhẹ";
			else return "tăng";
		} else {
			return "đứng giá";
			}
	}
	
	public String AugDecVolume() {
		if(a.getVolume() < b.getVolume()) {
			this.volumeDiff = b.getVolume() - a.getVolume();
			
			if((int)(volumeDiff * 100 / b.getVolume()) == 0) this.volumePercent = 1;
			else {
				this.volumePercent = (int)(volumeDiff * 100 / b.getVolume());	
			}
			
			if(1 <= volumePercent && volumePercent <= 10) return "giảm nhẹ";
			else return "giảm";
		} else if (a.getVolume() > b.getVolume()) {
			this.volumeDiff = (a.getVolume() - b.getVolume());
			
			if((int)(volumeDiff * 100 / b.getVolume()) == 0) this.volumePercent = 1;
			else {
				this.volumePercent = (int)(volumeDiff * 100 / b.getVolume());
			}
			
			if(1 <= volumePercent && volumePercent <= 10) return "tăng nhẹ";
			else return "tăng";
		} else {
			return "đứng";
		}
	}
	
	public int getVolumePercent() {
		return volumePercent;
	}
	
	public int getPricePercent() {
		return pricePercent;
	}
	
	public double getPriceDiff() {
		return priceDiff;
	}
	
	public double getVolumeDiff() {
		return volumeDiff;
	}
}
