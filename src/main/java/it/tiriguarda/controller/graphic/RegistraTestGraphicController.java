package it.tiriguarda.controller.graphic;

import it.tiriguarda.controller.app.RegistraTestAppController;
import it.tiriguarda.domain.TipoTest;
import it.tiriguarda.dto.TestBean;
import it.tiriguarda.exception.DataFuturaException;
import it.tiriguarda.exception.DatabaseNonRaggiungibileException;
import it.tiriguarda.exception.DatiIncompletiException;
import it.tiriguarda.exception.FileSystemNonRaggiungibileException;
import it.tiriguarda.exception.UtenteNonLoggatoException;
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
	     try {
	    	 TestBean bean = new TestBean();
			 
		     bean.setData(dataRapportoPicker.getValue());
		     
		     TipoTest tipoTest = null;
		     if (radioRapido.isSelected()) {
		    	 tipoTest = TipoTest.RAPIDO;
		        } else if (radioPrelievo.isSelected()) {
		        	tipoTest = TipoTest.PRELIEVO;
		        }
		     bean.setTipo(tipoTest);
		     RegistraTestAppController controller = new RegistraTestAppController();
	    	 controller.registraTest(bean);
	    	 ViewDispatcher.mostraSuccesso("Test registrato con successo!");
	     } catch (DatiIncompletiException e) {
	        	ViewDispatcher.mostraErrore(e.getMessage());
	        } catch (DataFuturaException e) {
	        	ViewDispatcher.mostraErrore(e.getMessage());
	            dataRapportoPicker.setValue(null);          
	        }catch (DatabaseNonRaggiungibileException | FileSystemNonRaggiungibileException e) {
	        	ViewDispatcher.mostraErroreCriticoEChiudi(e.getMessage());
	        }catch(UtenteNonLoggatoException e) {
	        	ViewDispatcher.mostraErrore(e.getMessage());
	        	ViewDispatcher.mostraLogin();
	        }
	}
	
	@FXML
	public void onMenuPrincipale(ActionEvent event) {
		ViewDispatcher.mostraMenuPrincipale();
		}
    
}
