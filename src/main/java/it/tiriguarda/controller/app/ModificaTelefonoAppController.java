package it.tiriguarda.controller.app;

import java.util.logging.Logger;

import it.tiriguarda.dao.DAOFactory;
import it.tiriguarda.dao.DAOFactoryProvider;
import it.tiriguarda.dao.UtenteDAO;
import it.tiriguarda.domain.Utente;
import it.tiriguarda.dto.CambioTelefonoBean;
import it.tiriguarda.service.SessionManager;

public class ModificaTelefonoAppController {
    private static final Logger logger = Logger.getLogger(ModificaTelefonoAppController.class.getName());

    public void cambiaTelefono(CambioTelefonoBean bean) {
        Utente utenteCorrente = SessionManager.getInstance().getUtenteLoggato();
 
        utenteCorrente.setNumeroTelefono(bean.getNuovoTelefono());

        DAOFactory factory = DAOFactoryProvider.getDAOFactory();
        UtenteDAO dao = factory.createUtenteDAO();        
        dao.aggiornaUtente(utenteCorrente); 
        
        logger.info("Numero di telefono aggiornato con successo.");
    }
}