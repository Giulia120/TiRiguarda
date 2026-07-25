package it.tiriguarda.controller.app;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.logging.Logger;

import it.tiriguarda.dao.DAOFactory;
import it.tiriguarda.dao.DAOFactoryProvider;
import it.tiriguarda.dao.TestDAO;
import it.tiriguarda.domain.Test;
import it.tiriguarda.dto.TestBean;
import it.tiriguarda.exception.DataFuturaException;
import it.tiriguarda.exception.DatiIncompletiException;
import it.tiriguarda.service.SessionManager;

public class RegistraTestAppController {
	private static final Logger logger = Logger.getLogger(RegistraTestAppController.class.getName());
	public void registraTest(TestBean bean) throws DatiIncompletiException, DataFuturaException{
		if(bean.getData() == null || bean.getTipo() == null) {
			throw new DatiIncompletiException();
		}
		if (bean.getData().isAfter(LocalDate.now(ZoneId.systemDefault()))) {
            throw new DataFuturaException();
        }
	Test nuovoTest = new Test(SessionManager.getInstance().getUtenteLoggato(), bean.getTipo(), bean.getData());
	
	DAOFactory factory = DAOFactoryProvider.getDAOFactory();
	TestDAO dao = factory.createTestDAO();
	dao.salvaTest(nuovoTest);
	logger.info("Test registrato con successo.");
	}
}
	

