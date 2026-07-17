package it.tiriguarda.dao;

import it.tiriguarda.domain.ProtocolloPrEP;

public class ProtocolloPrEPDAOMem implements ProtocolloPrEPDAO{
	
	@Override
	public void annullaStatoProtocollo(ProtocolloPrEP protocolloPrEP) {
		protocolloPrEP.setStatoPrEP(false);
		protocolloPrEP.setDataAnnullamento();
	}

}
