package it.tiriguarda.controller.cli;

import java.util.Scanner;

import it.tiriguarda.controller.app.LoginAppController;
import it.tiriguarda.dto.CredenzialiBean;
import it.tiriguarda.exception.CredenzialiErrateException;

public class LoginCLIController {

	public void avviaLogin(Scanner scanner) {
		boolean fine = false;
		
		while (!fine) {
			System.out.println("\n========================================");
			System.out.println("                  LOGIN                 ");
			System.out.println("========================================");
			System.out.println("1 - Accedi");
			System.out.println("2 - Registrati");
			System.out.println("q - Esci");
			System.out.print("Scegli un'opzione: ");
			
			String scelta = scanner.nextLine();
			
			switch (scelta) {
				case "1":
					boolean successo = eseguiAccesso(scanner);
					if (successo) {
						fine = true;
					}
					break;
				case "2":
					RegistraUtenteCLIController registrazioneController = new RegistraUtenteCLIController();
					registrazioneController.avviaRegistrazione(scanner);
					break;
				case "q":
					fine = true;
					break;
				default:
					System.out.println("\n[ERRORE] Scelta non valida.");
			}
		}
	}

	private boolean eseguiAccesso(Scanner scanner) {
		System.out.print("Username: ");
		String username = scanner.nextLine();
		
		System.out.print("Password: ");
		String password = scanner.nextLine();
		
		if (username.trim().isEmpty() || password.trim().isEmpty()) {
			System.out.println("\n[ERRORE] Dati mancanti.");
			return false;
		}
		
		CredenzialiBean bean = new CredenzialiBean();
		bean.setUsername(username);
		bean.setPassword(password);
		
		try {
			LoginAppController appController = new LoginAppController();
			appController.effettuaLogin(bean);
			
			MenuPrincipaleCLIController menuController = new MenuPrincipaleCLIController();
			menuController.avviaMenu(scanner);
			
			return true;
		} catch (CredenzialiErrateException e) {
			System.out.println("\n[ERRORE] " + e.getMessage());
			return false;
		}
	}
}