package it.tiriguarda.controller.graphic;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class RichiestaSMSRapportoGraphicController {

	 @FXML
	 private Button siButton;
	 @FXML
	 private Button noButton;
	 @FXML 
	 private Label dataFinestraLabel;
	 
	 public void initData(LocalDate dataFineFinestra) {
			if (dataFineFinestra != null) {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		        String dataFormattata = dataFineFinestra.format(formatter);
		        dataFinestraLabel.setText(dataFormattata);
			}
		}
	 
	 
	 @FXML
	 private void onSiButton(){
		 System.out.println("Daje");
	 }
	 
	 @FXML
	 private void onNoButton(ActionEvent event){
		 ViewDispatcher.mostraSuccesso();
	 }
}
