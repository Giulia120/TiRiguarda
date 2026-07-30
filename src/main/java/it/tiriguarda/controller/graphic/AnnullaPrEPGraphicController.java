package it.tiriguarda.controller.graphic;

import it.tiriguarda.controller.app.AnnullaPrEPAppController;
import it.tiriguarda.exception.DatabaseNonRaggiungibileException;
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
		AnnullaPrEPAppController controller = new AnnullaPrEPAppController();
		try {
			controller.annullaPrEP();
			ViewDispatcher.mostraSuccesso();
			
		}catch(IllegalStateException e) {
			ViewDispatcher.mostraErrore(e.getMessage());
			ViewDispatcher.mostraLogin();
		}catch (DatabaseNonRaggiungibileException e) {
        	ViewDispatcher.mostraErroreCriticoEChiudi(e.getMessage());
        }
	}
	@FXML
	public void onNoConfermaAnnullamento(ActionEvent event){
		ViewDispatcher.mostraMenuPrincipale();
	}
	
	@FXML
	public void onMenuPrincipale(ActionEvent event){
		ViewDispatcher.mostraMenuPrincipale();
	}
}
