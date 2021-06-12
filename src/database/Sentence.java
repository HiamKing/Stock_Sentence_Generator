package database;

import java.util.ArrayList;

public class Sentence {
	
	private ArrayList<Integer> tag;
	
	private String sentence;
	
	public Sentence(ArrayList<Integer> tag, String sentence) {
		this.tag = tag;
		this.sentence = sentence;
	}
	
	public String getSentence() {
		return sentence;
	}
	
	public ArrayList<Integer> getTag() {
		return tag;
	}
}
