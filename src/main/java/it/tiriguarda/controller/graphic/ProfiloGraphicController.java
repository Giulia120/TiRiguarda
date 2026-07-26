package it.tiriguarda.controller.graphic;

import it.tiriguarda.domain.SessoBiologico;
import it.tiriguarda.domain.Utente;
import it.tiriguarda.service.SessionManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ProfiloGraphicController {
	
	@FXML 
	private Button modificaPwd;
	@FXML
	private Button modificaTel;
	@FXML
	private Button modificaUser;
	@FXML
	private Label usernameLabel;
	@FXML
	private Label telLabel;
	@FXML
	private Label sessoLabel;
	
	@FXML
	public void initialize() {
		Utente utenteCorrente = SessionManager.getInstance().getUtenteLoggato();
		usernameLabel.setText(utenteCorrente.getUsername());
		telLabel.setText(utenteCorrente.getNumeroTelefono());
		if (utenteCorrente.getSessoBiologico() == SessoBiologico.FEMMINILE) {
			sessoLabel.setText("Femminile");
		} else {
			sessoLabel.setText("Maschile");
		}
	} 
	
	@FXML
	void onTornaMenuPrincipale(ActionEvent event) {
		ViewDispatcher.mostraMenuPrincipale();
	}
	
	@FXML
	void onModificaTel(ActionEvent event) {
		ViewDispatcher.mostraModificaTel();
	}
	
	@FXML
	void onModificaPwd(ActionEvent event) {
		ViewDispatcher.mostraModificaPwd();
	}
	
	
}
