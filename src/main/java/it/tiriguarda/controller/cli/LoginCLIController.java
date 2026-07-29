package it.tiriguarda.controller.cli;

import java.util.Scanner;

import it.tiriguarda.controller.app.LoginAppController;
import it.tiriguarda.dto.CredenzialiBean;
import it.tiriguarda.exception.CredenzialiErrateException;
import it.tiriguarda.exception.DatiIncompletiException;

public class LoginCLIController {

	public void avviaLogin(Scanner scanner) {
		boolean fine = false;
		
		while (!fine) {
			ViewCLI.stampaTitolo("Login");
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
				default: ViewCLI.stampaInvalido();
			}
		}
	}

	private boolean eseguiAccesso(Scanner scanner) {
		System.out.print("Username: ");
		String username = scanner.nextLine();
		
		System.out.print("Password: ");
		String password = scanner.nextLine();
		
		
		try {
			CredenzialiBean bean = new CredenzialiBean();
			bean.setUsername(username);
			bean.setPassword(password);
			
			LoginAppController appController = new LoginAppController();
			appController.effettuaLogin(bean);
			
			MenuPrincipaleCLIController menuController = new MenuPrincipaleCLIController();
			menuController.avviaMenu(scanner);
			
			return true;
		} catch (CredenzialiErrateException | DatiIncompletiException e) {
			System.out.println("\n[ERRORE] " + e.getMessage());
			return false;
		}
	}
}