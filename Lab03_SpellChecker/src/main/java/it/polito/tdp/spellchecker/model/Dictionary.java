package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Dictionary {
	
	List<String> dictionary;

	public Dictionary() {
		this.dictionary = new ArrayList<>();
	}
	
	public void loadDictionary(String language) {
		try {
			String fileName = language+".txt";
			FileReader fr = new FileReader("src/main/resources/"+fileName);
			BufferedReader br = new BufferedReader(fr);
		
			String word;
			while((word = br.readLine())!=null) {
				dictionary.add(word.toLowerCase());
			}
		
			br.close();
			fr.close();
		} catch(Exception e) {
			throw new RuntimeException();
		}
	}

	public List<String> getDictionary() {
		return dictionary;
	}
	
}
