package it.tiriguarda.controller.graphic;

import it.tiriguarda.controller.app.PrEPAppController;
import it.tiriguarda.domain.TipologiaPrEP;
import it.tiriguarda.exception.AnnullamentoPrEPException;
import it.tiriguarda.exception.DatabaseNonRaggiungibileException;
import it.tiriguarda.exception.UtenteNonLoggatoException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class SceltaPrEPGraphicController {
	
	@FXML private Button dailyButton;
	
	@FXML private Button onDemandButton;
	
	@FXML private Button annullaButton;
	
	@FXML private Button oldButton;
	
	@FXML private Button menuPrincipale;
	
	@FXML public void onDaily(ActionEvent event) {
		ViewDispatcher.mostraConfiguraPrEP(TipologiaPrEP.DAILY);
	}
	@FXML public void onOnDemand(ActionEvent event) {
		ViewDispatcher.mostraConfiguraPrEP(TipologiaPrEP.ON_DEMAND);
	}
	
	@FXML public void onOld(ActionEvent event) {
		ViewDispatcher.mostraVecchiaPrEP();
	}
	
	@FXML public void onAnnullaPrEP(ActionEvent event) {
		PrEPAppController controller = new PrEPAppController();
		try {
			controller.verificaStatoPrEP();
			ViewDispatcher.mostraConfermaAnnullamento();
		}catch(AnnullamentoPrEPException e) {
			ViewDispatcher.mostraErrore(e.getMessage());
			ViewDispatcher.mostraMenuPrincipale();
		}catch(UtenteNonLoggatoException e) {
			ViewDispatcher.mostraErrore(e.getMessage());
			ViewDispatcher.mostraLogin();
		}catch (DatabaseNonRaggiungibileException e) {
	        	ViewDispatcher.mostraErroreCriticoEChiudi(e.getMessage());
	        }
	}
	
	@FXML public void onMenuPrincipale(ActionEvent event) {
		ViewDispatcher.mostraMenuPrincipale();
	}
}
	
