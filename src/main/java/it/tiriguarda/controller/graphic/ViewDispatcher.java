package it.tiriguarda.controller.graphic;

import java.util.logging.Level;
import java.util.logging.Logger;

import it.tiriguarda.domain.LivelloRischio;
import it.tiriguarda.domain.TipologiaPrEP;
import it.tiriguarda.dto.RapportoBean;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class ViewDispatcher {
	private static final Logger logger = Logger.getLogger(ViewDispatcher.class.getName());
	private static Stage finestraPrincipale;
	
	private ViewDispatcher() {
        // Costruttore privato per nascondere quello pubblico di default
    }
	public static void setFinestraPrincipale(Stage stage) {
        finestraPrincipale = stage;
        finestraPrincipale = stage;
        finestraPrincipale.setTitle("Ti Riguarda");
        finestraPrincipale.setResizable(false);
    }
	
	private static void cambiaSchermata(String fxmlPath) {
		try {
			FXMLLoader loader = new FXMLLoader(ViewDispatcher.class.getResource(fxmlPath));
			Parent nuovaVista = loader.load();			
			finestraPrincipale.setScene(new Scene(nuovaVista));
			finestraPrincipale.show();
		} catch (Exception e) {
			logger.log(Level.SEVERE, e, () -> "Errore nel caricamento della schermata: " + fxmlPath);
		}
	}
	
	public static void mostraSuccesso() {
		cambiaSchermata("/it/tiriguarda/view/Successo.fxml");
	}
	
	public static void mostraTest() {
        cambiaSchermata("/it/tiriguarda/view/Test.fxml");
    }

    public static void mostraProfilo() {
        cambiaSchermata("/it/tiriguarda/view/Profilo.fxml");
    }
    
    public static void mostraModificaPwd() {
        mostraPopup("/it/tiriguarda/view/ModificaPwd.fxml", "Modifica Password");
    }
    
    public static void mostraModificaTel() {
        mostraPopup("/it/tiriguarda/view/ModificaTel.fxml", "Modifica Telefono");
    }
    
    public static void mostraRiepilogo() {
        cambiaSchermata("/it/tiriguarda/view/Riepilogo.fxml");
    }

    public static void mostraRegistraRapporto() {
        cambiaSchermata("/it/tiriguarda/view/RegistraRapporto.fxml");
    }

    public static void mostraPrEP() {
        cambiaSchermata("/it/tiriguarda/view/SceltaPrEP.fxml");
    }

    public static void mostraInfo() {
        cambiaSchermata("/it/tiriguarda/view/Info.fxml");
    }

    public static void mostraLogin() {
        cambiaSchermata("/it/tiriguarda/view/Login.fxml");
    }

    public static void mostraQuestionario() {
    	mostraPopup("/it/tiriguarda/view/QuestionarioDomanda.fxml", "Valutazione Rischio HIV");
    }
    
    public static void mostraMenuPrincipale() {
    	cambiaSchermata("/it/tiriguarda/view/MenuPrincipale.fxml");
    }
    
    public static void mostraRegistrazione() {
        cambiaSchermata("/it/tiriguarda/view/Registrazione.fxml");
    }
    
    public static void mostraSchermataSMSRapporto(RapportoBean bean) {
        try {
            FXMLLoader loader = new FXMLLoader(ViewDispatcher.class.getResource("/it/tiriguarda/view/RichiestaSMSRapporto.fxml"));
            Parent nuovaVista = loader.load();            
            RichiestaSMSRapportoGraphicController controller = loader.getController();
            controller.inizializza(bean);
            finestraPrincipale.setScene(new Scene(nuovaVista));
            finestraPrincipale.show();
            
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Errore nel caricamento della schermata SMS Rapporto", e);
        }
    }
    
    public static void mostraConfermaAnnullamento() {
    	cambiaSchermata("/it/tiriguarda/view/ConfermaAnnullamentoPrEP.fxml");
    }
    
    public static void mostraConfiguraPrEP(TipologiaPrEP tipoPrEP) {
    	 try {
    		 FXMLLoader loader = new FXMLLoader(
    	     ViewDispatcher.class.getResource("/it/tiriguarda/view/ConfiguraPrEP.fxml"));
    	        Parent nuovaVista = loader.load();
    	        ConfiguraPrEPGraphicController controller = loader.getController();
    	        controller.initData(tipoPrEP);
    	        finestraPrincipale.setScene(new Scene(nuovaVista));
    	        finestraPrincipale.show();
    	    } catch (Exception e) {
    	        logger.log(Level.SEVERE, "Errore nel caricamento della schermata Configura PrEP", e);
    	    }
    }

    public static void mostraRisultatoQuestionario(LivelloRischio rischio) {
        try {
            FXMLLoader loader = new FXMLLoader(ViewDispatcher.class.getResource("/it/tiriguarda/view/QuestionarioRisultato.fxml"));
            Parent nuovaVista = loader.load();
            
            QuestionarioRisultatoGraphicController controller = loader.getController();
            controller.inizializza(rischio);
            
            finestraPrincipale.setScene(new Scene(nuovaVista));
            finestraPrincipale.show();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Errore nel caricamento del risultato questionario", e);
        }
    }
	
    private static void mostraPopup(String fxmlPath, String titolo) {
        try {
            FXMLLoader loader = new FXMLLoader(ViewDispatcher.class.getResource(fxmlPath));
            Parent root = loader.load();
            
            Stage popupStage = new Stage();
            popupStage.setTitle(titolo);
            popupStage.initModality(javafx.stage.Modality.APPLICATION_MODAL);
            popupStage.initOwner(finestraPrincipale);
            popupStage.setResizable(false);
            
            popupStage.setScene(new Scene(root));
            popupStage.showAndWait();
            
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Errore nel caricamento del popup: ", e);
        }
    }
    
	public static void mostraErrore(String messaggio) {
		 Alert alert = new Alert(AlertType.ERROR);
	   	 alert.setTitle("Ops!");
	   	 alert.setHeaderText(null);
	   	 alert.setContentText(messaggio);
	   	 alert.showAndWait();
	 }
	
	public static void mostraErroreCriticoEChiudi(String messaggioGrave) {
	    mostraErrore("Si è verificato un errore critico di connessione:\n" 
	                 + messaggioGrave 
	                 + "\nPer evitare la perdita dei dati, l'applicazione verrà chiusa.");
	    javafx.application.Platform.exit();
	    System.exit(1);
	}
}
