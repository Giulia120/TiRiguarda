package it.tiriguarda.controller.graphic;

import it.tiriguarda.controller.app.ProfiloAppController;
import it.tiriguarda.dto.DatiProfiloBean;
import it.tiriguarda.exception.UtenteNonLoggatoException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ProfiloGraphicController {
	
	@FXML 
	private Button modificaPwd;
	@FXML
	private Button modificaTel;
	@FXML
	private Button modificaUser;
	@FXML
	private Label usernameLabel;
	@FXML
	private Label telLabel;
	@FXML
	private Label sessoLabel;
	
	@FXML
	public void initialize() {
		
		try {
			ProfiloAppController controller = new ProfiloAppController();
			DatiProfiloBean bean = controller.getDatiProfilo();
			
			usernameLabel.setText(bean.getUsername());
			telLabel.setText(bean.getNumTelefono());
			sessoLabel.setText(bean.getSesso().toString().toLowerCase());
			
		}catch (UtenteNonLoggatoException e) {
			ViewDispatcher.mostraErrore(e.getMessage());
			ViewDispatcher.mostraLogin();
		}
	} 
	
	@FXML
	void onTornaMenuPrincipale(ActionEvent event) {
		ViewDispatcher.mostraMenuPrincipale();
	}
	
	@FXML
	void onModificaTel(ActionEvent event) {
		ViewDispatcher.mostraModificaTel();
	}
	
	@FXML
	void onModificaPwd(ActionEvent event) {
		ViewDispatcher.mostraModificaPwd();
	}
	
	
}
