package it.tiriguarda.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import it.tiriguarda.domain.Rapporto;
import it.tiriguarda.domain.Utente;

public class RapportoDAOMem implements RapportoDAO {
	private static final Logger logger = Logger.getLogger(RapportoDAOMem.class.getName());
	
	private static List<Rapporto> rapportiInMemoria = new ArrayList<>();
	
	@Override
	public void salvaRapporto (Rapporto rapporto) {
		rapportiInMemoria.add(rapporto);
		logger.info("Rapporto salvato correttamente");
	}
	
	@Override
	public List<Rapporto> riepilogoRapporti(Utente utente, LocalDate data) {
	    List<Rapporto> rapporti = new ArrayList<>();
	    for (Rapporto r : rapportiInMemoria) {
	        boolean stessoUtente = r.getUtente() != null && r.getUtente().equals(utente.getUsername());
	        boolean dataValida = r.getData() != null && !r.getData().isBefore(data);
	        
	        if (stessoUtente && dataValida) {
	            rapporti.add(r);
	        }
	    }
	    return rapporti;
	}
}
