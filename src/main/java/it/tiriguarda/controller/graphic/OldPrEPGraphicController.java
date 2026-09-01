package it.tiriguarda.controller.graphic;

import it.tiriguarda.controller.app.PrEPAppController;
import it.tiriguarda.domain.TipologiaPrEP;
import it.tiriguarda.dto.OldProtocolloPrEPBean;
import it.tiriguarda.exception.DataFuturaException;
import it.tiriguarda.exception.DatiIncompletiException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;

public class OldPrEPGraphicController {
	
	@FXML private Button confermaButton;
	@FXML private DatePicker dataInizioPrEPPicker;
	@FXML private DatePicker dataFinePrEPPicker;
	@FXML private Button backButton;
	@FXML private RadioButton dailyRadio;
	@FXML private RadioButton onDemandRadio;
	
	@FXML
	public void onBackButton() {
		ViewDispatcher.mostraMenuPrincipale();
	}
	
	@FXML
	public void onConfermaPrEP() {
		try {
			OldProtocolloPrEPBean bean = new OldProtocolloPrEPBean();
			bean.setDataInizio(dataInizioPrEPPicker.getValue());
			bean.setDataFine(dataFinePrEPPicker.getValue());
			if (dailyRadio.isSelected()) {
				bean.setTipoPrEP(TipologiaPrEP.DAILY);
			}else {
				bean.setTipoPrEP(TipologiaPrEP.ON_DEMAND);
			}
			PrEPAppController controller = new PrEPAppController();
			controller.configuraVecchiaPrEP(bean);
			ViewDispatcher.mostraSuccesso("PrEP registrata con successo!");
			
		}catch (DatiIncompletiException e ) {
			ViewDispatcher.mostraErrore(e.getMessage());
		}catch (DataFuturaException e ) {
			ViewDispatcher.mostraErrore(e.getMessage());
			dataInizioPrEPPicker.setValue(null);
			dataFinePrEPPicker.setValue(null);
		}
	}
}
