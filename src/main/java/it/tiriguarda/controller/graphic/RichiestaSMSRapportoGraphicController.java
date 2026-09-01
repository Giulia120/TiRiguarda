package it.tiriguarda.controller.graphic;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import it.tiriguarda.controller.app.GestioneSmsAppController;
import it.tiriguarda.controller.app.RegistraRapportoAppController;
import it.tiriguarda.domain.StatoSms;
import it.tiriguarda.domain.TipoSms;
import it.tiriguarda.dto.RapportoBean;
import it.tiriguarda.dto.SmsBean;
import it.tiriguarda.exception.DatabaseNonRaggiungibileException;
import it.tiriguarda.exception.UtenteNonLoggatoException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class RichiestaSMSRapportoGraphicController {
	
	 @FXML private Button siButton;
	 @FXML private Button noButton;
	 @FXML private Button annullaButton;
	 @FXML private Label livelloRischioLabel;
	 @FXML private Label dataFinestraLabel;
	 
	 private RapportoBean beanInSospeso;
	 
	 public void inizializza(RapportoBean bean) {
		 this.beanInSospeso = bean;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String dataFormattata = bean.getDataFinePeriodoFinestra().format(formatter);
		dataFinestraLabel.setText(dataFormattata);
		livelloRischioLabel.setText(String.format("e' un %s rischio", bean.getRischio().toString()));
	 }
	 
	 @FXML
	 private void onSiButton(){
		 SmsBean bean = new SmsBean();
		 bean.setTesto("[PROMEMORIA]: E' ora di fare il test!");
		 LocalDateTime dataEOra = LocalDateTime.of(beanInSospeso.getDataFinePeriodoFinestra(), LocalTime.of(10, 00));
		 bean.setDataSpedizione(dataEOra);
		 bean.setTipo(TipoSms.TEST);
		 bean.setStato(StatoSms.DA_INVIARE);
		 
		 siButton.setDisable(true);
		 noButton.setDisable(true);

		 salvaEConcludi();
			 try {
				 GestioneSmsAppController controller = new GestioneSmsAppController();
				 controller.programmaSms(bean);
				 ViewDispatcher.mostraSuccesso("Rapporto e promemoria registrati con successo! Ricorda di fare il test!");
			 } catch (DatabaseNonRaggiungibileException e) {
                 ViewDispatcher.mostraErroreCriticoEChiudi(e.getMessage());
		     } catch (UtenteNonLoggatoException e) {
                 ViewDispatcher.mostraErrore(e.getMessage());
                 ViewDispatcher.mostraLogin();
		     }
	 }
	 
	 @FXML
	 private void onNoButton(){
		 salvaEConcludi();
		 ViewDispatcher.mostraSuccesso("Rapporto registrato con successo! Ricorda di fare il test!");
	 }
	 
	 private void salvaEConcludi() {
		RegistraRapportoAppController appController = new RegistraRapportoAppController();
		appController.salvaRapportoDefinitivo(beanInSospeso);
	 }
	 
	 @FXML
	 private void onAnnullaButton() {
		 ViewDispatcher.mostraMenuPrincipale();
	 }
	 
}
