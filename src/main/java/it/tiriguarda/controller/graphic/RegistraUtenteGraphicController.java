package it.tiriguarda.controller.graphic;

import java.io.IOException;

import it.tiriguarda.controller.app.RegistraUtenteAppController;
import it.tiriguarda.domain.SessoBiologico;
import it.tiriguarda.dto.UtenteBean;
import it.tiriguarda.exception.DatabaseNonRaggiungibileException;
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
		
		try {
			UtenteBean bean = new UtenteBean();
			
			bean.setUsername(usernameField.getText());
			bean.setPassword(passwordField.getText());
			
			SessoBiologico sessoSelezionato = null;
	        if (radioFemm.isSelected()) {
	            sessoSelezionato = SessoBiologico.FEMMINILE;
	        } else if (radioMasc.isSelected()) {
	            sessoSelezionato = SessoBiologico.MASCHILE;
	        }
	        bean.setSessoBiologico(sessoSelezionato);
			bean.setNumeroTelefono(telefonoField.getText());
			
			RegistraUtenteAppController appController = new RegistraUtenteAppController();
			appController.registraUtente(bean);
			ViewDispatcher.mostraLogin();
		} catch (DatiIncompletiException e){
			ViewDispatcher.mostraErrore(e.getMessage());
		}catch (UsernameEsistenteException e) {
			ViewDispatcher.mostraErrore(e.getMessage());
			usernameField.setText(null);
		}catch (DatabaseNonRaggiungibileException e) {
        	ViewDispatcher.mostraErrore(e.getMessage());
        	ViewDispatcher.mostraSceltaConfig();
        }
	}	
	
	 @FXML
		public void onLoginPage(ActionEvent event) {
		 ViewDispatcher.mostraLogin();
	 }	
}
