package it.tiriguarda.controller.graphic;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;

public class RegistraRapportoGraphicController {

	 @FXML
	 private DatePicker dataRapportoPicker;
	 @FXML
	 private CheckBox checkPenetrativo;
	 @FXML
	 private CheckBox checkOrale;
	 @FXML
	 private RadioButton radioNulla;
	 @FXML
	 private RadioButton radioCoInt;
	 @FXML
	 private RadioButton radioPreservativo;
	 @FXML
	 private Button confermaButton;
	 @FXML
	 private Button homeButton;
	 
	 @FXML
	 public void onConfermaRegistrazione(ActionEvent event) {
		 System.out.println("Bottone cliccato! La GUI comunica col Graphic Controller.");
		 //da implementare
	 }
	 @FXML
	 public void onTornaMenuPrincipale(ActionEvent event) {
		 System.out.println("Bottone cliccato! La GUI comunica col Graphic Controller.");
		 //da implementare
	 }
	
}
