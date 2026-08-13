package it.tiriguarda.controller.graphic;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import it.tiriguarda.controller.app.ConfiguraPrEPAppController;
import it.tiriguarda.domain.TipologiaPrEP;
import it.tiriguarda.dto.ProtocolloPrEPBean;
import it.tiriguarda.exception.DatabaseNonRaggiungibileException;
import it.tiriguarda.exception.DatiIncompletiException;
import it.tiriguarda.exception.ProtocolloAttivoException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ConfiguraPrEPGraphicController {
	@FXML private DatePicker dataInizioPrEPPicker;
	
	@FXML private CheckBox checkSMS;
	
	@FXML private Button confermaButton;
	
	@FXML private Button backButton;
	
	@FXML private TextField orarioPrEP;
	
	@FXML private Label tipoPrEPLabel;
	
	private TipologiaPrEP tipoPrEP;

	public void inizializza(TipologiaPrEP tipoPrEP) {
	    this.tipoPrEP = tipoPrEP;
	    tipoPrEPLabel.setText(tipoPrEP.toString().replace("_", " "));
	}
	
	@FXML public void onConfermaPrEP() {
		 try {
			 ProtocolloPrEPBean bean = new ProtocolloPrEPBean();
			 bean.setTipoPrEP(tipoPrEP);
			 bean.setDataInizio(dataInizioPrEPPicker.getValue());
       
			 String oraInserita = orarioPrEP.getText();
			 LocalTime ora = LocalTime.parse(oraInserita);
        
			 bean.setOrario(ora);
        
			 bean.setRicevereSMS(checkSMS.isSelected());
        
			 ConfiguraPrEPAppController controller = new ConfiguraPrEPAppController();
        	controller.configuraPrEP(bean);
			ViewDispatcher.mostraSuccesso("PrEP registrata con successo! Ricordati di seguire correttamente il protocollo. Visita la sezione INFORMAZIONI per maggiori info.");
			
		 }catch(DateTimeParseException e) {
			 ViewDispatcher.mostraErrore("Formato orario non valido. Inserisci l'orario nel formato HH:mm.");
			 orarioPrEP.setText(null);
		 }catch(ProtocolloAttivoException e) {
			ViewDispatcher.mostraErrore(e.getMessage());
			ViewDispatcher.mostraPrEP();
		}catch(DatiIncompletiException e) {
			ViewDispatcher.mostraErrore(e.getMessage());
		}catch (DatabaseNonRaggiungibileException e) {
        	ViewDispatcher.mostraErroreCriticoEChiudi(e.getMessage());
        }
	}
	
	
	@FXML public void onBackButton(ActionEvent event) {
		ViewDispatcher.mostraPrEP();
	 }
	
}
