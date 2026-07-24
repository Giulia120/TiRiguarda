package it.tiriguarda.controller.graphic;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class InfoGraphicController {

	@FXML
	private Button backButton;
	
	@FXML
	public void onBackButton(ActionEvent event) {
		ViewDispatcher.mostraMenuPrincipale();
	}
}
