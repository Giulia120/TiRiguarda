package it.tiriguarda.controller.app;

import java.util.logging.Logger;

import it.tiriguarda.dao.DAOFactory;
import it.tiriguarda.dao.DAOFactoryProvider;
import it.tiriguarda.dao.UtenteDAO;
import it.tiriguarda.domain.Utente;
import it.tiriguarda.dto.UtenteBean;
import it.tiriguarda.exception.DatiIncompletiException;
import it.tiriguarda.exception.UsernameEsistenteException;

public class RegistraUtenteAppController {
	private static final Logger logger = Logger.getLogger(RegistraUtenteAppController.class.getName());
	
	public RegistraUtenteAppController() {}
	
	public void registraUtente (UtenteBean bean) throws DatiIncompletiException, UsernameEsistenteException{
		if(bean.getUsername() == null || bean.getPassword() == null || bean.getNumeroTelefono() == null) {
			throw new DatiIncompletiException();			
		}
		
		DAOFactory factory = DAOFactoryProvider.getDAOFactory();
		UtenteDAO dao = factory.createUtenteDAO();
		if (dao.trovaPerUsername(bean.getUsername()) != null) {
			throw new UsernameEsistenteException();
		}
		Utente nuovoUtente = new Utente(bean.getUsername(), bean.getPassword(), bean.getNumeroTelefono());
		dao.registraUtente(nuovoUtente);
		logger.info("Rapporto registrato con successo.");
		return;
	}
	
}
