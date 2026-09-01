package it.tiriguarda.dao;

import it.tiriguarda.dao.db.LoginDAODB;
import it.tiriguarda.dao.db.ProtocolloPrEPDAODB;
import it.tiriguarda.dao.db.RapportoDAODB;
import it.tiriguarda.dao.db.SmsDAODB;
import it.tiriguarda.dao.db.TestDAODB;
import it.tiriguarda.dao.db.UtenteDAODB;

public class FullDBDAOFactory extends DAOFactory{
	@Override
	public LoginDAO createLoginDAO() {
		return new LoginDAODB();
	}
	@Override
	public UtenteDAO createUtenteDAO() {
		return new UtenteDAODB();
	}
	@Override 
	public RapportoDAO createRapportoDAO() {
		return new RapportoDAODB();
	}
	@Override 
	public ProtocolloPrEPDAO createProtocolloPrEPDAO() {
		return new ProtocolloPrEPDAODB();
	}
	
	@Override 
	public TestDAO createTestDAO() {
		return new TestDAODB();
	}
	
	@Override 
	public SmsDAO createSmsDAO() {
		return new SmsDAODB();
	}
	
}
