package it.tiriguarda.controller.graphic;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import it.tiriguarda.controller.app.GestioneSmsAppController;
import it.tiriguarda.controller.app.RegistraRapportoAppController;
import it.tiriguarda.domain.LivelloRischio;
import it.tiriguarda.domain.StatoSms;
import it.tiriguarda.domain.TipoSms;
import it.tiriguarda.dto.RapportoBean;
import it.tiriguarda.dto.SmsBean;
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
		 SmsBean bean = new SmsBean();
		 bean.setTesto("[PROMEMORIA]: È ora di fare il test!");
		 //LocalDateTime dataEOra = LocalDateTime.of(beanInSospeso.getDataFinePeriodoFinestra(), LocalTime.of(10, 00));
		 LocalDateTime dataEOra = LocalDateTime.now();
		 bean.setDataSpedizione(dataEOra);
		 bean.setTipo(TipoSms.TEST);
		 bean.setStato(StatoSms.DA_INVIARE);
		 try {
			 GestioneSmsAppController controller = new GestioneSmsAppController();
			 controller.programmaSms(bean);
		 }catch (DatabaseNonRaggiungibileException e) {
	        	ViewDispatcher.mostraErrore(e.getMessage());
	        	ViewDispatcher.mostraSceltaConfig();
	     }catch (IllegalStateException e) {
	    	 	ViewDispatcher.mostraErrore(e.getMessage());
	    	 	ViewDispatcher.mostraLogin();
	     }
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
