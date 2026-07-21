package it.tiriguarda.controller.app;

import java.util.logging.Logger;

import it.tiriguarda.dao.ProtocolloPrEPDAO;
import it.tiriguarda.dao.ProtocolloPrEPDAOFactory;
import it.tiriguarda.domain.ProtocolloPrEP;
import it.tiriguarda.domain.Utente;
import it.tiriguarda.exception.PrEPAnnullataException;
import it.tiriguarda.exception.PrEPNonEsistenteException;
import it.tiriguarda.exception.TiRiguardaException;
import it.tiriguarda.service.SessionManager;

public class AnnullaPrEPController  {
	private static final Logger logger = Logger.getLogger(AnnullaPrEPController.class.getName());
	
	public void verificaStatoPrEP() throws TiRiguardaException{
		Utente utente = SessionManager.getInstance().getUtenteLoggato();
		if(utente == null) {
			throw new TiRiguardaException("Utente non loggato");
		}
		ProtocolloPrEP protocollo = utente.getProtocolloAttivo();
	
		if(protocollo == null) {
			throw new PrEPNonEsistenteException("Protocollo non esistente");
		}
		
		boolean stato = protocollo.getStatoPrEP();
		
		if(stato == false) {
			throw new PrEPAnnullataException("Protocollo già annullato");
		}
	}
	
	public void annullaPrEP() throws TiRiguardaException{
		Utente utente = SessionManager.getInstance().getUtenteLoggato();
		if(utente == null) {
			throw new TiRiguardaException("Utente non loggato");
		}
		ProtocolloPrEP protocollo = utente.getProtocolloAttivo();
		ProtocolloPrEPDAOFactory factory = new ProtocolloPrEPDAOFactory();
		ProtocolloPrEPDAO dao = factory.creaProtocolloPrEPDAO();
		dao.annullaStatoProtocollo(protocollo);	
		logger.info("Protocollo PrEP annullato correttamente");
	}
	
}
