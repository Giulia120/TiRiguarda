package it.tiriguarda.controller.graphic;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CompletableFuture;

import it.tiriguarda.controller.app.GestioneSmsAppController;
import it.tiriguarda.controller.app.RegistraRapportoAppController;
import it.tiriguarda.domain.StatoSms;
import it.tiriguarda.domain.TipoSms;
import it.tiriguarda.dto.RapportoBean;
import it.tiriguarda.dto.SmsBean;
import it.tiriguarda.exception.DatabaseNonRaggiungibileException;
import it.tiriguarda.exception.UtenteNonLoggatoException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
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

		 salvaEConcludi().thenRun(() -> {
			 try {
				 GestioneSmsAppController controller = new GestioneSmsAppController();
				 controller.programmaSms(bean);
				 Platform.runLater(() -> {
				     ViewDispatcher.mostraSuccesso("Rapporto e promemoria registrati con successo! Ricorda di fare il test!");
				 });
			 } catch (DatabaseNonRaggiungibileException e) {
                 Platform.runLater(() -> ViewDispatcher.mostraErroreCriticoEChiudi(e.getMessage()));
		     } catch (UtenteNonLoggatoException e) {
                 Platform.runLater(() -> {
                     ViewDispatcher.mostraErrore(e.getMessage());
                     ViewDispatcher.mostraLogin();
                 });
		     }
		 }).exceptionally(ex -> {
             Platform.runLater(() -> {
                 siButton.setDisable(false);
                 noButton.setDisable(false);
                 ViewDispatcher.mostraErrore("Errore nel salvataggio del rapporto: " + (ex.getCause() != null ? ex.getCause().getMessage() : ex.getMessage()));
             });
             return null;
         });
	 }
	 
	 @FXML
	 private void onNoButton(ActionEvent event){
		 siButton.setDisable(true);
		 noButton.setDisable(true);
		 
		 salvaEConcludi().thenRun(() -> {
             Platform.runLater(() -> ViewDispatcher.mostraSuccesso("Rapporto registrato con successo! Ricorda di fare il test!"));
		 }).exceptionally(ex -> {
             Platform.runLater(() -> {
                 siButton.setDisable(false);
                 noButton.setDisable(false);
                 ViewDispatcher.mostraErrore("Errore nel salvataggio: " + (ex.getCause() != null ? ex.getCause().getMessage() : ex.getMessage()));
             });
             return null;
         });
	 }
	 
	 private CompletableFuture<Void> salvaEConcludi() {
		RegistraRapportoAppController appController = new RegistraRapportoAppController();
		return appController.salvaRapportoDefinitivo(beanInSospeso);
	 }
	 
	 @FXML
	 private void onAnnullaButton() {
		 ViewDispatcher.mostraMenuPrincipale();
	 }
	 
}
