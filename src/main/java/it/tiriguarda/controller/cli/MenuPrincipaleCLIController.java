package it.tiriguarda.controller.cli;

import java.util.Scanner;

public class MenuPrincipaleCLIController {

	public void avviaMenu(Scanner scanner) {
		boolean esci = false;

		while (!esci) {
			System.out.println("\n========================================");
			System.out.println("            MENU PRINCIPALE           ");
			System.out.println("========================================");
			System.out.println("1 - Registra Rapporto");
			System.out.println("2 - Gestione Test");
			System.out.println("3 - Visualizza Profilo");
			System.out.println("4 - Riepilogo");
			System.out.println("5 - Scelta PrEP");
			System.out.println("6 - Questionario");
			System.out.println("7 - Informazioni");
			System.out.println("8 - Logout");
			System.out.print("Scegli un'opzione (1-8): ");

			String scelta = scanner.nextLine();

			switch (scelta) {
				case "1":
					RegistraRapportoCLIController registraController = new RegistraRapportoCLIController();
					registraController.avviaRegistrazioneRapporto(scanner);
					break;
					
				case "2":
					System.out.println("\n[INFO] Sezione Test aperta.");
					break;
					
				case "3":
					System.out.println("\n[INFO] Sezione Profilo aperta.");
					break;
					
				case "4":
					System.out.println("\n[INFO] Sezione Riepilogo aperta.");
					break;
					
				case "5":
					SceltaPrEPCLIController prEPController = new SceltaPrEPCLIController();
					prEPController.avviaPrEP(scanner);
					break;
					
				case "6":
					System.out.println("\n[INFO] Sezione Questionario aperta.");
					break;
					
				case "7":
					System.out.println("\n[INFO] Sezione Informazioni aperta.");
					break;
					
				case "8":
					System.out.println("\nLogout effettuato con successo. Chiusura sessione CLI.");
					esci = true;
					break;
					
				default:
					System.out.println("\n[ERRORE] Scelta non valida! Inserisci un numero da 1 a 8.");
			}
		}
	}
}