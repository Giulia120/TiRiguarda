package it.tiriguarda.controller.graphic;

import it.tiriguarda.controller.app.AnnullaPrEPAppController;
import it.tiriguarda.exception.DatabaseNonRaggiungibileException;
import it.tiriguarda.exception.UtenteNonLoggatoException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class AnnullaPrEPGraphicController {
	
	@FXML
	private Button siConfermaAnnullamento;
	
	@FXML
	private Button noConfermaAnnullamento;
	
	@FXML
	private Button backButton;
	
	@FXML
	public void onConfermaAnnullamento(ActionEvent event) {
		AnnullaPrEPAppController controller = new AnnullaPrEPAppController();
		siConfermaAnnullamento.setDisable(true);
		controller.annullaPrEP().thenRun(() -> {
			Platform.runLater(()-> {
				siConfermaAnnullamento.setDisable(false);
				ViewDispatcher.mostraSuccesso("PrEP annullata con successo!");
			});
		}).exceptionally(ex -> {
			Platform.runLater(() ->{
				siConfermaAnnullamento.setDisable(false);
				Throwable cause = ex.getCause() != null ? ex.getCause() : ex;
                
                if (cause instanceof DatabaseNonRaggiungibileException) {
                    ViewDispatcher.mostraErroreCriticoEChiudi(cause.getMessage());
                } else if (cause instanceof UtenteNonLoggatoException) {
                    ViewDispatcher.mostraErrore(cause.getMessage());
                    ViewDispatcher.mostraLogin();
                }
			});
			return null;
		});
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
