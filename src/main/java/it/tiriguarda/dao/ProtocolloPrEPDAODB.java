package it.tiriguarda.dao;

import it.tiriguarda.domain.ProtocolloPrEP;

public class ProtocolloPrEPDAODB implements ProtocolloPrEPDAO{
	@Override
	public void annullaStatoProtocollo(ProtocolloPrEP protocolloPrEP) {
		System.out.println("Salvato nel DB");
	}
}
