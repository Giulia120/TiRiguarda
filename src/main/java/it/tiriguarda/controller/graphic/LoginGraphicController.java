package it.tiriguarda.controller.graphic;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import it.tiriguarda.controller.app.LoginAppController;
import it.tiriguarda.dto.CredenzialiBean;
import it.tiriguarda.exception.CredenzialiErrateException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginGraphicController {
	private static final Logger logger = Logger.getLogger(LoginGraphicController.class.getName());
	
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
			mostraErrore("Dati mancanti");
			return;
		}
		CredenzialiBean bean = new CredenzialiBean();
		bean.setUsername(usernameField.getText());
		bean.setPassword(passwordField.getText());
		
		try {
			LoginAppController appController = new LoginAppController();
			appController.effettuaLogin(bean);
		} catch (CredenzialiErrateException e) {
			mostraErrore(e.getMessage());
			usernameField.setText(null);
			passwordField.setText(null);
		}
	}
	
	 @FXML
		public void onRegistratiButton(ActionEvent event) {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/it/tiriguarda/view/Registrazione.fxml"));
				Parent vistaMenuPrincipale = loader.load();
				Stage finestra = (Stage) ((Node) event.getSource()).getScene().getWindow();
				finestra.setScene(new Scene(vistaMenuPrincipale));
				finestra.show();
			}catch (IOException e){
				logger.log(Level.SEVERE, "Errore nel caricamento della schermata Registrazione.", e);
			}
	 	}
	 
	 private void mostraErrore(String messaggio) {
		 Alert alert = new Alert(AlertType.ERROR);
	   	 alert.setTitle("Errore di Validazione");
	   	 alert.setHeaderText(null);
	   	 alert.setContentText(messaggio);
	   	 alert.showAndWait();
	 }
}
