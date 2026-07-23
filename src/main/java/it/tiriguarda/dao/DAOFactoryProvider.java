package it.tiriguarda.dao;

import it.tiriguarda.config.AppConfig;
import it.tiriguarda.config.AppMode;

public class DAOFactoryProvider {
	private static DAOFactory dAOFactoryInstance;
	
	private DAOFactoryProvider(){}
	 
	public static DAOFactory getDAOFactory() {
		if (dAOFactoryInstance == null) {
			if (AppConfig.getCurrentMode() == AppMode.DEMO) {
				dAOFactoryInstance = new DemoDAOFactory();
			}
			else {
				dAOFactoryInstance = new FullDAOFactory();
			}
		}
		return dAOFactoryInstance;
	}
}
