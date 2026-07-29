package it.tiriguarda.controller.cli;

import java.util.Scanner;

import it.tiriguarda.config.AppConfig;
import it.tiriguarda.config.AppMode;
import it.tiriguarda.manager.SmsScheduler;

public class SceltaConfigCLIController {
	public void avviaScelta(Scanner scanner) {
		
		while (true) {
			ViewCLI.stampaTitolo("Configurazione versione");
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
				ViewCLI.stampaInvalido();
			}
		}
	}
		private void login(Scanner scanner) {
			LoginCLIController loginCLI = new LoginCLIController();
            loginCLI.avviaLogin(scanner); 
            }
		
}
