package it.tiriguarda.controller.graphic;

import it.tiriguarda.controller.app.RegistraTestAppController;
import it.tiriguarda.domain.TipoTest;
import it.tiriguarda.dto.TestBean;
import it.tiriguarda.exception.DataFuturaException;
import it.tiriguarda.exception.DatiIncompletiException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;

public class RegistraTestGraphicController {
	@FXML
	private DatePicker dataRapportoPicker;
	@FXML
	private RadioButton radioRapido;
	@FXML
	private RadioButton radioPrelievo;
	@FXML
	private Button backButton; 
	
	@FXML
	 public void onConfermaRegistrazione(ActionEvent event) {
		 if (dataRapportoPicker.getValue() == null) {
			 ViewDispatcher.mostraErrore("Devi selezionare la data del test!");
	            return;
	        }
		 if (!radioRapido.isSelected() && !radioPrelievo.isSelected()) {
	        	ViewDispatcher.mostraErrore("Devi selezionare il tipo!");
	            return;
	        }
		 
		 TestBean bean = new TestBean();
		 
	     bean.setData(dataRapportoPicker.getValue());
	     
	     if (radioRapido.isSelected()) {
	            bean.setTipo(TipoTest.RAPIDO);
	        } else if (radioPrelievo.isSelected()) {
	            bean.setTipo(TipoTest.PRELIEVO);
	        }
	     
	     try {
	    	 RegistraTestAppController controller = new RegistraTestAppController();
	    	 controller.registraTest(bean);
	     } catch (DatiIncompletiException e) {
	        	ViewDispatcher.mostraErrore(e.getMessage());
	        } catch (DataFuturaException e) {
	        	ViewDispatcher.mostraErrore(e.getMessage());
	            dataRapportoPicker.setValue(null);          
	        }
	     ViewDispatcher.mostraSuccesso();
	}
	
	@FXML
	public void onMenuPrincipale(ActionEvent event) {
		ViewDispatcher.mostraMenuPrincipale();
		}
    
}
