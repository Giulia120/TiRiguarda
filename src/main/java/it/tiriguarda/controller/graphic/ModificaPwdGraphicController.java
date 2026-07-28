package it.tiriguarda.controller.graphic;

import it.tiriguarda.controller.app.ModificaPwdAppController;
import it.tiriguarda.dto.CambioPwdBean;
import it.tiriguarda.exception.CredenzialiErrateException;
import it.tiriguarda.exception.DatabaseNonRaggiungibileException;
import it.tiriguarda.exception.DatiIncompletiException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ModificaPwdGraphicController {

    @FXML
    private PasswordField oldPwdField;
    @FXML
    private TextField newPwdField;
    @FXML
    private Button confermaButton;

    @FXML
    public void onConfermaButton(ActionEvent event) {
        try {
            CambioPwdBean bean = new CambioPwdBean();
            bean.setVecchiaPassword(oldPwdField.getText());
            bean.setNuovaPassword(newPwdField.getText());
            
            ModificaPwdAppController appController = new ModificaPwdAppController();
            appController.cambiaPassword(bean);
            
            ViewDispatcher.mostraProfilo(); 
            chiudiFinestra();
            
        } catch (DatiIncompletiException | CredenzialiErrateException e) {
            ViewDispatcher.mostraErrore(e.getMessage());

            oldPwdField.setText("");
            newPwdField.setText("");
        }catch (DatabaseNonRaggiungibileException e) {
        	chiudiFinestra();
        	ViewDispatcher.mostraErrore(e.getMessage());
        	ViewDispatcher.mostraSceltaConfig();
        }catch (IllegalStateException e) {
        	ViewDispatcher.mostraErrore(e.getMessage());
        	ViewDispatcher.mostraLogin();
        }
    }
    
    private void chiudiFinestra() {
        Stage stage = (Stage) oldPwdField.getScene().getWindow();
        stage.close();
    }
}