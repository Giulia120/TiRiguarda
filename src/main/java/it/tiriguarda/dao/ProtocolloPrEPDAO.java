package it.tiriguarda.dao;

import java.time.LocalDate;
import java.util.List;

import it.tiriguarda.domain.ProtocolloPrEP;

public interface ProtocolloPrEPDAO {
	public ProtocolloPrEP trovaProtocolloAttivo(String username);
	public void configuraProtocollo(ProtocolloPrEP protocolloPrEP);
	public void aggiornaProtocollo(ProtocolloPrEP protocolloPrEP);
	public void annullaStatoProtocollo(ProtocolloPrEP protocolloPrEP);
	public List<ProtocolloPrEP> riepilogoPrEP(String utente, LocalDate data);
	public boolean esisteProtocollo(String utente, LocalDate data);
	public boolean esisteProtocollo(String utente, LocalDate data, boolean soloAttivi);
}
