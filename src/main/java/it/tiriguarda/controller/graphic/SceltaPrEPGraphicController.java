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
			//errore
		}
	}
	
	private void apriConfermaAnnullamento(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/it/tiriguarda/view/ConfermaAnnullamneto.fxml"));
		Parent vistaConferma = loader.load();
		Stage finestra = (Stage) ((Node) event.getSource()).getScene().getWindow();
		
		finestra.setScene(new Scene(vistaConferma));
		finestra.show();
	}
	
	@FXML
	public void onMenuPrincipale(ActionEvent event) {
		
	}
	
}
	
