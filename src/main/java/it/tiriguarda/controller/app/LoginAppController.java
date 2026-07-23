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
	
	
	public LoginAppController() {}
	
	public void effettuaLogin(CredenzialiBean bean) throws CredenzialiErrateException{
		DAOFactory factory = DAOFactoryProvider.getDAOFactory();
		UtenteDAO dao = factory.createUtenteDAO();
		Utente utenteTrovato = dao.trovaPerUsername(bean.getUsername());
		if (utenteTrovato != null && utenteTrovato.verificaPassword(bean.getPassword())) {
		    SessionManager SM = SessionManager.getInstance();
		    SM.setUtenteLoggato(utenteTrovato);
			logger.info("Login effettuato");
		    return;
		} else {
		    throw new CredenzialiErrateException();
		}
	}
}
