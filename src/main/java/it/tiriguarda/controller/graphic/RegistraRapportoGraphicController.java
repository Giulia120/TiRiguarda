package it.tiriguarda.controller.graphic;

import java.util.ArrayList;
import java.util.List;

import it.tiriguarda.controller.app.RegistraRapportoAppController;
import it.tiriguarda.domain.LivelloRischio;
import it.tiriguarda.domain.Precauzioni;
import it.tiriguarda.domain.TipoRapporto;
import it.tiriguarda.dto.RapportoBean;
import it.tiriguarda.exception.DataFuturaException;
import it.tiriguarda.exception.DatiIncompletiException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;

public class RegistraRapportoGraphicController {
	 @FXML
	 private DatePicker dataRapportoPicker;
	 @FXML
	 private CheckBox checkPenetrativo;
	 @FXML
	 private CheckBox checkOrale;
	 @FXML
	 private RadioButton radioNulla;
	 @FXML
	 private RadioButton radioCoInt;
	 @FXML
	 private RadioButton radioPreservativo;
	 @FXML
	 private Button confermaButton;
	 @FXML
	 private Button backButton;
	 
	 @FXML
	 public void onConfermaRegistrazione(ActionEvent event) {
		 if (dataRapportoPicker.getValue() == null) {
			 ViewDispatcher.mostraErrore("Devi selezionare la data del rapporto!");
	            return;
	        }
	        
	        if (!checkPenetrativo.isSelected() && !checkOrale.isSelected()) {
	        	ViewDispatcher.mostraErrore("Devi selezionare almeno un tipo di rapporto!");
	            return;
	        }
	        
	        if (!radioNulla.isSelected() && !radioCoInt.isSelected() && !radioPreservativo.isSelected()) {
	        	ViewDispatcher.mostraErrore("Devi selezionare le precauzioni usate!");
	            return;
	        }
	        
	        RapportoBean bean = new RapportoBean();
	        
	        java.sql.Date dataConvertita = java.sql.Date.valueOf(dataRapportoPicker.getValue());
	        bean.setData(dataConvertita);
	        
	        List<TipoRapporto> tipiSelezionati = new ArrayList<>();
	        if (checkPenetrativo.isSelected()) {
	            tipiSelezionati.add(TipoRapporto.PENETRATIVO);
	        }
	        if (checkOrale.isSelected()) {
	            tipiSelezionati.add(TipoRapporto.ORALE);
	        }
	        bean.setTipo(tipiSelezionati);
	        
	        if (radioPreservativo.isSelected()) {
	            bean.setPrecauzioniUsate(Precauzioni.PRESERVATIVO);
	        } else if (radioCoInt.isSelected()) {
	            bean.setPrecauzioniUsate(Precauzioni.COITO_INTERROTTO);
	        } else if (radioNulla.isSelected()) {
	            bean.setPrecauzioniUsate(Precauzioni.NULLA);
	        }
	        
	        try {
	        	RegistraRapportoAppController appController = RegistraRapportoAppController.getInstance();
	        	RapportoBean beanAggiornato = appController.registraRapporto(bean);
	            if (beanAggiornato.getRischio() != LivelloRischio.NULLO) {
	            	ViewDispatcher.mostraSchermataSMSRapporto(beanAggiornato);
	            } else {
	            	ViewDispatcher.mostraSuccesso();
	            }
	            
	        } catch (DatiIncompletiException e) {
	        	ViewDispatcher.mostraErrore(e.getMessage());
	        } catch (DataFuturaException e) {
	        	ViewDispatcher.mostraErrore(e.getMessage());
	            dataRapportoPicker.setValue(null);          
	        }
	 } 
	 @FXML
		public void onMenuPrincipale(ActionEvent event) {
		 ViewDispatcher.mostraMenuPrincipale();
		 }
}
