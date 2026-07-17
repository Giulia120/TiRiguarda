package it.tiriguarda.dao;

import it.tiriguarda.config.AppConfig;
import it.tiriguarda.config.AppMode;

public class RapportoDAOFactory {
	public RapportoDAO createRapportoDAO() {
		if (AppConfig.getCurrentMode() == AppMode.DEMO) {
			return new RapportoDAOMemory();
		}
		else {
			return new RapportoDAODB();
			//return new RapportoDAOFS();
		}
	}
}
