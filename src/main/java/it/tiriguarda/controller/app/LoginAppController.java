package it.tiriguarda.controller.app;

import java.util.logging.Logger;

import it.tiriguarda.dao.DAOFactory;
import it.tiriguarda.dao.DAOFactoryProvider;
import it.tiriguarda.dao.LoginDAO;
import it.tiriguarda.domain.Utente;
import it.tiriguarda.dto.CredenzialiBean;
import it.tiriguarda.service.SessionManager;
import it.tiriguarda.util.SecurityUtil;

public class LoginAppController {
	private static final Logger logger = Logger.getLogger(LoginAppController.class.getName());
	
	public void effettuaLogin(CredenzialiBean bean) {
		String hash = SecurityUtil.hashPassword(bean.getPassword());
		bean.setPassword(hash);
		DAOFactory factory = DAOFactoryProvider.getDAOFactory();
		LoginDAO dao = factory.createLoginDAO();
		Utente utenteTrovato = dao.effetuaLogin(bean);
		SessionManager sm = SessionManager.getInstance();
		sm.setUtenteLoggato(utenteTrovato);
		logger.info("Login effettuato");
	}
}
