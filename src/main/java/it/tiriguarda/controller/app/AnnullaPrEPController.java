package it.tiriguarda.controller.app;

import it.tiriguarda.domain.ProtocolloPrEP;
import it.tiriguarda.domain.Utente;
import it.tiriguarda.service.SessionManager;

public class AnnullaPrEPController  {
	
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
		
		//chiama aggiornaStato della DAO
		
	}
	
}
