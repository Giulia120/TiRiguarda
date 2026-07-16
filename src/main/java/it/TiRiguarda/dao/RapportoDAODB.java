package it.tiriguarda.dao;

import it.tiriguarda.domain.Rapporto;

public class RapportoDAODB implements RapportoDAO {
	
	@Override
	public void salvaRapporto (Rapporto rapporto) {
		System.out.println("FAKE: Rapporto salvato DB");
	}

}
