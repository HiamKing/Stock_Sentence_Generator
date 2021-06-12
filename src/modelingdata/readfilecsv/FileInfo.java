package modelingdata.readfilecsv;

import java.io.BufferedReader;
import java.io.File;
//import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
//import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import modelingdata.stockinfo.StockInfo;

public class FileInfo extends InputRead {
	List<StockInfo> fileInfoList;
	
	public FileInfo() {
		fileInfoList = new ArrayList<>();
		read();
	}
	
	public List<StockInfo> getStockInfoList() {
		return fileInfoList;
	}
	
	@Override
	void read() {
		String dir = System.getProperty("user.dir");

		for(File file : filesList) {
	        String path = dir + "\\data\\data\\" + file.getName();
			String line  = "";
			BufferedReader br = null;
			try {
				br = new BufferedReader(new FileReader(path));
				
				while((line = br.readLine()) != null) {
					String[] values = line.split(",");
					StockInfo listLine;
					listLine = new StockInfo(values[0], values[2], Double.parseDouble(values[3]), Double.parseDouble(values[1]), Double.parseDouble(values[4]), Double.parseDouble(values[5]), Double.parseDouble(values[6]));
					fileInfoList.add(listLine);
				}
				br.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}finally {
				try {
					if (br != null) br.close();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
	      }
	}
}