package it.polito.tdp.spellchecker.model;

import java.util.ArrayList;
import java.util.List;

public class Model {
	
	long controlBegin;
	long controlEnd;
	
	public Model() {
		this.controlBegin = 0;
		this.controlEnd = 0;
	}

	public List<String> getErrors(String input, String language) {
		
		ArrayDictionary tempDictionary = new ArrayDictionary();
		try {
			tempDictionary.loadDictionary(language);
		} catch(Exception e) {
			throw new RuntimeException();
		}

		input = input.replaceAll("[.,\\/#!?$%\\^&\\*;:{}=\\-_`~()\\[\\]\"]", "");
		String[] arrayInput = input.split(" ");
		List<String> errors = new ArrayList<>();
		
		controlBegin = System.nanoTime();
		for(int i=0; i<arrayInput.length; i++) {
			if(!tempDictionary.getDictionary().contains(arrayInput[i]))
				errors.add(arrayInput[i]);
		}
		controlEnd = System.nanoTime();
		
		return errors;
	}
	
	public double getTimeOperation() {
		return (double)(controlEnd-controlBegin)/100000000;
	}
}
