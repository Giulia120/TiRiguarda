package it.tiriguarda.controller.cli;

import java.util.Scanner;

import it.tiriguarda.domain.SessoBiologico;
import it.tiriguarda.domain.Utente;
import it.tiriguarda.service.SessionManager;

public class ProfiloCLIController {

	public void avviaProfilo(Scanner scanner) {
		
		while (true) {
			Utente utenteCorrente = SessionManager.getInstance().getUtenteLoggato();
			
			if (utenteCorrente == null) {
				System.out.println("\n[ERRORE CRITICO]: Nessun utente loggato in sessione.");
				System.out.println("Torno al Login...");
				return;
			}
			
			System.out.println("\n========================================");
			System.out.println("              IL MIO PROFILO            ");
			System.out.println("========================================");
			
			System.out.println("Username: " + utenteCorrente.getUsername());
			System.out.println("Telefono: " + utenteCorrente.getNumeroTelefono());
			
			if (utenteCorrente.getSessoBiologico() == SessoBiologico.FEMMINILE) {
				System.out.println("Sesso:    Femminile");
			} else {
				System.out.println("Sesso:    Maschile");
			}
			
			System.out.println("----------------------------------------");
			System.out.println("1. Modifica Password");
			System.out.println("2. Modifica Numero di Telefono");
			System.out.println("3. Torna al Menu Principale");
			System.out.print("Scegli un'opzione: ");
			
			String scelta = scanner.nextLine().trim();
			
			switch (scelta) {
				case "1":
					ModificaPwdCLIController pwdController = new ModificaPwdCLIController();
					pwdController.avvia(scanner);
					break;
					
				case "2":
					ModificaTelefonoCLIController telController = new ModificaTelefonoCLIController();
					telController.avvia(scanner);
					break;
					
				case "3":
					System.out.println("\nTorno al Menu Principale...");
					return;
					
				default:
					System.out.println("\n[ERRORE] Scelta non valida. Inserisci 1, 2 o 3.");
			}
		}
	}
}