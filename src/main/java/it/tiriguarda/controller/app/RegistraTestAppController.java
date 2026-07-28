package it.tiriguarda.controller.app;

import java.util.UUID;
import java.util.logging.Logger;

import it.tiriguarda.dao.DAOFactory;
import it.tiriguarda.dao.DAOFactoryProvider;
import it.tiriguarda.dao.TestDAO;
import it.tiriguarda.domain.Test;
import it.tiriguarda.domain.Utente;
import it.tiriguarda.dto.TestBean;
import it.tiriguarda.service.SessionManager;

public class RegistraTestAppController {
	private static final Logger logger = Logger.getLogger(RegistraTestAppController.class.getName());
	public void registraTest(TestBean bean){
	Utente utenteCorrente = SessionManager.getInstance().getUtenteLoggato();
	if (utenteCorrente == null) {
        throw new IllegalStateException("Errore critico: Nessun utente loggato in sessione.");
    }
	String idTest = UUID.randomUUID().toString();
	
	Test nuovoTest = new Test(utenteCorrente, idTest, bean.getTipo(), bean.getData());

	DAOFactory factory = DAOFactoryProvider.getDAOFactory();
	TestDAO dao = factory.createTestDAO();
	dao.salvaTest(nuovoTest);
	logger.info("Test registrato con successo.");
	}
}