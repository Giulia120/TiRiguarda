package it.tiriguarda.dao;

import java.time.LocalDate;
import java.util.List;

import it.tiriguarda.domain.ProtocolloPrEP;

public interface ProtocolloPrEPDAO {
	ProtocolloPrEP trovaProtocolloAttivo(String username);
	void configuraProtocollo(ProtocolloPrEP protocolloPrEP);
	void aggiornaProtocollo(ProtocolloPrEP protocolloPrEP);
	void annullaStatoProtocollo(ProtocolloPrEP protocolloPrEP);
	List<ProtocolloPrEP> riepilogoPrEP(String utente, LocalDate data);
	public boolean esisteProtocollo(String utente, LocalDate data);
	boolean esisteProtocollo(String utente, LocalDate data, boolean soloAttivi);
}
