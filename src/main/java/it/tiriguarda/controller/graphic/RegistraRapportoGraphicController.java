package it.tiriguarda.controller.graphic;

import java.util.ArrayList;
import java.util.List;

import it.tiriguarda.controller.app.RegistraRapportoAppController;
import it.tiriguarda.domain.LivelloRischio;
import it.tiriguarda.domain.Precauzioni;
import it.tiriguarda.domain.TipoRapporto;
import it.tiriguarda.dto.RapportoBean;
import it.tiriguarda.exception.DataFuturaException;
import it.tiriguarda.exception.DatabaseNonRaggiungibileException;
import it.tiriguarda.exception.DatiIncompletiException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

public class RegistraRapportoGraphicController {
	 @FXML
	 private DatePicker dataRapportoPicker;
	 @FXML
	 private CheckBox checkPenetrativo;
	 @FXML
	 private CheckBox checkOrale;
	 @FXML
	 private ToggleGroup precauzioni;
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
	        try {
	        	RapportoBean bean = new RapportoBean();
		        
		        bean.setData(dataRapportoPicker.getValue());
		        
		        List<TipoRapporto> tipiSelezionati = new ArrayList<>();
		        if (checkPenetrativo.isSelected()) {
		            tipiSelezionati.add(TipoRapporto.PENETRATIVO);
		        }
		        if (checkOrale.isSelected()) {
		            tipiSelezionati.add(TipoRapporto.ORALE);
		        }
		        bean.setTipo(tipiSelezionati);
		        
		        Precauzioni precauzioneSelezionata = null;
		        if (radioPreservativo.isSelected()) {
		        	precauzioneSelezionata = Precauzioni.PRESERVATIVO;
		        } else if (radioCoInt.isSelected()) {
		        	precauzioneSelezionata = Precauzioni.COITO_INTERROTTO;
		        } else if (radioNulla.isSelected()) {
		        	precauzioneSelezionata = Precauzioni.NULLA;
		        }
		        bean.setPrecauzioniUsate(precauzioneSelezionata);
		        
		        RegistraRapportoAppController appController = new RegistraRapportoAppController();
	        	RapportoBean beanAggiornato = appController.valutaRischio(bean);
	            if (beanAggiornato.getRischio() != LivelloRischio.NULLO) {
	            	ViewDispatcher.mostraSchermataSMSRapporto(beanAggiornato);
	            } else {
	            	appController.salvaRapportoDefinitivo(beanAggiornato);
	            	ViewDispatcher.mostraSuccesso();
	            }
	        } catch (DatiIncompletiException e) {
	        	ViewDispatcher.mostraErrore(e.getMessage());
	        } catch (DataFuturaException e) {
	        	ViewDispatcher.mostraErrore(e.getMessage());
	            dataRapportoPicker.setValue(null);          
	        }catch (DatabaseNonRaggiungibileException e) {
	        	ViewDispatcher.mostraErrore(e.getMessage());
	        	ViewDispatcher.mostraSceltaConfig();
	        }catch(IllegalStateException e) {
	        	ViewDispatcher.mostraErrore(e.getMessage());
	        	ViewDispatcher.mostraLogin();
	        }
	 } 
	 @FXML
		public void onMenuPrincipale(ActionEvent event) {
		 ViewDispatcher.mostraMenuPrincipale();
		 }
}
