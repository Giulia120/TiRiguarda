package it.tiriguarda.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import it.tiriguarda.domain.Rapporto;

public class RapportoDAOMem implements RapportoDAO {
	private static final Logger logger = Logger.getLogger(RapportoDAOMem.class.getName());
	
	private static List<Rapporto> rapportiInMemoria = new ArrayList<>();
	
	@Override
	public void salvaRapporto (Rapporto rapporto) {
		rapportiInMemoria.add(rapporto);
		logger.info("Rapporto salvato correttamente");
	}
}
