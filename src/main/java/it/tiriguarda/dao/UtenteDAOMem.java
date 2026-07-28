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
	public void eliminaProtocolloAttivo(Utente utente) {
		utente.setProtocolloAttivo(null);
	}
	
	@Override
	public void aggiornaPwdUtente(Utente utente) {
		System.out.println("Ciao");
	}
	
	@Override
	public void aggiornaTelUtente(Utente utente) {
		System.out.println("Ciao");
	}
	
	@Override
	public String recuperaNumeroTelefono(String username) {
		
	}
}
