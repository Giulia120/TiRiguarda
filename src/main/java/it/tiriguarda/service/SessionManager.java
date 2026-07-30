package it.tiriguarda.service;

import it.tiriguarda.domain.Utente;

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
	
	public Utente getUtenteLoggato() {
		//return utenteLoggato;
		return null;
	}
	
	public void setUtenteLoggato(Utente utenteLoggato) {
		this.utenteLoggato = utenteLoggato;
	}
	
	public void clearSessione() {
		this.utenteLoggato = null;
	}
}
