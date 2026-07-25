package it.tiriguarda.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import it.tiriguarda.domain.ProtocolloPrEP;

public class ProtocolloPrEPDAOMem implements ProtocolloPrEPDAO{
	private static final Logger logger = Logger.getLogger(RapportoDAOMem.class.getName());
	
	private static List<ProtocolloPrEP> protocolliInMemoria = new ArrayList<>();
	@Override
	public void configuraProtocollo(ProtocolloPrEP protocolloPrEP) {
		protocolliInMemoria.add(protocolloPrEP);
		logger.info("Nuovo protocollo attivo salvato.");
	}
	
	@Override
	public void aggiornaProtocollo(ProtocolloPrEP protocolloPrEP) {
		
	}
	
	
	@Override
	public void annullaStatoProtocollo(ProtocolloPrEP protocolloPrEP) {
		protocolloPrEP.setStatoPrEP(false);
		protocolloPrEP.setDataAnnullamento();
		logger.info("Protocollo annullato.");
	}
}
