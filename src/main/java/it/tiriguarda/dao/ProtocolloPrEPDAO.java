package it.tiriguarda.dao;

import java.util.List;

import it.tiriguarda.domain.ProtocolloPrEP;
import it.tiriguarda.domain.Utente;

public interface ProtocolloPrEPDAO {
	ProtocolloPrEP trovaProtocolloAttivo(String username);
	void configuraProtocollo(ProtocolloPrEP protocolloPrEP);
	void aggiornaProtocollo(ProtocolloPrEP protocolloPrEP);
	void annullaStatoProtocollo(ProtocolloPrEP protocolloPrEP);
	List<ProtocolloPrEP> riepilogoPrEP(Utente utente);
}
