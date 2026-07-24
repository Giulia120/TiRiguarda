package it.tiriguarda.dao;

public class FullDAOFactory implements DAOFactory{
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
		return new TestDAOMem();
	}

}
