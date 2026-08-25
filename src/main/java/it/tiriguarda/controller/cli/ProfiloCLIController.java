package it.tiriguarda.controller.cli;

import java.util.Scanner;

import it.tiriguarda.controller.app.ProfiloAppController;
import it.tiriguarda.dto.DatiProfiloBean;
import it.tiriguarda.exception.UtenteNonLoggatoException;

public class ProfiloCLIController {
	private ProfiloAppController controller = new ProfiloAppController();
	private ModificaPwdCLIController pwdController = new ModificaPwdCLIController();
	private ModificaTelefonoCLIController telController = new ModificaTelefonoCLIController();
	
	public void avviaProfilo(Scanner scanner) {
		
		while (true) {
			ViewCLI.stampaTitolo("Profilo");
			
			try {
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
					pwdController.avvia(scanner);
					break;
					
				case "2":
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