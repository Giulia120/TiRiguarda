package it.tiriguarda.controller.graphic;

import it.tiriguarda.controller.app.RegistraRapportoAppController;
import it.tiriguarda.dto.RapportoBean;
import it.tiriguarda.exception.DatabaseNonRaggiungibileException;
import it.tiriguarda.exception.UtenteNonLoggatoException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class InfoRapportoGraphicController {
	private RegistraRapportoAppController appController = new RegistraRapportoAppController();
	 @FXML private Button hoCapitoButton;
	 @FXML private Button annullaButton;
	 @FXML private Label infoRapportoLabel;
	 
	 private RapportoBean beanInSospeso;
	 
	 public void inizializza(RapportoBean bean) {
		 this.beanInSospeso = bean;
		 infoRapportoLabel.setText(String.format("ATTENZIONE: il tuo rapporto ha un %s rischio. Il periodo finestra è già terminato: quindi ti consigliamo di fare un test prima possibile!", bean.getRischio().toString()));
	 }
	 
	 @FXML
	 private void onAnnullaButton(ActionEvent event) {
		 ViewDispatcher.mostraMenuPrincipale();
	 }
	 
	 @FXML
	 private void onHoCapitoButton(ActionEvent event){
		 try{
			appController.salvaRapportoDefinitivo(beanInSospeso);
			ViewDispatcher.mostraSuccesso("Rapporto registrato con successo!");
			}catch (DatabaseNonRaggiungibileException e) {
	        	ViewDispatcher.mostraErroreCriticoEChiudi(e.getMessage());
	        }catch(UtenteNonLoggatoException e) {
	        	ViewDispatcher.mostraErrore(e.getMessage());
	        	ViewDispatcher.mostraLogin();
	        }
	 }
		 
}
