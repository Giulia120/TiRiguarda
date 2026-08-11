package it.tiriguarda.controller.app;

import java.util.logging.Logger;

import it.tiriguarda.dao.DAOFactory;
import it.tiriguarda.dao.DAOFactoryProvider;
import it.tiriguarda.dao.UtenteDAO;
import it.tiriguarda.domain.Utente;
import it.tiriguarda.dto.CredenzialiBean;
import it.tiriguarda.exception.CredenzialiErrateException;
import it.tiriguarda.service.SessionManager;

public class LoginAppController {
	private static final Logger logger = Logger.getLogger(LoginAppController.class.getName());
	
	public void effettuaLogin(CredenzialiBean bean) {
		DAOFactory factory = DAOFactoryProvider.getDAOFactory();
		UtenteDAO dao = factory.createUtenteDAO();
		Utente utenteTrovato = dao.trovaPerUsername(bean.getUsername());
		if (utenteTrovato != null && utenteTrovato.verificaPassword(bean.getPassword())) {
		    SessionManager sm = SessionManager.getInstance();
		    sm.setUtenteLoggato(utenteTrovato);
			logger.info("Login effettuato");
		} else {
		    throw new CredenzialiErrateException();
		}
	}
}
