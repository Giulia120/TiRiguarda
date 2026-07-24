package it.tiriguarda.dao;

import it.tiriguarda.domain.ProtocolloPrEP;

public interface ProtocolloPrEPDAO {
	void configuraProtocollo(ProtocolloPrEP protocolloPrEP);
	void annullaStatoProtocollo(ProtocolloPrEP protocolloPrEP);
	
}
