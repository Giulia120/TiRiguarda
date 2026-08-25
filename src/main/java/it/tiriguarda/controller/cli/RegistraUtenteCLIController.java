package it.tiriguarda.controller.cli;

import java.util.Scanner;

import it.tiriguarda.controller.app.RegistraUtenteAppController;
import it.tiriguarda.domain.SessoBiologico;
import it.tiriguarda.dto.UtenteBean;
import it.tiriguarda.exception.DatiIncompletiException;
import it.tiriguarda.exception.UsernameEsistenteException;
import it.tiriguarda.exception.UtenteNonLoggatoException;

public class RegistraUtenteCLIController {
	private RegistraUtenteAppController appController = new RegistraUtenteAppController();
	
	public void avviaRegistrazione(Scanner scanner) {
		boolean fine = false;

		while (!fine) {
			ViewCLI.stampaTitolo("Registrazione");

			ViewCLI.stampaMessaggio("Username: ");
			String username = scanner.nextLine();
			
			if (username.equalsIgnoreCase("q")) {
				return;
			}

			ViewCLI.stampaMessaggio("Password: ");
			String password = scanner.nextLine();
			
			if (password.equalsIgnoreCase("q")) {
				return;
			}
			ViewCLI.stampaMessaggio("Numero di telefono: ");
			String telefono = scanner.nextLine();
			if (telefono.equalsIgnoreCase("q")) {
				return;
			}
			
			SessoBiologico sesso = leggiSessoBiologico(scanner);
			if (sesso == null) return;


			try {
				UtenteBean bean = new UtenteBean();
				bean.setUsername(username);
				bean.setPassword(password);
				bean.setSessoBiologico(sesso);
				bean.setNumeroTelefono(telefono);
				
				appController.registraUtente(bean);
				
				ViewCLI.stampaSuccesso(scanner);
				fine = true;
				
			} catch (DatiIncompletiException | UsernameEsistenteException e) {
				ViewCLI.stampaErrore(e.getMessage());
			}catch(UtenteNonLoggatoException e) {
				ViewCLI.stampaErroreSistema(e.getMessage());
				throw e;
			}
		}
	}
	
	private SessoBiologico leggiSessoBiologico(Scanner scanner) {
        while (true) {
        	ViewCLI.stampaMessaggio("Sesso Biologico:\n");
            ViewCLI.mostraMenu(
            		"Femminile", 
            		"Maschile");
            
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