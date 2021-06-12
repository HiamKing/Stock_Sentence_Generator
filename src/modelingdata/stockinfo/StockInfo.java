package modelingdata.stockinfo;


public class StockInfo {

	private String date;
	private String codeStock;
	private double openPrice, closePrice, highPrice, lowPrice, volume;
	
	public StockInfo() {
		date = new String();
		codeStock = new String();
		openPrice = closePrice = highPrice = lowPrice = volume = 0;
	}
	
	public StockInfo(String Date, String CodeStock, double OpenPrice, double ClosePrice, double HighPrice, double LowPrice, double Volume)
	{
		this.date = Date;
		this.codeStock = CodeStock;
		this.openPrice = OpenPrice;
		this.closePrice = ClosePrice;
		this.highPrice = HighPrice;
		this.lowPrice = LowPrice;
		this.volume = Volume;
	}
	
	public String getDate() {
		return date;
	}
	
	public String getCodeStock() {
		return codeStock;
	}
	
	public double getOpenPrice() {
		return openPrice;
	}
	
	public double getClosePrice() {
		return closePrice;
	}
	
	public double getHighPrice() {
		return highPrice;
	}
	
	public double getLowPrice() {
		return lowPrice;
	}
	
	public double getVolume() {
		return volume;
	}
}
