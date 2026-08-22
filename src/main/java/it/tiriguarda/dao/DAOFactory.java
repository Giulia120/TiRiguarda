package it.tiriguarda.dao;

public interface DAOFactory {
	public LoginDAO createLoginDAO();
	public RapportoDAO createRapportoDAO();
	public ProtocolloPrEPDAO createProtocolloPrEPDAO();
	public UtenteDAO createUtenteDAO();
	public TestDAO createTestDAO();
	public SmsDAO createSmsDAO();
	public QuestionDAO createQuestionDAO();
}
