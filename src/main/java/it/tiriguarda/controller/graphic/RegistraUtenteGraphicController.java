package it.tiriguarda.controller.graphic;

import java.io.IOException;

import it.tiriguarda.controller.app.RegistraUtenteAppController;
import it.tiriguarda.domain.SessoBiologico;
import it.tiriguarda.dto.UtenteBean;
import it.tiriguarda.exception.DatiIncompletiException;
import it.tiriguarda.exception.UsernameEsistenteException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class RegistraUtenteGraphicController {
	
	@FXML 
	private TextField usernameField; 
	@FXML
	private PasswordField passwordField;
	@FXML
	private TextField telefonoField;
	@FXML
	private Button registraUtenteButton;
	@FXML
	private Button backButton;
	@FXML
	private ToggleGroup sessoBiologico;
	@FXML
	private RadioButton radioFemm;
	@FXML
	private RadioButton radioMasc;
	
	@FXML
	public void onRegistraUtenteButton(ActionEvent event) throws IOException, DatiIncompletiException {
		UtenteBean bean = new UtenteBean();
		if(usernameField.getText().isBlank() || passwordField.getText().isBlank() || sessoBiologico.getSelectedToggle() == null) {
			ViewDispatcher.mostraErrore("Tutti i campi sono obbligatori!");
            return;
		}
		bean.setUsername(usernameField.getText());
		bean.setPassword(passwordField.getText());
		if (radioFemm.isSelected()) {
			bean.setSessoBiologico(SessoBiologico.FEMMINILE);
		} else if (radioMasc.isSelected()) {
			bean.setSessoBiologico(SessoBiologico.MASCHILE);
		}
		bean.setNumeroTelefono(telefonoField.getText());
		try {
			RegistraUtenteAppController appController = new RegistraUtenteAppController();
			appController.registraUtente(bean);
			ViewDispatcher.mostraLogin();
		} catch (DatiIncompletiException e){
			ViewDispatcher.mostraErrore(e.getMessage());
		}catch (UsernameEsistenteException e) {
			ViewDispatcher.mostraErrore(e.getMessage());
			usernameField.setText(null);
		}
	}	
	
	 @FXML
		public void onLoginPage(ActionEvent event) {
		 ViewDispatcher.mostraLogin();
	 }	
}
