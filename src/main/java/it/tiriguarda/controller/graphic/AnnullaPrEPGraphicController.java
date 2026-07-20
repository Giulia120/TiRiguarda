package it.tiriguarda.controller.graphic;

import it.tiriguarda.controller.app.AnnullaPrEPController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class AnnullaPrEPGraphicController {
	
	@FXML
	private Button siConfermaAnnullamento;
	
	@FXML
	private Button noConfermaAnnullamento;
	
	@FXML
	private Button menuPrincipale;
	
	@FXML
	public void onConfermaAnnullamento(ActionEvent event) {
		
		AnnullaPrEPController controller = new AnnullaPrEPController();
		
		try {
			controller.annullaPrEP();
			apriSuccesso(event);
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("Errore nell'annullamento del protocollo.");
		}
	}
	@FXML
	public void onNoConfermaAnnullamento(ActionEvent event){
		try {
			apriMenuPrincipale(event);
		}catch (Exception e){
			e.printStackTrace();
			System.out.println("Errore nel caricamento della schermata.");
		}
	}
	
	@FXML
	public void onMenuPrincipale(ActionEvent event){
		try {
			apriMenuPrincipale(event);
		}catch (Exception e){
			e.printStackTrace();
			System.out.println("Errore nel caricamento della schermata.");
		}
	}
	
	private void apriMenuPrincipale(ActionEvent event) throws Exception{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/it/tiriguarda/view/MenuPrincipale.fxml"));
		Parent vistaMenuPrincipale = loader.load();
		Stage finestra = (Stage) ((Node) event.getSource()).getScene().getWindow();
		
		finestra.setScene(new Scene(vistaMenuPrincipale));
		finestra.show();
	}
	
	private void apriSuccesso(ActionEvent event) throws Exception{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/it/tiriguarda/view/Successo.fxml"));
		Parent vistaMenuPrincipale = loader.load();
		Stage finestra = (Stage) ((Node) event.getSource()).getScene().getWindow();
		
		finestra.setScene(new Scene(vistaMenuPrincipale));
		finestra.show();
	}
}
