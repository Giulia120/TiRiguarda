package it.tiriguarda.controller.graphic;

import it.tiriguarda.controller.app.ModificaTelefonoAppController;
import it.tiriguarda.dto.CambioTelefonoBean;
import it.tiriguarda.exception.DatabaseNonRaggiungibileException;
import it.tiriguarda.exception.DatiIncompletiException;
import it.tiriguarda.exception.UtenteNonLoggatoException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ModificaTelefonoGraphicController {

    @FXML
    private TextField newTelField;
    @FXML
    private Button confermaButton;

    @FXML
    public void onConfermaButton(ActionEvent event) {
        try {
            CambioTelefonoBean bean = new CambioTelefonoBean();
            bean.setNuovoTelefono(newTelField.getText());
            
            ModificaTelefonoAppController appController = new ModificaTelefonoAppController();
            appController.cambiaTelefono(bean);
           
            ViewDispatcher.mostraProfilo();
            
            chiudiFinestra();
            
        }catch (DatiIncompletiException e) {
            ViewDispatcher.mostraErrore(e.getMessage());
        }catch (DatabaseNonRaggiungibileException e) {
        	chiudiFinestra();
        	ViewDispatcher.mostraErroreCriticoEChiudi(e.getMessage());
        }catch(UtenteNonLoggatoException e) {
        	ViewDispatcher.mostraErrore(e.getMessage());
        	ViewDispatcher.mostraLogin();
        }
    }
    
    private void chiudiFinestra() {
        Stage stage = (Stage) newTelField.getScene().getWindow();
        stage.close();
    }
}