package it.tiriguarda.dao;

import it.tiriguarda.config.AppConfig;
import it.tiriguarda.config.AppMode;

public abstract class DAOFactory {
	private static DAOFactory dAOFactoryInstance;
	
	public static DAOFactory getDAOFactory() {
		if (dAOFactoryInstance == null) {
			if (AppConfig.getCurrentMode() == AppMode.DEMO) {
				dAOFactoryInstance = new DemoDAOFactory();
			}
			else if  (AppConfig.getCurrentMode() == AppMode.FULL_DB)  {
				dAOFactoryInstance = new FullDBDAOFactory();
			}
			else {
				dAOFactoryInstance = new FullFSDAOFactory();
			}
		}
		return dAOFactoryInstance;
	}
	
	public abstract LoginDAO createLoginDAO();
	public abstract RapportoDAO createRapportoDAO();
	public abstract ProtocolloPrEPDAO createProtocolloPrEPDAO();
	public abstract UtenteDAO createUtenteDAO();
	public abstract TestDAO createTestDAO();
	public abstract SmsDAO createSmsDAO();
	public abstract QuestionDAO createQuestionDAO();
	
	
}
