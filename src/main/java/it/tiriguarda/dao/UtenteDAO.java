package it.tiriguarda.dao;

import it.tiriguarda.domain.Utente;

public interface UtenteDAO {
	void registraUtente(Utente utente);
	
	Utente trovaPerUsername(String username);
}
