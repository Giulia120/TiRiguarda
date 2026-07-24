package it.tiriguarda.controller.cli;

import java.util.Scanner;

import it.tiriguarda.controller.app.RegistraUtenteAppController;
import it.tiriguarda.dto.UtenteBean;
import it.tiriguarda.exception.DatiIncompletiException;
import it.tiriguarda.exception.UsernameEsistenteException;
import it.tiriguarda.util.SecurityUtil;

public class RegistraUtenteCLIController {

	public void avviaRegistrazione(Scanner scanner) {
		boolean fine = false;

		while (!fine) {
			System.out.println("\n========================================");
			System.out.println("              REGISTRAZIONE             ");
			System.out.println("========================================");
			System.out.println("(Digita 'q' sul campo Username per tornare indietro)");

			System.out.print("Username: ");
			String username = scanner.nextLine();
			
			if (username.equalsIgnoreCase("q")) {
				return;
			}

			System.out.print("Password: ");
			String password = scanner.nextLine();

			System.out.print("Numero di telefono: ");
			String telefono = scanner.nextLine();

			UtenteBean bean = new UtenteBean();
			bean.setUsername(username);
			
			if (password.isBlank()) {
				bean.setPassword("");
			} else {
				bean.setPassword(SecurityUtil.hashPassword(password));
			}
			
			bean.setNumeroTelefono(telefono);

			try {
				RegistraUtenteAppController appController = new RegistraUtenteAppController();
				appController.registraUtente(bean);
				
				System.out.println("\nRegistrazione completata con successo! Torno al menu di Login...");
				fine = true;
				
			} catch (DatiIncompletiException | UsernameEsistenteException e) {
				System.out.println("\n[ERRORE] " + e.getMessage());
			}
		}
	}
}