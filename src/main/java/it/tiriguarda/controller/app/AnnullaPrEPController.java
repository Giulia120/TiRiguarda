package it.tiriguarda.controller.app;

import it.tiriguarda.dao.ProtocolloPrEPDAO;
import it.tiriguarda.dao.ProtocolloPrEPDAOFactory;
import it.tiriguarda.domain.ProtocolloPrEP;
import it.tiriguarda.domain.Utente;
import it.tiriguarda.exception.TiRiguardaException;
import it.tiriguarda.service.SessionManager;

public class AnnullaPrEPController  {
	
	public void verificaStatoPrEP() throws TiRiguardaException{
		Utente utente = SessionManager.getInstance().getUtenteLoggato();
		if(utente == null) {
			throw new TiRiguardaException("Utente non loggato");
		}
		
		ProtocolloPrEP protocollo = utente.getProtocolloAttivo();
		
		if(protocollo == null) {
			throw new TiRiguardaException("Protocollo non esistente");
		}
		
		boolean stato = protocollo.getStatoPrEP();
		
		if(stato == false) {
			throw new TiRiguardaException("Protocollo già annullato");
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
		
	}
	
}
