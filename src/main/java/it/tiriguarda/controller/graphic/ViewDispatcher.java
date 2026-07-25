package it.tiriguarda.controller.graphic;

import java.util.logging.Level;
import java.util.logging.Logger;

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
	
	public static void mostraSceltaConfig() {
        cambiaSchermata("/it/tiriguarda/view/SceltaConfig.fxml");
    }
	
	public static void mostraTest() {
        cambiaSchermata("/it/tiriguarda/view/Test.fxml");
    }

    public static void mostraProfilo() {
        cambiaSchermata("/it/tiriguarda/view/Profilo.fxml");
    }
    
    public static void mostraModificaPwd() {
        cambiaSchermata("/it/tiriguarda/view/ModificaPwd.fxml");
    }
    
    public static void mostraModificaTel() {
        cambiaSchermata("/it/tiriguarda/view/ModificaTel.fxml");
    }
    
    public static void mostraModificaUser() {
        cambiaSchermata("/it/tiriguarda/view/ModificaUser.fxml");
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
        cambiaSchermata("/it/tiriguarda/view/Questionario.fxml");
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
            controller.initData(bean.getDataFinePeriodoFinestra());
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
    	cambiaSchermata("/it/tiriguarda/view/ConfiguraPrEP.fxml");
    }
	
	public static void mostraErrore(String messaggio) {
		 Alert alert = new Alert(AlertType.ERROR);
	   	 alert.setTitle("Errore di Validazione");
	   	 alert.setHeaderText(null);
	   	 alert.setContentText(messaggio);
	   	 alert.showAndWait();
	 }
}
