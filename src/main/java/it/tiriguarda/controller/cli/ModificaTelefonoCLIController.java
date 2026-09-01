package it.tiriguarda.controller.cli;

import java.util.Scanner;

import it.tiriguarda.controller.app.ModificaTelefonoAppController;
import it.tiriguarda.dto.CambioTelefonoBean;
import it.tiriguarda.exception.DatabaseNonRaggiungibileException;
import it.tiriguarda.exception.DatiIncompletiException;
import it.tiriguarda.exception.UtenteNonLoggatoException;

public class ModificaTelefonoCLIController {
	
	public void avvia(Scanner scanner) {
		
		ViewCLI.stampaTitolo("Modfica Telefono");
		
		while (true) {
			ViewCLI.stampaMessaggio("Inserisci il nuovo numero di telefono: ");
			String nuovoTelefono = scanner.nextLine().trim();
			
			if (nuovoTelefono.equalsIgnoreCase("q")) {
				return;
			}
			
			try {
				CambioTelefonoBean bean = new CambioTelefonoBean();
				bean.setNuovoTelefono(nuovoTelefono);
				ModificaTelefonoAppController appController = new ModificaTelefonoAppController();
				appController.cambiaTelefono(bean);
				
				ViewCLI.stampaSuccesso(scanner);
				return;
				
			} catch (DatiIncompletiException e) {
				ViewCLI.stampaErrore(e.getMessage());
				ViewCLI.stampaMessaggio("Premi INVIO per riprovare...");
				scanner.nextLine();
				
			} catch (DatabaseNonRaggiungibileException e) {
				ViewCLI.stampaErroreCriticoEChiudi(e.getMessage());
				
			} catch (UtenteNonLoggatoException e) {
				ViewCLI.stampaErroreSistema(e.getMessage());
				throw e;
			}
		}
	}
}