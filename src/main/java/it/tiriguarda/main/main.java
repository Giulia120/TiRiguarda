package it.tiriguarda.main;

import it.tiriguarda.config.AppConfig;
import it.tiriguarda.config.AppMode;
import it.tiriguarda.domain.Utente;
import it.tiriguarda.service.SessionManager;

public class main {

	public static void main(String[] args) {
		
		System.out.println("Avvio applicazione in corso...");
		AppConfig.setCurrentMode(AppMode.DEMO); 
		
		Utente utenteFittizio = new Utente("User01", "Giulia", "3331234567");
		SessionManager.getInstance().setUtenteLoggato(utenteFittizio);
		
		System.out.println("Utente di test caricato in sessione.");
		
		System.out.println("Apertura interfaccia grafica...");
		
		
	}
}