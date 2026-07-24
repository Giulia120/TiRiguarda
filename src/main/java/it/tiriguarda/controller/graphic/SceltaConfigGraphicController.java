package it.tiriguarda.controller.graphic;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import it.tiriguarda.config.AppConfig;
import it.tiriguarda.config.AppMode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class SceltaConfigGraphicController {
	private static final Logger logger = Logger.getLogger(SceltaConfigGraphicController.class.getName());
	
	@FXML
	private Button demoButton;
	@FXML
	private Button fullButton;
	@FXML
	public void onDemoButton(ActionEvent event) throws IOException {
		AppConfig.setCurrentMode(AppMode.DEMO);
		try {
			apriLoginPage(event);
		}catch (Exception e){
			logger.log(Level.SEVERE, "Errore nel caricamento della schermata di Login.", e);
		}
	}
	
	@FXML
	public void onFullButton(ActionEvent event) throws IOException {
		AppConfig.setCurrentMode(AppMode.FULL);
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
	
}
