package it.tiriguarda.dao;

import java.time.LocalDate;
import java.util.List;

import it.tiriguarda.domain.Rapporto;
import it.tiriguarda.domain.Utente;

public interface RapportoDAO {
	
	void salvaRapporto(Rapporto rapporto);
	List<Rapporto> riepilogoRapporti(Utente utente, LocalDate data);
}
