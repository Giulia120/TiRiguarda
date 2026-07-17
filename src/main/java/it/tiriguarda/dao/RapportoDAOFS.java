package it.tiriguarda.dao;

import it.tiriguarda.domain.Rapporto;

public class RapportoDAOFS implements RapportoDAO {
	
	@Override
	public void salvaRapporto (Rapporto rapporto) {
		System.out.println("FAKE: Rapporto salvato FS");
	}

}
