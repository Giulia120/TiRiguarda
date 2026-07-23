package it.tiriguarda.dao;

public interface DAOFactory {
	public RapportoDAO createRapportoDAO();
	public ProtocolloPrEPDAO createProtocolloPrEPDAO();
	public UtenteDAO createUtenteDAO();

}
