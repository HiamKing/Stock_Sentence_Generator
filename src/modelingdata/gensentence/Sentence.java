package modelingdata.gensentence;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Random;

import modelingdata.stockinfo.StockList;

import java.util.ArrayList;
import java.util.List;

public class  Sentence {
	protected String str;
	public static StockList st;

	public void getSentence(String path) {
		try {
			String dir = System.getProperty("user.dir");
			File FileDir = new File(dir + "\\data\\" + path + ".txt");
			BufferedReader in1 = new BufferedReader(new InputStreamReader(new FileInputStream(FileDir), "UTF8"));

			List<String> ListSentences = new ArrayList<>();
			
			while ((str = in1.readLine()) != null) {
				ListSentences.add(str);
			}
			in1.close();
			
			Random rand = new Random();
			int SenIndex = rand.nextInt(ListSentences.size());
			
			str = ListSentences.get(SenIndex);
		}
		catch (UnsupportedEncodingException e) {
			System.out.println(e.getMessage());
		}
	    catch (IOException e) {
			System.out.println(e.getMessage());
	    } 
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void saveSentence(String sentence, String path)
	{
		String Dir = System.getProperty("user.dir");
		String FileDir = Dir + "\\data\\" + path + ".txt";
		try {
			Files.write(Paths.get(FileDir), (sentence + "\r\n").getBytes(), StandardOpenOption.APPEND);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void  process(String date) {};
	
	public void process(String date, String codeStock) {};
	
	public void process(int month) {};
}
