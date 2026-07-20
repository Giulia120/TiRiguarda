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

public class SuccessoGraphicController {
	private static final Logger logger = Logger.getLogger(SuccessoGraphicController.class.getName());
	
	 @FXML
	 private Button homeButton;

	 @FXML
	    public void onTornaMenuPrincipale(ActionEvent event) {
	        try {
	            FXMLLoader loader = new FXMLLoader(getClass().getResource("/it/tiriguarda/view/MenuPrincipale.fxml")); 
	            Parent nuovaVista = loader.load();
	            
	            Stage finestraAttuale = (Stage) ((Node) event.getSource()).getScene().getWindow();
	            finestraAttuale.setScene(new Scene(nuovaVista));
	            finestraAttuale.show();
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	            logger.log(Level.SEVERE, "Errore nel caricamento della schermata Home.", e);
	        }
	    }
}