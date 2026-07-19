package it.tiriguarda.controller.graphic;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class SuccessoGraphicController {
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
	            
	            System.out.println("Cliccato: Tornata alla Home con successo!");
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	            System.out.println("Errore nel caricamento della schermata Home.");
	        }
	    }
}