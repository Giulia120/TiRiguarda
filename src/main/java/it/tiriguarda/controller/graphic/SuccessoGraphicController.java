package it.tiriguarda.controller.graphic;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class SuccessoGraphicController {
	
	 @FXML
	 private Button homeButton;

	 @FXML
	    public void onTornaMenuPrincipale(ActionEvent event) {
		 ViewDispatcher.mostraMenuPrincipale();
		 }
}