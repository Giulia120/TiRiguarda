package it.tiriguarda.controller.graphic;

import java.io.IOException;

import it.tiriguarda.controller.app.RegistraUtenteAppController;
import it.tiriguarda.dto.UtenteBean;
import it.tiriguarda.exception.DatiIncompletiException;
import it.tiriguarda.exception.UsernameEsistenteException;
import it.tiriguarda.util.SecurityUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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
	public void onRegistraUtenteButton(ActionEvent event) throws IOException, DatiIncompletiException {
		UtenteBean bean = new UtenteBean();
		bean.setUsername(usernameField.getText());
		String test = passwordField.getText();
		if (test.isBlank()) {
			test = "";
			bean.setPassword(test);
		} else {
			bean.setPassword(SecurityUtil.hashPassword(test));
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
