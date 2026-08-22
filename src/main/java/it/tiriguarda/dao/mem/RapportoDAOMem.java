package it.tiriguarda.dao.mem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import it.tiriguarda.dao.RapportoDAO;
import it.tiriguarda.domain.Rapporto;

public class RapportoDAOMem implements RapportoDAO {
	
	private static List<Rapporto> rapportiInMemoria = new ArrayList<>();
	
	@Override
	public void salvaRapporto (Rapporto rapporto) {
		rapportiInMemoria.add(rapporto);
	}
	
	@Override
	public List<Rapporto> riepilogoRapporti(String utente, LocalDate data) {
	    List<Rapporto> rapporti = new ArrayList<>();
	    for (Rapporto r : rapportiInMemoria) {
	        boolean stessoUtente = r.getUtente() != null && r.getUtente().equals(utente);
	        boolean dataValida = r.getData() != null && !r.getData().isBefore(data);
	        
	        if (stessoUtente && dataValida) {
	            rapporti.add(r);
	        }
	    }
	    return rapporti;
	}
}
