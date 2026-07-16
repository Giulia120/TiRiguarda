package it.TiRiguarda.service;

import it.TiRiguarda.domain.Utente;

public class SessionManager {
	private Utente utenteLoggato;
	private static SessionManager instance;
	
	private SessionManager() {}
	
	public static SessionManager getInstance() {
		if (instance == null) {
			instance = new SessionManager();
		}
		return instance;
	}
	
	public String getIdUtenteLoggato() {
		return utenteLoggato.getIdUtente();
	}
	
}
