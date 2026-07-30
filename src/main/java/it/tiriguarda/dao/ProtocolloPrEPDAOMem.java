package it.tiriguarda.dao;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import it.tiriguarda.domain.ProtocolloPrEP;
import it.tiriguarda.domain.Test;
import it.tiriguarda.domain.Utente;

public class ProtocolloPrEPDAOMem implements ProtocolloPrEPDAO{
	private static final Logger logger = Logger.getLogger(RapportoDAOMem.class.getName());
	
	private static List<ProtocolloPrEP> protocolliInMemoria = new ArrayList<>();
	
	@Override
	public ProtocolloPrEP trovaProtocolloAttivo(String username) {
		for (ProtocolloPrEP p : protocolliInMemoria) {
			if (p.getUtente().equals(username)) {
				return p;
			}
		}
		return null;
	}
	@Override
	public void configuraProtocollo(ProtocolloPrEP protocolloPrEP) {
		protocolliInMemoria.add(protocolloPrEP);
		logger.info("Nuovo protocollo attivo salvato.");
	}
	
	@Override
	public void aggiornaProtocollo(ProtocolloPrEP protocolloPrEP) {
		int index = -1;
	  
	    for (int i = 0; i < protocolliInMemoria.size(); i++) {
	        ProtocolloPrEP p = protocolliInMemoria.get(i);
	        if (p.getStatoPrEP()) {
	            index = i;
	            break; 
	        }
	    }
	    protocolliInMemoria.set(index, protocolloPrEP);
	    logger.info("Protocollo attivo aggiornato con successo.");
	}
	
	
	@Override
	public void annullaStatoProtocollo(ProtocolloPrEP protocolloPrEP) {
		protocolloPrEP.setStatoPrEP(false);
		protocolloPrEP.setDataFine(LocalDate.now(ZoneId.systemDefault()));
		logger.info("Protocollo annullato.");
	}
	
	@Override
    public List<ProtocolloPrEP> riepilogoPrEP(Utente utente) {
		List<ProtocolloPrEP> protocolli = new ArrayList<>();
	    for (ProtocolloPrEP p : protocolliInMemoria) {
	        if (p.getUtente() != null && p.getUtente().equals(utente.getUsername())) {
	            protocolli.add(p);
	        }
	    }
	    return protocolli;
    }
}
