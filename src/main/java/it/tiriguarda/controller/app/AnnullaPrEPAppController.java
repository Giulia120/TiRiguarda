package it.tiriguarda.controller.app;

import java.util.logging.Logger;

import it.tiriguarda.dao.DAOFactory;
import it.tiriguarda.dao.DAOFactoryProvider;
import it.tiriguarda.dao.ProtocolloPrEPDAO;
import it.tiriguarda.domain.ProtocolloPrEP;
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
		
		ProtocolloPrEP protocollo = utente.getProtocolloAttivo();
	 
		if(protocollo == null  || !protocollo.getStatoPrEP()) {
			throw new AnnullamentoPrEPException();
		}
	}
	
	public void annullaPrEP() {
		Utente utente = SessionManager.getInstance().getUtenteLoggato();
		if (utente == null) {
	        throw new IllegalStateException("Errore critico: Nessun utente loggato in sessione.");
	    }
		
		ProtocolloPrEP protocollo = utente.getProtocolloAttivo();
		DAOFactory factory = DAOFactoryProvider.getDAOFactory();
		ProtocolloPrEPDAO dao = factory.createProtocolloPrEPDAO();
		dao.annullaStatoProtocollo(protocollo);	
		logger.info("Protocollo PrEP annullato correttamente");
	}
	
}
