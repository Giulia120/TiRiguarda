package it.tiriguarda.controller.graphic;

import it.tiriguarda.controller.app.AnnullaPrEPController;
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
	
	@FXML
	private Button DailyButton;
	
	@FXML
	private Button OnDemandButton;
	
	@FXML
	private Button AnnullaButton;
	
	@FXML
	private Button menuPrincipale;
	
	@FXML
	public void onDaily(ActionEvent event) {
		
	}
	@FXML
	public void onOnDemand(ActionEvent event) {
		
	}
	
	@FXML
	public void onAnnullaPrEP(ActionEvent event) {
		
		AnnullaPrEPController controller = new AnnullaPrEPController();
		
		try {
			controller.verificaStatoPrEP();
			apriConfermaAnnullamento(event);
			
		}catch(Exception e) {
			errore("Impossibile annullare PrEP: Protocollo non eisstente o gia annullato.");
		}
	}
	
	private void apriConfermaAnnullamento(ActionEvent event) throws Exception{
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
	
}
	
