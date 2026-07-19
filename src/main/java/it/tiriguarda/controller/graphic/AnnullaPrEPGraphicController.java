package it.tiriguarda.controller.graphic;

import it.tiriguarda.controller.app.AnnullaPrEPController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

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
			//schermata di successo
			
		}catch(Exception e) {
			//errore
		}
	}
	@FXML
	public void onNoConfermaAnnullamento(ActionEvent event) {
		
	}
	
	@FXML
	public void onMenuPrincipale(ActionEvent event) {
		
	}
}
