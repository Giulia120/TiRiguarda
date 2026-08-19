package it.tiriguarda.controller.graphic;

import java.time.format.DateTimeFormatter;

import it.tiriguarda.controller.app.GestioneSmsAppController;
import it.tiriguarda.dto.ProtocolloPrEPBean;
import it.tiriguarda.exception.DatabaseNonRaggiungibileException;
import it.tiriguarda.exception.UtenteNonLoggatoException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class RichiestaSMSPrEPGraphicController {
	@FXML private Button siButton;
	@FXML private Button noButton;
	@FXML private Label oraSmsLabel;
	@FXML private Label tipoPrEPLabel;
	@FXML private Button backButton;
	
	private ProtocolloPrEPBean protocolloBean;

	public void inizializza(ProtocolloPrEPBean protocolloBean) {
		this.protocolloBean = protocolloBean;
	    tipoPrEPLabel.setText(protocolloBean.getTipoPrEP().toString().replace("_", " "));
	    
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String oraFormattata = protocolloBean.getOrario().format(formatter);
        
        oraSmsLabel.setText(oraFormattata);
	}
	
	public void onSiButton(ActionEvent event) {
		 try {
			 GestioneSmsAppController controller = new GestioneSmsAppController();
			 controller.programmaPromemoriaPrEP();
			 ViewDispatcher.mostraSuccesso("Promemoria registrati con successo!");
		 }catch (DatabaseNonRaggiungibileException e) {
	        	ViewDispatcher.mostraErroreCriticoEChiudi(e.getMessage());
	     }catch (UtenteNonLoggatoException e) {
	    	 	ViewDispatcher.mostraErrore(e.getMessage());
	    	 	ViewDispatcher.mostraLogin();
	     }
	}
	
	public void onNoButton(ActionEvent event) {
		ViewDispatcher.mostraSuccesso("Protocollo registrato con successo!");
	}
	
	public void onBackButton(ActionEvent event) {
		ViewDispatcher.mostraConfiguraPrEP(protocolloBean.getTipoPrEP());
	}
}
