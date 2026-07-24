package it.tiriguarda.controller.graphic;

import it.tiriguarda.controller.app.AnnullaPrEPAppController;
import it.tiriguarda.exception.PrEPAnnullataException;
import it.tiriguarda.exception.PrEPNonEsistenteException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class SceltaPrEPGraphicController {
	
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
		AnnullaPrEPAppController controller = new AnnullaPrEPAppController();
		try {
			controller.verificaStatoPrEP();
			ViewDispatcher.mostraConfermaAnnullamento();
		}catch(PrEPNonEsistenteException | PrEPAnnullataException e) {
			ViewDispatcher.mostraErrore(e.getMessage());
			ViewDispatcher.mostraMenuPrincipale();
			}
	}
	
	@FXML
	public void onMenuPrincipale(ActionEvent event) {
		ViewDispatcher.mostraMenuPrincipale();
	}
}
	
