package it.tiriguarda.controller.cli;

import java.util.Scanner;

import it.tiriguarda.controller.app.ProfiloAppController;
import it.tiriguarda.dto.DatiProfiloBean;
import it.tiriguarda.exception.UtenteNonLoggatoException;

public class ProfiloCLIController {
	
	public void avviaProfilo(Scanner scanner) {
		
		while (true) {
			ViewCLI.stampaTitolo("Profilo");
			
			try {
				ProfiloAppController controller = new ProfiloAppController();
				DatiProfiloBean bean = controller.getDatiProfilo();
				
				ViewCLI.stampaMessaggio("Username: " + bean.getUsername());
				ViewCLI.stampaMessaggio("Telefono: " + bean.getNumTelefono());
				ViewCLI.stampaMessaggio("Sesso: " + bean.getSesso().toString().toLowerCase());

				}catch (UtenteNonLoggatoException e) {
				ViewCLI.stampaErroreSistema(e.getMessage());
				throw e;
			} 
			
			ViewCLI.stampaSeparatore();
			ViewCLI.mostraMenu(
					"Modifica Password", 
					"Modifica Numero di Telefono");
			
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
					
				case "q":
					return;
					
				default:
					ViewCLI.stampaInvalido();
			}
		}
	}
}