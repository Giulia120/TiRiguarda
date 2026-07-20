package it.tiriguarda.controller.graphic;

import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class RichiestaSMSRapportoGraphicController {

	 @FXML
	 private Button SiButton;
	 @FXML
	 private Button NoButton;
	 @FXML 
	 private Label DataFinestraLabel;
	 
	 public void initData(Date dataFineFinestra) {
			if (dataFineFinestra != null) {
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				String dataFormattata = sdf.format(dataFineFinestra);
				DataFinestraLabel.setText(dataFormattata);
			}
		}
	 
	 
	 @FXML
	 private void onSiButton(){
		 System.out.println("Daje");
	 }
	 
	 @FXML
	 private void onNoButton(){
		 System.out.println("Daje");
	 }
}
