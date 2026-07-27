package it.tiriguarda.dao;

import it.tiriguarda.domain.ProtocolloPrEP;

public class ProtocolloPrEPDAODB implements ProtocolloPrEPDAO{
	
	@Override
	public void configuraProtocollo(ProtocolloPrEP protocolloPrEP) {
		String sql = "insert into `ProtocolloPrEP`(`idProt`  ";
		
		System.out.println("Salvato nel DB");
	}
	
	@Override
	public void aggiornaProtocollo(ProtocolloPrEP protocolloPrEP) {
		System.out.println("Aggiornato nel DB");
	}
	
	@Override
	public void annullaStatoProtocollo(ProtocolloPrEP protocolloPrEP) {
		System.out.println("Aggiornato nel DB");
	}
}
