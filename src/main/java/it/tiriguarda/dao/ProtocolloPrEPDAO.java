package it.tiriguarda.dao;

import it.tiriguarda.domain.ProtocolloPrEP;

public interface ProtocolloPrEPDAO {
	void trovaProtocolloAttivo(String username);
	void configuraProtocollo(ProtocolloPrEP protocolloPrEP);
	void aggiornaProtocollo(ProtocolloPrEP protocolloPrEP);
	void annullaStatoProtocollo(ProtocolloPrEP protocolloPrEP);
}
