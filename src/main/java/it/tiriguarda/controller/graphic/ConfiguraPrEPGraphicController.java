package it.tiriguarda.controller.graphic;

import java.time.LocalTime;

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
import javafx.scene.control.TextField;

public class ConfiguraPrEPGraphicController {
	@FXML
	private DatePicker dataInizioPrEPPicker;
	
	@FXML
	private CheckBox checkSMS;
	
	@FXML
	private Button confermaButton;
	
	@FXML
	private Button backButton;
	
	@FXML
	private TextField orarioPrEP;
	
	private TipologiaPrEP tipoPrEP;

	public void initData(TipologiaPrEP tipoPrEP) {
	    this.tipoPrEP = tipoPrEP;
	}
	
	@FXML
	public void onConfermaPrEP() {
		if(dataInizioPrEPPicker.getValue() == null) {
			ViewDispatcher.mostraErrore("Inserire la data di inizio assunzione!");
			return;
		}
		if(orarioPrEP.getText().isBlank()) {
			ViewDispatcher.mostraErrore("Inserire la data di inizio assunzione!");
			return;
		}
		ProtocolloPrEPBean bean = new ProtocolloPrEPBean();
		bean.setTipoPrEP(tipoPrEP);
        bean.setDataInizio(dataInizioPrEPPicker.getValue());
       
        String oraInserita = orarioPrEP.getText();
        LocalTime ora = LocalTime.parse(oraInserita);
        
        bean.setOrario(ora);
        
        if(checkSMS.isSelected()) {
        	bean.setRicevereSMS(true);
        }
        else {
        	bean.setRicevereSMS(false);
        }
        
        ConfiguraPrEPAppController controller = new ConfiguraPrEPAppController();
        try {
        	controller.configuraPrEP(bean);
			ViewDispatcher.mostraSuccesso();
			
		}catch(ProtocolloAttivoException e) {
			ViewDispatcher.mostraErrore(e.getMessage());
			ViewDispatcher.mostraPrEP();
		}catch(DatiIncompletiException e) {
			ViewDispatcher.mostraErrore(e.getMessage());
		}catch (DatabaseNonRaggiungibileException e) {
        	ViewDispatcher.mostraErroreCriticoEChiudi(e.getMessage());
        }
	}
	
	
	@FXML
	public void onMenuPrincipale(ActionEvent event) {
		ViewDispatcher.mostraMenuPrincipale();
	 }
	
}
