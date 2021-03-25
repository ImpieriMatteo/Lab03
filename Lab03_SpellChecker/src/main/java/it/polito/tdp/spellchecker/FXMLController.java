package it.polito.tdp.spellchecker;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.spellchecker.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class FXMLController {
	
	private Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private TextArea txtInput;

    @FXML
    private TextArea txtOutput;
    
    @FXML
    private ComboBox<String> boxLanguage;
    
    @FXML
    private Button btnSpellCheck;

    @FXML
    private Button btnClearText;

    @FXML
    private Label txtNumberOfErrors;

    @FXML
    private Label txtOperationTimeLinear;
    
    @FXML
    private Label txtOperationTimeDichotomic;
    
    @FXML
    private Label txtOperationTime;

    @FXML
    void handleClearText(ActionEvent event) {
    	this.txtInput.clear();
    	this.txtOutput.clear();
    	this.txtOperationTime.setText("");
    	this.txtOperationTimeLinear.setText("");
    	this.txtOperationTimeDichotomic.setText("");
    	this.txtNumberOfErrors.setText("");
    }

    @FXML
    void handleSpellCheck(ActionEvent event) {
    	this.txtOutput.setText("");
    	this.txtOperationTimeLinear.setText("");
    	this.txtOperationTimeDichotomic.setText("");
    	this.txtNumberOfErrors.setText("");
    	
    	if(this.txtInput.getText().isEmpty()) {
    		this.txtOutput.setText("You have to insert some text!");
    		return;
    	}
    	
    	if(this.boxLanguage.getValue()==null) {
    		this.txtOutput.setText("You have to choose a language!");
    		return;

    	}
    	
    	String language = this.boxLanguage.getValue();
    	List<String> errors;
    	
    	try {
    		errors = model.spellCheckText(this.txtInput.getText(), language);
    		model.spellCheckTextDichotomic(this.txtInput.getText(), language);
    		model.spellCheckTextLinear(this.txtInput.getText(), language);
    		
    	} catch(Exception e) {
    		
    		this.txtOutput.setText("Error occured reading the dictionary, re-enter the text");
    		return;
    	}
    	for(String s : errors) {
    		this.txtOutput.appendText(s+"\n");
    	}
    	this.txtOperationTimeLinear.setText("Linear spell check completed in "+model.getTimeOperationLinear()+" seconds");
    	this.txtOperationTimeDichotomic.setText("Dichotomic spell check completed in "+model.getTimeOperationDichotomic()+" seconds");
    	this.txtOperationTime.setText("Spell check completed in "+model.getTimeOperation()+" seconds");
    	this.txtNumberOfErrors.setText("The text contains "+errors.size()+" errors");
    }
    
    public void setModel(Model model) {
    	this.model = model;
    	this.boxLanguage.getItems().addAll("English", "Italian");
    }	

    @FXML
    void initialize() {
    	assert txtInput != null : "fx:id=\"txtInput\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtOutput != null : "fx:id=\"txtOutput\" was not injected: check your FXML file 'Scene.fxml'.";
        assert boxLanguage != null : "fx:id=\"boxLanguage\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnSpellCheck != null : "fx:id=\"btnSpellCheck\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnClearText != null : "fx:id=\"btnClearText\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNumberOfErrors != null : "fx:id=\"txtNumberOfErrors\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtOperationTimeLinear != null : "fx:id=\"txtOperationTime\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtOperationTimeDichotomic != null : "fx:id=\"txtOperationTimeDichotomic\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtOperationTime != null : "fx:id=\"txtOperationTime\" was not injected: check your FXML file 'Scene.fxml'.";

    }
}

