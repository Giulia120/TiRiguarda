package it.tiriguarda.dao;

import it.tiriguarda.config.AppConfig;
import it.tiriguarda.config.AppMode;

public class ProtocolloPrEPDAOFactory {
	public ProtocolloPrEPDAO creaProtocolloPrEPDAO() {
		if(AppConfig.getCurrentMode() == AppMode.DEMO) {
			return new ProtocolloPrEPDAOMem();
		}
		else {
			return new ProtocolloPrEPDAODB();
		}
	}
}
