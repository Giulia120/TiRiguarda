package it.tiriguarda.controller.graphic;

import it.tiriguarda.service.SessionManager;
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

	private void eseguiSeLoggato(Runnable azione) {
		if (SessionManager.getInstance().getUtenteLoggato() == null) {
			ViewDispatcher.mostraErrore("Funzionalita' riservata ad utenti registrati");
		} else {
			azione.run();
		}
	}
	
	@FXML
	private void onTestButton(ActionEvent event) {
		eseguiSeLoggato(() -> ViewDispatcher.mostraTest());
	}
	
	@FXML
	private void onProfiloButton(ActionEvent event) {
		eseguiSeLoggato(() -> ViewDispatcher.mostraProfilo());
	}
	
	@FXML
	private void onRiepilogoButton(ActionEvent event) {
		eseguiSeLoggato(() -> ViewDispatcher.mostraRiepilogo());
	}
	
	@FXML
	private void onRegistraRapportoButton(ActionEvent event) {
		eseguiSeLoggato(() -> ViewDispatcher.mostraRegistraRapporto());
	}
	
	@FXML
	private void onPrEPButton(ActionEvent event) {
		eseguiSeLoggato(() -> ViewDispatcher.mostraPrEP());
	}

	@FXML
	private void onInfoButton(ActionEvent event) {
		ViewDispatcher.mostraInfo();
	}
	
	@FXML
	private void onQuestionarioButton(ActionEvent event) {
		ViewDispatcher.mostraQuestionario();
	}

	@FXML
	private void onLogoutButton(ActionEvent event) {
		SessionManager.getInstance().clearSessione();
		ViewDispatcher.mostraLogin();
	}
}