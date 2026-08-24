package it.tiriguarda.controller.graphic;

import java.time.LocalDate;
import java.time.ZoneId;
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
import it.tiriguarda.exception.UtenteNonLoggatoException;
import it.tiriguarda.logic.observer.RicalcoloSMSPrEP;
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
		        new RicalcoloSMSPrEP(appController);

		        RapportoBean beanAggiornato = appController.valutaRischio(bean);
		            
		            if (beanAggiornato.getRischio() == LivelloRischio.NULLO) {

		                appController.salvaRapportoDefinitivo(beanAggiornato);
		                ViewDispatcher.mostraSuccesso("Rapporto registrato con successo!");
                        
		            } else {
		                    if (beanAggiornato.getDataFinePeriodoFinestra().isAfter(LocalDate.now(ZoneId.systemDefault()))) {
		                        ViewDispatcher.mostraSchermataSMSRapporto(beanAggiornato);
		                    } else {
		                        ViewDispatcher.mostraInfoRapporto(beanAggiornato);
		                    }
		            }
		     } catch (DataFuturaException e) {
		    	 ViewDispatcher.mostraErrore(e.getMessage());
		    	 dataRapportoPicker.setValue(null);
		     } catch (DatiIncompletiException e) {
		    	 ViewDispatcher.mostraErrore(e.getMessage());
		     } catch (DatabaseNonRaggiungibileException e) {
		    	 ViewDispatcher.mostraErroreCriticoEChiudi(e.getMessage());
		     }catch (UtenteNonLoggatoException e) {
		    	 ViewDispatcher.mostraErrore(e.getMessage());
                 ViewDispatcher.mostraLogin();
		     }
	 }
	 @FXML
		public void onMenuPrincipale(ActionEvent event) {
		 ViewDispatcher.mostraMenuPrincipale();
		 }
}
