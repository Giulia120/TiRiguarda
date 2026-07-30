package it.tiriguarda.dao;

import java.util.ArrayList;
import java.util.List;

import it.tiriguarda.domain.Rapporto;
import it.tiriguarda.domain.Utente;

public class RapportoDAOMem implements RapportoDAO {
	
	private static List<Rapporto> rapportiInMemoria = new ArrayList<>();
	
	@Override
	public void salvaRapporto (Rapporto rapporto) {
		rapportiInMemoria.add(rapporto);
	}
	
	@Override
    public List<Rapporto> riepilogoRapporti(Utente utente) {
		List<Rapporto> rapporti = new ArrayList<>();
	    for (Rapporto r : rapportiInMemoria) {
	        if (r.getUtente() != null && r.getUtente().equals(utente.getUsername())) {
	            rapporti.add(r);
	        }
	    }
	    return rapporti;
    }
}
