package it.tiriguarda.controller.graphic;

import it.tiriguarda.controller.app.PrEPAppController;
import it.tiriguarda.exception.DatabaseNonRaggiungibileException;
import it.tiriguarda.exception.UtenteNonLoggatoException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class AnnullaPrEPGraphicController {
	private PrEPAppController controller = new PrEPAppController();
	
	@FXML
	private Button siConfermaAnnullamento;
	
	@FXML
	private Button noConfermaAnnullamento;
	
	@FXML
	private Button backButton;
	
	@FXML
	public void onConfermaAnnullamento(ActionEvent event) {
		try{
			controller.annullaPrEP();
		    ViewDispatcher.mostraSuccesso("PrEP annullata con successo!");
		    } catch (DatabaseNonRaggiungibileException e) {
		    	ViewDispatcher.mostraErroreCriticoEChiudi(e.getMessage());
		    } catch (UtenteNonLoggatoException e) {
		    	ViewDispatcher.mostraErrore(e.getMessage());
                ViewDispatcher.mostraLogin();
		    }
	}
	@FXML
	public void onNoConfermaAnnullamento(ActionEvent event){
		ViewDispatcher.mostraMenuPrincipale();
	}
	
	@FXML
	public void onBackButton(ActionEvent event){
		ViewDispatcher.mostraPrEP();
	}
}
