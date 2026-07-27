package it.tiriguarda.dao;

public class DemoDAOFactory implements DAOFactory{
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
