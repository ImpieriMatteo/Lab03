/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.spellchecker;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.spellchecker.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class FXMLController {
	
	private Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label txtNumberOfErrors;

    @FXML
    private Label txtOperationTime;

    @FXML
    void handleClearText(ActionEvent event) {

    }

    @FXML
    void handleEnglish(ActionEvent event) {

    }

    @FXML
    void handleItaliano(ActionEvent event) {

    }

    @FXML
    void handleSpellCheck(ActionEvent event) {

    }
    
    public void setModel(Model model) {
    	this.model = model;
    }	

    @FXML
    void initialize() {
        assert txtNumberOfErrors != null : "fx:id=\"txtNumberOfErrors\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtOperationTime != null : "fx:id=\"txtOperationTime\" was not injected: check your FXML file 'Scene.fxml'.";

    }
}

