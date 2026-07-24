package it.tiriguarda.controller.graphic;

import it.tiriguarda.controller.app.LoginAppController;
import it.tiriguarda.dto.CredenzialiBean;
import it.tiriguarda.exception.CredenzialiErrateException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginGraphicController {
	
	@FXML
	private Button loginButton;
	@FXML
	private Button registratiButton;
	@FXML
	private TextField usernameField;
	@FXML 
	private PasswordField passwordField;
	
	
	@FXML
	private void onLoginButton(ActionEvent event) {
		if (usernameField.getText() == null || passwordField.getText() == null) {
			ViewDispatcher.mostraErrore("Dati mancanti");
			return;
		}
		CredenzialiBean bean = new CredenzialiBean();
		bean.setUsername(usernameField.getText());
		bean.setPassword(passwordField.getText());
		
		try {
			LoginAppController appController = new LoginAppController();
			appController.effettuaLogin(bean);
			ViewDispatcher.mostraMenuPrincipale();
		} catch (CredenzialiErrateException e) {
			ViewDispatcher.mostraErrore(e.getMessage());
			usernameField.setText(null);
			passwordField.setText(null);
		}
	}
	
	 @FXML
		public void onRegistratiButton(ActionEvent event) {
			ViewDispatcher.mostraRegistrazione();
	 	}
	 
}
