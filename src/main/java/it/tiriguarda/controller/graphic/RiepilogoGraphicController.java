package it.tiriguarda.controller.graphic;

import it.tiriguarda.controller.app.RiepilogoAppController;
import it.tiriguarda.dto.RiepilogoBean;
import it.tiriguarda.exception.DataFuturaException;
import it.tiriguarda.exception.DatiIncompletiException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class RiepilogoGraphicController {
	
	@FXML
	private DatePicker dataRiepilogoPicker;
	
	@FXML
	private Button generaButton;
	
	@FXML
	private Button backButton;
	
	@FXML
	private VBox vboxContenutoRisultati;
	
	@FXML
	public void onGeneraButton() {
		try {
			RiepilogoBean bean = new RiepilogoBean();
			bean.setData(dataRiepilogoPicker.getValue());
			
			RiepilogoAppController controller = new RiepilogoAppController();
			
			controller.effettuaRiepilogo(bean);
			
			vboxContenutoRisultati.getChildren().clear();
			
			popolaSezioneRapporti(bean);
			popolaSezioneTest(bean);
			popolaSezioneProtocolli(bean);
			popolaSezioneVuota(bean);
			
		}catch(DataFuturaException e) {
        	ViewDispatcher.mostraErrore(e.getMessage());
            dataRiepilogoPicker.setValue(null);  
		} catch (DatiIncompletiException e) {
        	ViewDispatcher.mostraErrore(e.getMessage());
		}
	}
	
	private void popolaSezioneRapporti(RiepilogoBean bean) {
		if (bean.getRapporti() == null || bean.getRapporti().isEmpty()) {
			return;
		}
		Label lblTitoloRapporti = new Label("--- Rapporti ---");
		lblTitoloRapporti.setStyle("-fx-font-weight: bold; -fx-font-size: 16px; -fx-text-fill: #333333; -fx-padding: 10 0 5 0;");
		vboxContenutoRisultati.getChildren().add(lblTitoloRapporti);
		
		for (var r : bean.getRapporti()) {  
			String testo = "Data: " + r.getData() + " | Rischio: " + r.getRischio() + " | Fine Finestra: " + r.getDataFinePeriodoFinestra();
			Label lblItem = new Label("- " + testo);  
			vboxContenutoRisultati.getChildren().add(lblItem);
		}
	}
	
	private void popolaSezioneTest(RiepilogoBean bean) {
		if (bean.getTest() == null || bean.getTest().isEmpty()) {
			return;
		}
		Label lblTitoloTest = new Label("--- Test ---");
		lblTitoloTest.setStyle("-fx-font-weight: bold; -fx-font-size: 16px; -fx-text-fill: #333333; -fx-padding: 15 0 5 0;");
		vboxContenutoRisultati.getChildren().add(lblTitoloTest);
		
		for (var t : bean.getTest()) {
			String testo = "Tipo: " + t.getTipo() + " | Data: " + t.getData();
			Label lblItem = new Label("- " + testo);  
			vboxContenutoRisultati.getChildren().add(lblItem);
		}
	}

	private void popolaSezioneProtocolli(RiepilogoBean bean) {
		if (bean.getPrep() == null || bean.getPrep().isEmpty()) {
			return;
		}
		Label lblTitoloProtocolli = new Label("--- Protocolli ---");
		lblTitoloProtocolli.setStyle("-fx-font-weight: bold; -fx-font-size: 16px; -fx-text-fill: #333333; -fx-padding: 15 0 5 0;");
		vboxContenutoRisultati.getChildren().add(lblTitoloProtocolli);
		
		for (var p : bean.getPrep()) {
			String testo = "Tipo PrEP: " + p.getTipoPrEP() + " | Inizio: " + p.getDataInizio() + " | Ora: " + p.getOra() + " | Attivo: " + (p.getStatoPrEP() ? "Si" : "No") + (p.getDataFine() != null ? " | Fine: " + p.getDataFine() : "");
			Label lblItem = new Label("- " + testo);  
			vboxContenutoRisultati.getChildren().add(lblItem);
		}
	}

	private void popolaSezioneVuota(RiepilogoBean bean) {
		boolean tuttoVuoto = (bean.getRapporti() == null || bean.getRapporti().isEmpty()) &&
							 (bean.getTest() == null || bean.getTest().isEmpty()) &&
							 (bean.getPrep() == null || bean.getPrep().isEmpty());
							 
		if (tuttoVuoto) {
			Label lblVuoto = new Label("Nessun dato trovato per la data selezionata.");
			lblVuoto.setStyle("-fx-font-style: italic; -fx-text-fill: #666666; -fx-padding: 10 0 0 0;");
			vboxContenutoRisultati.getChildren().add(lblVuoto);
		}
	}
		
	@FXML
	public void onBackButton(ActionEvent event) {
		ViewDispatcher.mostraMenuPrincipale();
	}
	
}
