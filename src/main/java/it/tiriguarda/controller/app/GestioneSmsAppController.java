package it.tiriguarda.controller.app;

import java.util.UUID;
import java.util.logging.Logger;

import it.tiriguarda.dao.DAOFactory;
import it.tiriguarda.dao.DAOFactoryProvider;
import it.tiriguarda.dao.SmsDAO;
import it.tiriguarda.domain.Sms;
import it.tiriguarda.domain.TipoSms;
import it.tiriguarda.domain.Utente;
import it.tiriguarda.dto.SmsBean;
import it.tiriguarda.service.SessionManager;

public class GestioneSmsAppController {
	private static final Logger logger = Logger.getLogger(GestioneSmsAppController.class.getName());
	
	public void programmaSms(SmsBean bean) {
		
		Utente utenteCorrente = SessionManager.getInstance().getUtenteLoggato();
		if (utenteCorrente == null) {
	        throw new IllegalStateException("Errore critico: Nessun utente loggato in sessione.");
	    }

        DAOFactory factory = DAOFactoryProvider.getDAOFactory();
        SmsDAO smsDao = factory.createSmsDAO(); 
        	Sms sms = new Sms(utenteCorrente, UUID.randomUUID().toString(), bean.getTesto(), bean.getDataSpedizione(), bean.getTipo());
            smsDao.salvaSms(sms);
        logger.info("Programmato SMS nel sistema.");
    }
	
	public void cancellaSmsProgrammati(String username, TipoSms tipoSms) {
        DAOFactory factory = DAOFactoryProvider.getDAOFactory();
        SmsDAO smsDao = factory.createSmsDAO(); 
        
        smsDao.eliminaSmsProgrammati(username, tipoSms);
        logger.info("Cancellati SMS futuri di tipo " + tipoSms.name() + " per l'utente " + username);
    }
}
