package it.tiriguarda.controller.graphic;

import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MenuPrincipaleGraphicController {
	private static final Logger logger = Logger.getLogger(MenuPrincipaleGraphicController.class.getName());
	
	@FXML private Button testButton;
	@FXML private Button profiloButton;
	@FXML private Button riepilogoButton;
	@FXML private Button registraRapportoButton;
	@FXML private Button prEPButton;
	@FXML private Button questionarioButton;
	@FXML private Button infoButton;
	@FXML private Button logoutButton;

	private void cambiaSchermata(ActionEvent event, String fxmlPath) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
			Parent nuovaVista = loader.load();
			Stage finestra = (Stage) ((Node) event.getSource()).getScene().getWindow();
			
			finestra.setScene(new Scene(nuovaVista));
			finestra.show();
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Errore nel caricamento della schermata: " + fxmlPath, e);
		}
	}
	
	@FXML
	private void onTestButton(ActionEvent event) {
		cambiaSchermata(event, "/it/tiriguarda/view/Test.fxml");
	}
	
	@FXML
	private void onProfiloButton(ActionEvent event) {
		cambiaSchermata(event, "/it/tiriguarda/view/Profilo.fxml");
	}
	
	@FXML
	private void onRiepilogoButton(ActionEvent event) {
		cambiaSchermata(event, "/it/tiriguarda/view/Riepilogo.fxml");
	}
	
	@FXML
	private void onRegistraRapportoButton(ActionEvent event) {
		cambiaSchermata(event, "/it/tiriguarda/view/RegistraRapporto.fxml");
	}
	
	@FXML
	private void onPrEPButton(ActionEvent event) {
		cambiaSchermata(event, "/it/tiriguarda/view/SceltaPrEP.fxml");
	}
	
	@FXML
	private void onInfoButton(ActionEvent event) {
		cambiaSchermata(event, "/it/tiriguarda/view/Info.fxml");
	}
	
	@FXML
	private void onLogoutButton(ActionEvent event) {
		cambiaSchermata(event, "/it/tiriguarda/view/Login.fxml");
	}
	
	@FXML
	private void onQuestionarioButton(ActionEvent event) {
		cambiaSchermata(event,"/it/tiriguarda/view/Questionario.fxml");
	}
}