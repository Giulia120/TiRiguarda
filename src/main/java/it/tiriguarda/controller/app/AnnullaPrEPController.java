package it.tiriguarda.controller.app;

import it.tiriguarda.dao.ProtocolloPrEPDAO;
import it.tiriguarda.dao.ProtocolloPrEPDAOFactory;
import it.tiriguarda.domain.ProtocolloPrEP;
import it.tiriguarda.domain.Utente;
import it.tiriguarda.service.SessionManager;

public class AnnullaPrEPController {
	
	public void verificaStatoPrEP() throws Exception{
		Utente utente = SessionManager.getInstance().getUtenteLoggato();
		ProtocolloPrEP protocollo = utente.getProtocolloAttivo();
		
		if(protocollo == null) {
			throw new Exception("Protocollo non esistente");
		}
		
		boolean stato = protocollo.getStatoPrEP();
		
		if(stato == false) {
			throw new Exception("Protocollo gia annullato");
		}
	}
	
	public void annullaPrEP() {
		Utente utente = SessionManager.getInstance().getUtenteLoggato();
		ProtocolloPrEP protocollo = utente.getProtocolloAttivo();
		
		ProtocolloPrEPDAOFactory factory = new ProtocolloPrEPDAOFactory();
		ProtocolloPrEPDAO dao = factory.creaProtocolloPrEPDAO();
		
		dao.annullaStatoProtocollo(protocollo);
		
	}
	
}
