package it.tiriguarda.controller.graphic;

import java.util.ArrayList;
import java.util.List;

import it.tiriguarda.controller.app.RegistraRapportoAppController;
import it.tiriguarda.domain.LivelloRischio;
import it.tiriguarda.domain.Precauzioni;
import it.tiriguarda.domain.TipoRapporto;
import it.tiriguarda.dto.RapportoBean;
import it.tiriguarda.exception.DataFuturaException;
import it.tiriguarda.exception.DatiRapportoIncompletiException;
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
	        	RegistraRapportoAppController appController = RegistraRapportoAppController.getInstance();
	        	RapportoBean beanAggiornato = appController.registraRapporto(bean);
	            if (beanAggiornato.getRischio() != LivelloRischio.NULLO) {
	                mostraSchermataSMS(event, beanAggiornato);
	            } else {
	            	mostraSchermataSuccesso(event);
	            }
	            
	        } catch (DatiRapportoIncompletiException e) {
	            mostraErrore(e.getMessage());
	        } catch (DataFuturaException e) {
	            mostraErrore(e.getMessage());
	            dataRapportoPicker.setValue(null);          
	        } catch (Exception e) {
	            mostraErrore("Errore di sistema." + e.getMessage());
	            System.out.println(e.getMessage());
	        }
	 }
	 private void mostraErrore(String messaggio) {
		 Alert alert = new Alert(AlertType.ERROR);
	   	 alert.setTitle("Errore di Validazione");
	   	 alert.setHeaderText(null);
	   	 alert.setContentText(messaggio);
	   	 alert.showAndWait();
	 }
	 
	 private void mostraSchermataSuccesso(ActionEvent event) throws Exception {
		 System.out.println("Nessun rischio: carico la schermata di successo...");
		 FXMLLoader loader = new FXMLLoader(getClass().getResource("/it/tiriguarda/view/Successo.fxml"));
         Parent vistaSuccesso = loader.load();
         
         Stage finestra = (Stage) ((Node) event.getSource()).getScene().getWindow();
         finestra.setScene(new Scene(vistaSuccesso));
         finestra.show();
	 }
	 
	 private void mostraSchermataSMS(ActionEvent event, RapportoBean beanAggiornato) throws Exception {
		 System.out.println("Rischio rilevato: apro la richiesta SMS...");
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/it/tiriguarda/view/RichiestaSMSRapporto.fxml"));
         Parent vistaRichiestaSMSRapporto = loader.load();
         RichiestaSMSRapportoGraphicController controllerSMS = loader.getController();
         controllerSMS.initData(beanAggiornato.getDataFinePeriodoFinestra());
         Stage finestra = (Stage) ((Node) event.getSource()).getScene().getWindow();
         finestra.setScene(new Scene(vistaRichiestaSMSRapporto));
         finestra.show();
	 }
	 
	  
	 @FXML
		public void onMenuPrincipale(ActionEvent event) {
			try {
				apriMenuPrincipale(event);
			}catch (Exception e){
				e.printStackTrace();
				System.out.println("Errore nel caricamento della schermata." + e.getMessage());
			}
		}
		
		private void apriMenuPrincipale(ActionEvent event) throws Exception {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/it/tiriguarda/view/MenuPrincipale.fxml"));
			Parent vistaMenuPrincipale = loader.load();
			Stage finestra = (Stage) ((Node) event.getSource()).getScene().getWindow();
			
			finestra.setScene(new Scene(vistaMenuPrincipale));
			finestra.show();
		}     
}
