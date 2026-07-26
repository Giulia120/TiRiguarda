package it.tiriguarda.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import it.tiriguarda.domain.Utente;

public class UtenteDAOMem implements UtenteDAO{
	private static final Logger logger = Logger.getLogger(UtenteDAOMem.class.getName());
	private static List<Utente> utentiInMemoria = new ArrayList<>();
	
	@Override
	public void registraUtente (Utente utente) {
		utentiInMemoria.add(utente);
		logger.info("Utente salvato correttamente");
	}
	
	@Override
	public Utente trovaPerUsername(String username) {
		for (Utente u : utentiInMemoria) {
			if (u.getUsername().equals(username)) {
				return u;
			}
		}
		return null;
	}
	
	@Override
	public void aggiornaUtente(Utente utenteAggiornato) {
		for (int i = 0; i < utentiInMemoria.size(); i++) {
			if (utentiInMemoria.get(i).getUsername().equals(utenteAggiornato.getUsername())) {
				utentiInMemoria.set(i, utenteAggiornato);
				logger.info("Utente aggiornato correttamente in memoria");
				return;
			}
		}
		logger.warning("Impossibile aggiornare: utente non trovato in memoria.");
	}
}
