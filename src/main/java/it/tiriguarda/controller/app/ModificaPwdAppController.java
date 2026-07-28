package it.tiriguarda.controller.app;

import java.util.logging.Logger;

import it.tiriguarda.dao.DAOFactory;
import it.tiriguarda.dao.DAOFactoryProvider;
import it.tiriguarda.dao.UtenteDAO;
import it.tiriguarda.domain.Utente;
import it.tiriguarda.dto.CambioPwdBean;
import it.tiriguarda.exception.CredenzialiErrateException;
import it.tiriguarda.service.SessionManager;
import it.tiriguarda.util.SecurityUtil;

public class ModificaPwdAppController {
    private static final Logger logger = Logger.getLogger(ModificaPwdAppController.class.getName());

    public void cambiaPassword(CambioPwdBean bean) throws CredenzialiErrateException {
        Utente utenteCorrente = SessionManager.getInstance().getUtenteLoggato();
        if (utenteCorrente == null) {
	        throw new IllegalStateException("Errore critico: Nessun utente loggato in sessione.");
	    }

        
        if (!utenteCorrente.verificaPassword(bean.getVecchiaPassword())) {
            throw new CredenzialiErrateException("La vecchia password non è corretta!");
        }
        
        utenteCorrente.setPassword(SecurityUtil.hashPassword(bean.getNuovaPassword()));
        
        DAOFactory factory = DAOFactoryProvider.getDAOFactory();
        UtenteDAO dao = factory.createUtenteDAO();
        dao.aggiornaPwdUtente(utenteCorrente); 
        
        logger.info("Password aggiornata con successo.");
    }
}