package modelingdata;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import modelingdata.gensentence.Sentence;
import modelingdata.gensentence.Sentence1;
import modelingdata.gensentence.Sentence10;
import modelingdata.gensentence.Sentence2;
import modelingdata.gensentence.Sentence3;
import modelingdata.gensentence.Sentence4;
import modelingdata.gensentence.Sentence5;
import modelingdata.gensentence.Sentence6;
import modelingdata.gensentence.Sentence7;
import modelingdata.gensentence.Sentence8;
import modelingdata.gensentence.Sentence9;
import modelingdata.readfilecsv.FileInfo;
import modelingdata.stockinfo.StockList;

public class ModelingData {
	private List<Sentence> sentences;
	private FileInfo inf;
	private List<String> dayList;
	private List<String> codeList;
	private List<Integer> monthList;
	
	public void setDayList() {
		String dir = System.getProperty("user.dir");

		String line  = "";
		BufferedReader br = null;
		try 
		{
			br = new BufferedReader(new FileReader(dir + "\\data\\data\\BID.csv"));
			int i = 0;
			while((line = br.readLine()) != null && (i < 113))
			{
				String[] values = line.split(",");
				dayList.add(values[0]);
				i++;
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) br.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public void setCodeList() {
		String dir = System.getProperty("user.dir");
		
		String line  = "";
		BufferedReader br = null;
		try 
		{
			br = new BufferedReader(new FileReader(dir + "\\data\\filename.txt"));
			while((line = br.readLine()) != null)
			{
				String values = line;
				codeList.add(values.substring(0, 3));
			}
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) br.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public void setMonthList()
	{
		monthList.add(1);
		monthList.add(2);
		monthList.add(3);
		monthList.add(4);
		monthList.add(5);
		monthList.add(6);
	}
	
	public ModelingData() {
		dayList = new ArrayList<>();
		codeList = new ArrayList<>();
		monthList = new ArrayList<>();
		sentences = new ArrayList<>();
		inf = new FileInfo();
		Sentence.st = new StockList();
		Sentence.st.setData(inf.getStockInfoList()); 
		
		sentences.add(new Sentence9());
		sentences.add(new Sentence10());
		sentences.add(new Sentence8());
		sentences.add(new Sentence5());
		sentences.add(new Sentence6());
		sentences.add(new Sentence7());
		sentences.add(new Sentence1());
		sentences.add(new Sentence2());
		sentences.add(new Sentence3());
		sentences.add(new Sentence4());
		
		setCodeList();
		setDayList();
		setMonthList();
		String Dir = System.getProperty("user.dir");
		String FileDir = Dir + "\\data\\" + "sentences.txt";
		try {
			Files.deleteIfExists(Path.of(FileDir));
			Files.createFile(Path.of(FileDir));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void initData() {
		for (int i = 0; i < monthList.size(); i++) {
			processData(monthList.get(i));
		}
		
		for (int i = 0; i < dayList.size(); i++) {
			processData(dayList.get(i));
		}
		
		for (int i = 0; i < dayList.size(); i++) {
			for (int j = 0; j < codeList.size(); j++) {
				processData(codeList.get(j), dayList.get(i));
			}
		}
	}
	
	public void processData(String date) {
		sentences.get(0).process(date);
		sentences.get(2).process(date);
		sentences.get(8).process(date);
		sentences.get(9).process(date);
	}
	
	public void processData(String codeStock, String date) {
		sentences.get(3).process(codeStock, date);
		sentences.get(4).process(codeStock, date);
		sentences.get(5).process(codeStock, date);
		sentences.get(6).process(codeStock, date);
		sentences.get(7).process(codeStock, date);
	}
	
	public void processData(int month) {
		sentences.get(1).process(month);
	}
}	
