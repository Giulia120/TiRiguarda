package it.tiriguarda.controller.graphic;

import it.tiriguarda.controller.app.LoginAppController;
import it.tiriguarda.dto.CredenzialiBean;
import it.tiriguarda.exception.CredenzialiErrateException;
import it.tiriguarda.exception.DatabaseNonRaggiungibileException;
import it.tiriguarda.exception.DatiIncompletiException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginGraphicController {
	
	@FXML
	private Button loginButton;
	@FXML
	private Button registratiButton;
	@FXML
	private TextField usernameField;
	@FXML 
	private PasswordField passwordField;
	@FXML private Button noLoginButton;
	
	
	@FXML
	private void onLoginButton(ActionEvent event) {

		
		try {
			CredenzialiBean bean = new CredenzialiBean();
			bean.setUsername(usernameField.getText());
			bean.setPassword(passwordField.getText());
			
			LoginAppController appController = new LoginAppController();
			appController.effettuaLogin(bean);
			ViewDispatcher.mostraMenuPrincipale();
		} catch (DatiIncompletiException e) {
			ViewDispatcher.mostraErrore(e.getMessage());
		}catch (CredenzialiErrateException e) {
			ViewDispatcher.mostraErrore(e.getMessage());
			usernameField.setText(null);
			passwordField.setText(null);
		}catch (DatabaseNonRaggiungibileException e) {
        	ViewDispatcher.mostraErroreCriticoEChiudi(e.getMessage());
        }
	}
	
	 @FXML
		public void onRegistratiButton(ActionEvent event) {
			ViewDispatcher.mostraRegistrazione();
	 	}
	 
	 @FXML
	 public void onNoLoginButton(ActionEvent event) {
		 ViewDispatcher.mostraMenuPrincipale();
	 }
}
