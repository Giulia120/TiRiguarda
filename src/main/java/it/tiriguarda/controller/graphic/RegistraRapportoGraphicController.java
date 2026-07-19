package it.tiriguarda.controller.graphic;

import java.util.ArrayList;
import java.util.List;

import it.tiriguarda.domain.Precauzioni;
import it.tiriguarda.domain.TipoRapporto;
import it.tiriguarda.dto.RapportoBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

public class RegistraRapportoGraphicController {

	 @FXML
	 private DatePicker dataRapportoPicker;
	 @FXML
	 private CheckBox checkPenetrativo;
	 @FXML
	 private CheckBox checkOrale;
	 @FXML
	 private RadioButton radioNulla;
	 @FXML
	 private RadioButton radioCoInt;
	 @FXML
	 private RadioButton radioPreservativo;
	 @FXML
	 private Button confermaButton;
	 @FXML
	 private Button backButton;
	 @FXML
	 private Button homeButton;
	 @FXML
	 private Button SiButton;
	 @FXML
	 private Button NoButton;
	 
	 @FXML
	 public void onConfermaRegistrazione(ActionEvent event) {
		 if (dataRapportoPicker.getValue() == null) {
	            mostraErrore("Devi selezionare la data del rapporto!");
	            return;
	        }
	        
	        if (!checkPenetrativo.isSelected() && !checkOrale.isSelected()) {
	            mostraErrore("Devi selezionare almeno un tipo di rapporto!");
	            return;
	        }
	        
	        if (!radioNulla.isSelected() && !radioCoInt.isSelected() && !radioPreservativo.isSelected()) {
	            mostraErrore("Devi selezionare le precauzioni usate!");
	            return;
	        }
	        
	        RapportoBean bean = new RapportoBean();
	        
	        java.sql.Date dataConvertita = java.sql.Date.valueOf(dataRapportoPicker.getValue());
	        bean.setData(dataConvertita);
	        
	        List<TipoRapporto> tipiSelezionati = new ArrayList<>();
	        if (checkPenetrativo.isSelected()) {
	            tipiSelezionati.add(TipoRapporto.PENETRATIVO);
	        }
	        if (checkOrale.isSelected()) {
	            tipiSelezionati.add(TipoRapporto.ORALE);
	        }
	        bean.setTipo(tipiSelezionati);
	        
	        if (radioPreservativo.isSelected()) {
	            bean.setPrecauzioniUsate(Precauzioni.PRESERVATIVO);
	        } else if (radioCoInt.isSelected()) {
	            bean.setPrecauzioniUsate(Precauzioni.COITO_INTERROTTO);
	        } else if (radioNulla.isSelected()) {
	            bean.setPrecauzioniUsate(Precauzioni.NULLA);
	        }
	        
	        try {
	            //RegistraRapportoAppController.getInstance().registraRapporto(bean);
	            
	            boolean rischioRilevato = true;
	            if (rischioRilevato) {
	                mostraSchermataSMS(event);
	            } else {
	            	mostraSchermataSuccesso(event);
	            }
	            
	        } catch (Exception e) {
	            mostraErrore("Si è verificato un errore: " + e.getMessage());
	        }
	 }
	 private void mostraErrore(String messaggio) {
		 Alert alert = new Alert(AlertType.ERROR);
	   	 alert.setTitle("Errore di Validazione");
	   	 alert.setHeaderText(null);
	   	 alert.setContentText(messaggio);
	   	 alert.showAndWait();
	 }
	 
	 private void mostraSchermataSuccesso(ActionEvent eventoClick) throws Exception {
		 System.out.println("Nessun rischio: carico la schermata di successo...");
		 FXMLLoader loader = new FXMLLoader(getClass().getResource("/it/tiriguarda/view/SuccessoRapporto.fxml"));
         Parent nuovaVista = loader.load();
         
         Stage finestraAttuale = (Stage) ((Node) eventoClick.getSource()).getScene().getWindow();
         finestraAttuale.setScene(new Scene(nuovaVista));
         finestraAttuale.show();
	 }
	 
	 private void mostraSchermataSMS(ActionEvent eventoClick) throws Exception {
		 System.out.println("Rischio rilevato: apro la richiesta SMS...");
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/it/tiriguarda/view/RichiestaSMSRapporto.fxml"));
         Parent nuovaVista = loader.load();
         
         Stage finestraAttuale = (Stage) ((Node) eventoClick.getSource()).getScene().getWindow();
         finestraAttuale.setScene(new Scene(nuovaVista));
         finestraAttuale.show();
	 }
	 
	  
	 @FXML
	 private void onTornaMenuPrincipale(){
		 System.out.println("Daje");
	 }
	 
	 @FXML
	 private void onHomeButton(){
		 System.out.println("Daje");
	 }
	 
	 @FXML
	 private void onSiButton(){
		 System.out.println("Daje");
	 }
	 
	 @FXML
	 private void onNoButton(){
		 System.out.println("Daje");
	 }
	 
	        
}
