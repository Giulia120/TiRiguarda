package it.tiriguarda.dao;

import it.tiriguarda.domain.Utente;

public interface UtenteDAO {
	void registraUtente(Utente utente);
	
	Utente trovaPerUsername(String username);
	
	void eliminaProtocolloAttivo(Utente utente);
	
	void aggiornaPwdUtente(Utente utente);
	
	void aggiornaTelUtente(Utente utente);
}