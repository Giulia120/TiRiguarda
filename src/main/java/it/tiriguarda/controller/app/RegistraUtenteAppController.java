package it.tiriguarda.controller.app;

import java.util.logging.Logger;

import it.tiriguarda.dao.DAOFactory;
import it.tiriguarda.dao.UtenteDAO;
import it.tiriguarda.domain.Utente;
import it.tiriguarda.dto.UtenteBean;
import it.tiriguarda.exception.UsernameEsistenteException;
import it.tiriguarda.util.SecurityUtil;

public class RegistraUtenteAppController {
	private static final Logger logger = Logger.getLogger(RegistraUtenteAppController.class.getName());
	
	
	public void registraUtente (UtenteBean bean) {
		
		DAOFactory factory = DAOFactory.getDAOFactory();
		UtenteDAO dao = factory.createUtenteDAO();
		if (dao.trovaPerUsername(bean.getUsername()) != null) {
			throw new UsernameEsistenteException();
		}
		Utente nuovoUtente = new Utente(bean.getUsername(), SecurityUtil.hashPassword(bean.getPassword()), bean.getSessoBiologico(), bean.getNumeroTelefono());
		dao.registraUtente(nuovoUtente);
		logger.info("Utente registrato con successo.");
	}
	
}
