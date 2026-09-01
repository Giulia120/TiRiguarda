package it.tiriguarda.dao;

import it.tiriguarda.dao.mem.LoginDAOMem;
import it.tiriguarda.dao.mem.ProtocolloPrEPDAOMem;
import it.tiriguarda.dao.mem.RapportoDAOMem;
import it.tiriguarda.dao.mem.SmsDAOMem;
import it.tiriguarda.dao.mem.TestDAOMem;
import it.tiriguarda.dao.mem.UtenteDAOMem;

public class DemoDAOFactory extends DAOFactory{
	@Override
	public LoginDAO createLoginDAO() {
		return new LoginDAOMem();
	}
	@Override
	public UtenteDAO createUtenteDAO() {
		return new UtenteDAOMem();
	}
	@Override 
	public RapportoDAO createRapportoDAO() {
		return new RapportoDAOMem();
	}
	@Override 
	public ProtocolloPrEPDAO createProtocolloPrEPDAO() {
		return new ProtocolloPrEPDAOMem();
	}
	
	@Override 
	public TestDAO createTestDAO() {
		return new TestDAOMem();
	}
	
	@Override 
	public SmsDAO createSmsDAO() {
		return new SmsDAOMem();
	}

}
