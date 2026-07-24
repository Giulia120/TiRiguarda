package it.tiriguarda.dao;

import it.tiriguarda.domain.ProtocolloPrEP;

public class ProtocolloPrEPDAODB implements ProtocolloPrEPDAO{
	
	@Override
	public void configuraProtocollo(ProtocolloPrEP protocolloPrEP) {
		System.out.println("Salvato nel DB");
	}
	
	
	public void annullaStatoProtocollo(ProtocolloPrEP protocolloPrEP) {
		System.out.println("Aggiornato nel DB");
	}
}
