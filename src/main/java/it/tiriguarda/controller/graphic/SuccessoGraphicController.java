package it.tiriguarda.controller.graphic;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class SuccessoGraphicController {
	
	 @FXML private Button homeButton;
	 @FXML private Label messaggio;

	 @FXML
	    public void onTornaMenuPrincipale(ActionEvent event) {
		 ViewDispatcher.mostraMenuPrincipale();
		 }

	 public void setMessaggio(String mess) {
		 messaggio.setText(mess);
	 }
}