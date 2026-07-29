package it.tiriguarda.controller.cli;

import java.util.Scanner;

import it.tiriguarda.controller.app.RegistraUtenteAppController;
import it.tiriguarda.domain.SessoBiologico;
import it.tiriguarda.dto.UtenteBean;
import it.tiriguarda.exception.DatiIncompletiException;
import it.tiriguarda.exception.UsernameEsistenteException;

public class RegistraUtenteCLIController {

	public void avviaRegistrazione(Scanner scanner) {
		boolean fine = false;

		while (!fine) {
			ViewCLI.stampaTitolo("Registrazione");

			System.out.print("Username: ");
			String username = scanner.nextLine();
			
			if (username.equalsIgnoreCase("q")) {
				return;
			}

			System.out.print("Password: ");
			String password = scanner.nextLine();
			
			if (password.equalsIgnoreCase("q")) {
				return;
			}
			System.out.print("Numero di telefono: ");
			String telefono = scanner.nextLine();
			if (telefono.equalsIgnoreCase("q")) {
				return;
			}
			
			SessoBiologico sesso = leggiSessoBiologico(scanner);


			try {
				UtenteBean bean = new UtenteBean();
				bean.setUsername(username);
				bean.setPassword(password);
				bean.setSessoBiologico(sesso);
				bean.setNumeroTelefono(telefono);
				
				RegistraUtenteAppController appController = new RegistraUtenteAppController();
				appController.registraUtente(bean);
				
				ViewCLI.stampaSuccesso();
				fine = true;
				
			} catch (DatiIncompletiException | UsernameEsistenteException e) {
				System.out.println("\n[ERRORE] " + e.getMessage());
			}
		}
	}
	
	private SessoBiologico leggiSessoBiologico(Scanner scanner) {
        while (true) {
            System.out.println("Sesso Biologico:");
            System.out.println("1 - Femminile");
            System.out.println("2 - Maschile");
            System.out.print("Scegli un'opzione: ");
            
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("q")) return null;
            
            switch (input) {
                case "1": return SessoBiologico.FEMMINILE;
                case "2": return SessoBiologico.MASCHILE;
                default: ViewCLI.stampaInvalido();
            }
        }
    }
}