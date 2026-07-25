package it.tiriguarda.controller.app;

import it.tiriguarda.dao.DAOFactoryProvider;
import it.tiriguarda.dao.UtenteDAO;
import it.tiriguarda.domain.Utente;
import it.tiriguarda.exception.CredenzialiErrateException;
import it.tiriguarda.service.SessionManager;

public class GestisciDatiPeronaliAppController {
	
	private Utente verificaUtente(String password) throws CredenzialiErrateException {
		Utente utenteCorrente = SessionManager.getInstance().getUtenteLoggato();
		if (!utenteCorrente.verificaPassword(password)) {
            throw new CredenzialiErrateException();
        }
		return utenteCorrente;
	}
	
	public void modificaTel(String nuovoTel, String password) {
		Utente utenteCorrente = verificaUtente(password);
		utenteCorrente.setNumeroTelefono(nuovoTel);
		UtenteDAO dao = DAOFactoryProvider.getDAOFactory().createUtenteDAO();
        dao.aggiornaUtente(utenteCorrente);
	}
	
	public void modificaUser(String nuovoUser, String password) {
		Utente utenteCorrente = verificaUtente(password);
		utenteCorrente.setNumeroTelefono(nuovoTel);
		UtenteDAO dao = DAOFactoryProvider.getDAOFactory().createUtenteDAO();
        dao.aggiornaUtente(utenteCorrente);
	}

}
