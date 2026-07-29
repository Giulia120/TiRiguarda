package it.tiriguarda.controller.cli;

import java.util.Scanner;

import it.tiriguarda.config.AppConfig;
import it.tiriguarda.config.AppMode;
import it.tiriguarda.manager.SmsScheduler;

public class SceltaConfigCLIController {
	public void avviaScelta(Scanner scanner) {
		
		while (true) {
			System.out.println("\n========================================");
			System.out.println("                  SCEGLI LA VERSIONE                 ");
			System.out.println("========================================");
			System.out.println("1 - Demo");
			System.out.println("2 - Full");
			System.out.println("q - Esci");
			System.out.print("Scegli un'opzione: ");
			
			String scelta = scanner.nextLine();
			switch (scelta) {
			case "1":
				AppConfig.setCurrentMode(AppMode.DEMO);
				SmsScheduler.getInstance().avviaScheduler();
				login(scanner);
				break;
			case "2":
				AppConfig.setCurrentMode(AppMode.FULL);
				SmsScheduler.getInstance().avviaScheduler();
				login(scanner);
				break;
			case "q":
				break;
			default:
				System.out.println("\n[ERRORE] Scelta non valida.");
			}
		}
	}
		private void login(Scanner scanner) {
			LoginCLIController loginCLI = new LoginCLIController();
            loginCLI.avviaLogin(scanner); 
            }
		
}
