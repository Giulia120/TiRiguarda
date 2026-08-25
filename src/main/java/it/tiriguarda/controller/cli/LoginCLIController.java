package it.tiriguarda.controller.cli;

import java.util.Scanner;

import it.tiriguarda.controller.app.LoginAppController;
import it.tiriguarda.dto.CredenzialiBean;
import it.tiriguarda.exception.CredenzialiErrateException;
import it.tiriguarda.exception.DatiIncompletiException;

public class LoginCLIController {
	private RegistraUtenteCLIController registrazioneController = new RegistraUtenteCLIController();
	private MenuPrincipaleCLIController menuController = new MenuPrincipaleCLIController();
	private LoginAppController loginController = new LoginAppController();
	
	public void avviaLogin(Scanner scanner) {
		boolean fine = false;
		
		while (!fine) {
			ViewCLI.stampaTitolo("Login");
			ViewCLI.mostraMenu(
					"Accedi",
					"Registrati",
					"Continua come ospite");
			
			String scelta = scanner.nextLine();
			
			switch (scelta) {
				case "1":
					fine = eseguiAccesso(scanner);
					break;
				case "2":
					registrazioneController.avviaRegistrazione(scanner);
					break;
				case "3":
					menuController.avviaMenu(scanner);
					break;
				case "q":
					fine = true;
					break;
				default: ViewCLI.stampaInvalido();
			}
		}
	}
	
	private boolean eseguiAccesso(Scanner scanner) {
		ViewCLI.stampaMessaggio("Username: ");
		String username = scanner.nextLine();
		
		ViewCLI.stampaMessaggio("Password: ");
		String password = scanner.nextLine();
		
		
		try {
			CredenzialiBean bean = new CredenzialiBean();
			bean.setUsername(username);
			bean.setPassword(password);
			
			loginController.effettuaLogin(bean);
			menuController.avviaMenu(scanner);
			
			return false;
		} catch (CredenzialiErrateException | DatiIncompletiException e) {
			ViewCLI.stampaErrore(e.getMessage());
			scanner.nextLine();
			return false;
		}
	}
}