package it.polito.tdp.spellchecker.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Model {
	
	long controlBegin;
	long controlEnd;
	long controlBeginLinear;
	long controlEndLinear;
	long controlBeginDichotomic;
	long controlEndDichotomic;
	
	public Model() {
		this.controlBegin = 0;
		this.controlEnd = 0;
		this.controlBeginLinear = 0;
		this.controlEndLinear = 0;
		this.controlBeginDichotomic = 0;
		this.controlEndDichotomic = 0;
	}
	
	public List<String> spellCheckText(String input, String language) throws RuntimeException{
		
		//ArrayListDictionary tempDictionary = new ArrayListDictionary();
		LinkedListDictionary tempDictionary = new LinkedListDictionary();
		tempDictionary.loadDictionary(language);

		input = input.replaceAll("[.,\\/#!?$%\\^&\\*;:{}=\\-_`~()\\[\\]\"]", "");
		String[] arrayInput = input.split(" ");
		List<String> errors = new ArrayList<>();
		//ArrayList<String> dictionary = tempDictionary.getDictionary();
		LinkedList<String> dictionary = tempDictionary.getDictionary();
		
		controlBegin = System.nanoTime();
		for(int i=0; i<arrayInput.length; i++) {
			if(!dictionary.contains(arrayInput[i]))
				errors.add(arrayInput[i]);
		}
		controlEnd = System.nanoTime();
		
		return errors;
	}

	public void spellCheckTextLinear(String input, String language) throws RuntimeException{
		
		//ArrayListDictionary tempDictionary = new ArrayListDictionary();
		LinkedListDictionary tempDictionary = new LinkedListDictionary();
		tempDictionary.loadDictionary(language);

		input = input.replaceAll("[.,\\/#!?$%\\^&\\*;:{}=\\-_`~()\\[\\]\"]", "");
		String[] arrayInput = input.split(" ");
		List<String> errors = new ArrayList<>();
		//ArrayList<String> dictionary = tempDictionary.getDictionary();
		LinkedList<String> dictionary = tempDictionary.getDictionary();
		
		controlBeginLinear = System.nanoTime();
		for(int i=0; i<arrayInput.length; i++) {
			boolean flag = false;
			
			for(String s : dictionary) {
				if(s.equals(arrayInput[i])) {
					flag = true;
					break;
				}
			}
			
			if(!flag)
				errors.add(arrayInput[i]);
		}
		controlEndLinear = System.nanoTime();
	}
	
    public void spellCheckTextDichotomic(String input, String language) throws RuntimeException{
		
    	//ArrayListDictionary tempDictionary = new ArrayListDictionary();
		LinkedListDictionary tempDictionary = new LinkedListDictionary();
		tempDictionary.loadDictionary(language);

		input = input.replaceAll("[.,\\/#!?$%\\^&\\*;:{}=\\-_`~()\\[\\]\"]", "");
		String[] arrayInput = input.split(" ");
		List<String> errors = new ArrayList<>();
		//ArrayList<String> dictionary = tempDictionary.getDictionary();
		LinkedList<String> dictionary = tempDictionary.getDictionary();
		
		controlBeginDichotomic = System.nanoTime();
		
		int estremoX = dictionary.size()/2;
		int estremoY = dictionary.size();
		
		for(int i=0; i<arrayInput.length; i++) {
			boolean flag = false;
			
			if(dictionary.get(estremoX).compareTo(arrayInput[i])==0) 
				flag = true;
			else if(dictionary.get(estremoX).compareTo(arrayInput[i])>0){
				estremoY = 0;
				int control = estremoX;
				int flagControl = 0;
				while(estremoX!=estremoY) {
					int diff = (estremoX-estremoY)/2;
					estremoX = estremoX-diff;
					
					if(flagControl>2) {
						if(dictionary.get(estremoY-1).compareTo(arrayInput[i])==0 || dictionary.get(estremoY+1).compareTo(arrayInput[i])==0)
							flag = true;
						break;
					}
					
					if(estremoX==control)
						flagControl++;
					else
						control = estremoX;
					
					if(dictionary.get(estremoX).compareTo(arrayInput[i])==0) {
						flag = true;
						break;
					}
					else if(dictionary.get(estremoX).compareTo(arrayInput[i])<0) {
						estremoY = estremoX;
						estremoX = estremoX+diff;
					}		
				}
			}
			else if(dictionary.get(estremoX).compareTo(arrayInput[i])<0){
				int control = estremoY;
				int flagControl = 0;
				while(estremoX!=estremoY) {
					int diff = (estremoY-estremoX)/2;
					estremoY = estremoY-diff;
					
					if(flagControl>2) {
						if(dictionary.get(estremoY-1).compareTo(arrayInput[i])==0 || dictionary.get(estremoY+1).compareTo(arrayInput[i])==0)
							flag = true;
						break;
					}
					
					if(estremoY==control)
						flagControl++;
					else
						control = estremoY;
					
					if(dictionary.get(estremoY).compareTo(arrayInput[i])==0) {
						flag = true;
						break;
					}
					else if(dictionary.get(estremoY).compareTo(arrayInput[i])<0) {
						estremoX = estremoY;
						estremoY = estremoY+diff;
					}		
				}
			}
			
			if(!flag)
				errors.add(arrayInput[i]);
		}
		controlEndDichotomic = System.nanoTime();
	}
    
    public double getTimeOperation() {
		return (double)(controlEnd-controlBegin)/100000000;
	}
	
	public double getTimeOperationLinear() {
		return (double)(controlEndLinear-controlBeginLinear)/100000000;
	}
	
	public double getTimeOperationDichotomic() {
		return (double)(controlEndDichotomic-controlBeginDichotomic)/100000000;
	}
}
