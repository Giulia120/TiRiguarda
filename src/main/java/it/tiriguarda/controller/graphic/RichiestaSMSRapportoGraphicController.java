package it.tiriguarda.controller.graphic;

import java.time.format.DateTimeFormatter;

import it.tiriguarda.controller.app.RegistraRapportoAppController;
import it.tiriguarda.domain.LivelloRischio;
import it.tiriguarda.dto.RapportoBean;
import it.tiriguarda.exception.DatabaseNonRaggiungibileException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class RichiestaSMSRapportoGraphicController {

	 @FXML
	 private Button siButton;
	 @FXML
	 private Button noButton;
	 @FXML
	 private Button annullaButton;
	 @FXML
	 private Label livelloRischioLabel;
	 @FXML 
	 private Label dataFinestraLabel;
	 
	 private RapportoBean beanInSospeso;
	 
	 public void inizializza(RapportoBean bean) {
		 this.beanInSospeso = bean;
		 if (bean.getDataFinePeriodoFinestra() != null) {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		        String dataFormattata = bean.getDataFinePeriodoFinestra().format(formatter);
		        dataFinestraLabel.setText(dataFormattata);
			}
			
			if(bean.getRischio() == LivelloRischio.ALTO) {
				 livelloRischioLabel.setText("è un alto rischio, quindi");
			} else if (bean.getRischio() == LivelloRischio.BASSO) {
				 livelloRischioLabel.setText("è un basso rischio, ma");
			}
	 }
	 
	 @FXML
	 private void onSiButton(){
		 salvaEConcludi();
		 System.out.println("Daje");
		 ViewDispatcher.mostraSuccesso();
	 }
	 
	 @FXML
	 private void onNoButton(ActionEvent event){
		 salvaEConcludi();
		 ViewDispatcher.mostraSuccesso();
	 }
	 
	 @FXML
	 private void onAnnullaButton() {
		 ViewDispatcher.mostraMenuPrincipale();
	 }
	 
	 private void salvaEConcludi() {
			try{ RegistraRapportoAppController appController = new RegistraRapportoAppController();
				appController.salvaRapportoDefinitivo(beanInSospeso);
				}catch (DatabaseNonRaggiungibileException e) {
		        	ViewDispatcher.mostraErrore(e.getMessage());
		        	ViewDispatcher.mostraSceltaConfig();
		        }
		}
}
