package it.tiriguarda.dao;

import java.util.ArrayList;
import java.util.List;

import it.tiriguarda.domain.Rapporto;

public class RapportoDAOMem implements RapportoDAO {
	
	private static List<Rapporto> dbInMemoria = new ArrayList<>();
	
	@Override
	public void salvaRapporto (Rapporto rapporto) {
		dbInMemoria.add(rapporto);
		System.out.println("DEMO: Rapporto salvato correttamente");
	}
}
