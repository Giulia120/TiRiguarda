package it.tiriguarda.controller.app;

import java.time.LocalDate;
import java.util.logging.Logger;

import it.tiriguarda.dao.DAOFactory;
import it.tiriguarda.dao.DAOFactoryProvider;
import it.tiriguarda.dao.ProtocolloPrEPDAO;
import it.tiriguarda.dao.SmsDAO;
import it.tiriguarda.dao.UtenteDAO;
import it.tiriguarda.domain.ProtocolloPrEP;
import it.tiriguarda.domain.TipoSms;
import it.tiriguarda.domain.TipologiaPrEP;
import it.tiriguarda.domain.Utente;
import it.tiriguarda.exception.AnnullamentoPrEPException;
import it.tiriguarda.service.SessionManager;

public class AnnullaPrEPAppController  {
	private static final Logger logger = Logger.getLogger(AnnullaPrEPAppController.class.getName());
	
	public void verificaStatoPrEP() throws AnnullamentoPrEPException {
		Utente utente = SessionManager.getInstance().getUtenteLoggato();
		if (utente == null) {
	        throw new IllegalStateException("Errore critico: Nessun utente loggato in sessione.");
	    }
		
		DAOFactory factory = DAOFactoryProvider.getDAOFactory();
	    ProtocolloPrEPDAO dao = factory.createProtocolloPrEPDAO();

	    ProtocolloPrEP prot = dao.trovaProtocolloAttivo(utente.getUsername());
	 
		if(prot == null) {
			throw new AnnullamentoPrEPException();
		}
	}
	
	public void annullaPrEP() {
		Utente utente = SessionManager.getInstance().getUtenteLoggato();
		if (utente == null) {
	        throw new IllegalStateException("Errore critico: Nessun utente loggato in sessione.");
	    }
	
		DAOFactory factory = DAOFactoryProvider.getDAOFactory();
		ProtocolloPrEPDAO dao = factory.createProtocolloPrEPDAO();
		ProtocolloPrEP prot = dao.trovaProtocolloAttivo(utente.getUsername());
		prot.setStatoPrEP(false);
	    prot.setDataFine(LocalDate.now());
		dao.annullaStatoProtocollo(prot);	
		logger.info("Protocollo PrEP annullato correttamente");
		
	
		UtenteDAO daoUtente = factory.createUtenteDAO();
		daoUtente.eliminaProtocolloAttivo(utente);	
		
		SmsDAO smsDAO = factory.createSmsDAO();

		if(prot.getTipoPrEP() == TipologiaPrEP.DAILY) {
		    smsDAO.eliminaSmsProgrammati(utente.getUsername(), TipoSms.PREP_DAILY);
		}
		else {
		    smsDAO.eliminaSmsProgrammati(utente.getUsername(), TipoSms.PREP_OD);
		}
		logger.info("Eliminato riferimento a protocollo attivo.");
		
	}
	
}
