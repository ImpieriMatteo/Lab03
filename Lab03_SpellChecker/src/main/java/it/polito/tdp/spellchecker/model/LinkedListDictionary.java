package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;

public class LinkedListDictionary {

	LinkedList<String> dictionary;

	public LinkedListDictionary() {
		this.dictionary = new LinkedList<>();
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

	public LinkedList<String> getDictionary() {
		return dictionary;
	}
}
