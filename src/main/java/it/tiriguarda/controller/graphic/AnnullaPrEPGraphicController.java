package it.tiriguarda.controller.graphic;

import java.util.logging.Level;
import java.util.logging.Logger;

import it.tiriguarda.controller.app.AnnullaPrEPAppController;
import it.tiriguarda.exception.TiRiguardaException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class AnnullaPrEPGraphicController {
	private static final Logger logger = Logger.getLogger(AnnullaPrEPGraphicController.class.getName());
	
	@FXML
	private Button siConfermaAnnullamento;
	
	@FXML
	private Button noConfermaAnnullamento;
	
	@FXML
	private Button menuPrincipale;
	
	@FXML
	public void onConfermaAnnullamento(ActionEvent event) {
		AnnullaPrEPAppController controller = new AnnullaPrEPAppController();
		try {
			controller.annullaPrEP();
			apriSuccesso(event);
			
		}catch(TiRiguardaException e) {
			errore(e.getMessage());
		}
	}
	@FXML
	public void onNoConfermaAnnullamento(ActionEvent event){
		try {
			apriMenuPrincipale(event);
		}catch (Exception e){
			errore(e.getMessage());
			logger.log(Level.SEVERE, "Errore nel caricamento della schermata principale.", e);
		}
	}
	
	@FXML
	public void onMenuPrincipale(ActionEvent event){
		try {
			apriMenuPrincipale(event);
		}catch (Exception e){
			errore(e.getMessage());
			logger.log(Level.SEVERE, "Errore nel caricamento della schermata principale.", e);
		}
	}
	private void apriMenuPrincipale(ActionEvent event) throws Exception{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/it/tiriguarda/view/MenuPrincipale.fxml"));
		Parent vistaMenuPrincipale = loader.load();
		Stage finestra = (Stage) ((Node) event.getSource()).getScene().getWindow();
		
		finestra.setScene(new Scene(vistaMenuPrincipale));
		finestra.show();
	}
	private void apriSuccesso(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/it/tiriguarda/view/Successo.fxml"));
		try {
			Parent vistaSuccesso = loader.load();
			Stage finestra = (Stage) ((Node) event.getSource()).getScene().getWindow();
			finestra.setScene(new Scene(vistaSuccesso));
			finestra.show();
		}catch(Exception e) {
			errore(e.getMessage());
			logger.log(Level.SEVERE, "Errore nel caricamento della schermata di successo.", e);
		}	
	}
	private void errore(String messaggio) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Errore");
        alert.setHeaderText(null);
        alert.setContentText(messaggio);
        alert.showAndWait();
	}
}
