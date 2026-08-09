package it.tiriguarda.dao;

import java.time.LocalDate;
import java.util.List;

import it.tiriguarda.domain.Rapporto;

public interface RapportoDAO {
	
	void salvaRapporto(Rapporto rapporto);
	List<Rapporto> riepilogoRapporti(String utente, LocalDate data);
}
