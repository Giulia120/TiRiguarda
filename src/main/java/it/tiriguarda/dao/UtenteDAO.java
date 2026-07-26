package it.tiriguarda.dao;

import it.tiriguarda.domain.Utente;

public interface UtenteDAO {
	void registraUtente(Utente utente);
	
	Utente trovaPerUsername(String username);
	
<<<<<<< HEAD
	void eliminaProtocolloAttivo(Utente utente);
=======
	void aggiornaUtente(Utente utente);
>>>>>>> 6da2f0352818371590752a3ac54338c75b3b4e5f
}
