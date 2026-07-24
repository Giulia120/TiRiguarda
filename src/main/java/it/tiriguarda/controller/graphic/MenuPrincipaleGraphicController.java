package it.tiriguarda.controller.graphic;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MenuPrincipaleGraphicController {
	
	@FXML private Button testButton;
	@FXML private Button profiloButton;
	@FXML private Button riepilogoButton;
	@FXML private Button registraRapportoButton;
	@FXML private Button prEPButton;
	@FXML private Button questionarioButton;
	@FXML private Button infoButton;
	@FXML private Button logoutButton;

	
	@FXML
	private void onTestButton(ActionEvent event) {
		ViewDispatcher.mostraTest();
	}
	
	@FXML
	private void onProfiloButton(ActionEvent event) {
		ViewDispatcher.mostraProfilo();
	}
	
	@FXML
	private void onRiepilogoButton(ActionEvent event) {
		ViewDispatcher.mostraRiepilogo();
	}
	
	@FXML
	private void onRegistraRapportoButton(ActionEvent event) {
		ViewDispatcher.mostraRegistraRapporto();
	}
	
	@FXML
	private void onPrEPButton(ActionEvent event) {
		ViewDispatcher.mostraPrEP();
	}
	
	@FXML
	private void onInfoButton(ActionEvent event) {
		ViewDispatcher.mostraInfo();
	}
	
	@FXML
	private void onLogoutButton(ActionEvent event) {
		ViewDispatcher.mostraLogin();
	}
	
	@FXML
	private void onQuestionarioButton(ActionEvent event) {
		ViewDispatcher.mostraQuestionario();
	}
}