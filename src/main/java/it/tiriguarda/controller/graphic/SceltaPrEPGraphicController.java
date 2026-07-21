package it.tiriguarda.controller.graphic;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import it.tiriguarda.controller.app.AnnullaPrEPController;
import it.tiriguarda.exception.PrEPAnnullataException;
import it.tiriguarda.exception.PrEPNonEsistenteException;
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

public class SceltaPrEPGraphicController {
	private static final Logger logger = Logger.getLogger(SceltaPrEPGraphicController.class.getName());
	
	@FXML
	private Button dailyButton;
	
	@FXML
	private Button onDemandButton;
	
	@FXML
	private Button annullaButton;
	
	@FXML
	private Button menuPrincipale;
	
	@FXML
	public void onDaily(ActionEvent event) {
		//da fare
	}
	@FXML
	public void onOnDemand(ActionEvent event) {
		//da fare
	}
	
	@FXML
	public void onAnnullaPrEP(ActionEvent event) {
		AnnullaPrEPController controller = new AnnullaPrEPController();
		try {
			controller.verificaStatoPrEP();
			apriConfermaAnnullamento(event);
		}catch(PrEPNonEsistenteException e) {
			errore(e.getMessage());
		}catch(PrEPAnnullataException e) {
			errore(e.getMessage());
		} catch(TiRiguardaException e) {
	        errore(e.getMessage());
		}catch(Exception e) {
			logger.log(Level.SEVERE, "Errore nel caricamento della schermata di conferma.", e);
		}
	}
	
	private void apriConfermaAnnullamento(ActionEvent event) throws IOException{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/it/tiriguarda/view/ConfermaAnnullamneto.fxml"));
		Parent vistaConferma = loader.load();
		Stage finestra = (Stage) ((Node) event.getSource()).getScene().getWindow();
		
		finestra.setScene(new Scene(vistaConferma));
		finestra.show();
	}
	
	private void errore(String messaggio) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Errore");
        alert.setHeaderText(null);
        alert.setContentText(messaggio);
        alert.showAndWait();
	}
	
	@FXML
	public void onMenuPrincipale(ActionEvent event) {
		try {
			apriMenuPrincipale(event);
		}catch (Exception e){
			logger.log(Level.SEVERE, "Errore nel caricamento della schermata Menu Principale.", e);
		}
	}
	
	private void apriMenuPrincipale(ActionEvent event) throws IOException{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/it/tiriguarda/view/MenuPrincipale.fxml"));
		Parent vistaMenuPrincipale = loader.load();
		Stage finestra = (Stage) ((Node) event.getSource()).getScene().getWindow();
		
		finestra.setScene(new Scene(vistaMenuPrincipale));
		finestra.show();
	}
}
	
