package it.tiriguarda.controller.graphic;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import it.tiriguarda.controller.app.RegistraUtenteAppController;
import it.tiriguarda.dto.UtenteBean;
import it.tiriguarda.exception.DatiIncompletiException;
import it.tiriguarda.exception.UsernameEsistenteException;
import it.tiriguarda.util.SecurityUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegistraUtenteGraphicController {
	private static final Logger logger = Logger.getLogger(RegistraUtenteGraphicController.class.getName());
	
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
		if (usernameField.getText() == null || passwordField.getText() == null || telefonoField.getText() == null) {
			mostraErrore("Dati mancanti");
			return;
		}
		UtenteBean bean = new UtenteBean();
		bean.setUsername(usernameField.getText());
		bean.setPassword(SecurityUtil.hashPassword(passwordField.getText()));
		bean.setNumeroTelefono(telefonoField.getText());
		try {
			RegistraUtenteAppController appController = new RegistraUtenteAppController();
			appController.registraUtente(bean);
		} catch (UsernameEsistenteException e) {
			mostraErrore(e.getMessage());
			usernameField.setText(null);
		}
		try {
			apriLoginPage(event);
		}catch (Exception e){
			logger.log(Level.SEVERE, "Errore nel caricamento della schermata di Login.", e);
		}
	}	
	
	 @FXML
		public void onLoginPage(ActionEvent event) {
			try {
				apriLoginPage(event);
			}catch (Exception e){
				logger.log(Level.SEVERE, "Errore nel caricamento della schermata di Login.", e);
			}
	 }	
		private void apriLoginPage(ActionEvent event) throws IOException {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/it/tiriguarda/view/Login.fxml"));
			Parent vistaMenuPrincipale = loader.load();
			Stage finestra = (Stage) ((Node) event.getSource()).getScene().getWindow();
			finestra.setScene(new Scene(vistaMenuPrincipale));
			finestra.show();
		} 
		
		private void mostraErrore(String messaggio) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
	        alert.setTitle("Errore");
	        alert.setHeaderText(null);
	        alert.setContentText(messaggio);
	        alert.showAndWait();
		}
}
