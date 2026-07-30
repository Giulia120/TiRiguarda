package it.tiriguarda.controller.cli;

import java.util.Scanner;

import it.tiriguarda.controller.app.ModificaTelefonoAppController;
import it.tiriguarda.dto.CambioTelefonoBean;
import it.tiriguarda.exception.DatabaseNonRaggiungibileException;
import it.tiriguarda.exception.DatiIncompletiException;

public class ModificaTelefonoCLIController {

	public void avvia(Scanner scanner) {
		
		ViewCLI.stampaTitolo("Modfica Telefono");
		
		while (true) {
			System.out.print("\nInserisci il nuovo numero di telefono: ");
			String nuovoTelefono = scanner.nextLine().trim();
			
			if (nuovoTelefono.equalsIgnoreCase("q")) {
				return;
			}
			
			try {
				CambioTelefonoBean bean = new CambioTelefonoBean();
				bean.setNuovoTelefono(nuovoTelefono);
				
				ModificaTelefonoAppController appController = new ModificaTelefonoAppController();
				appController.cambiaTelefono(bean);
				
				ViewCLI.stampaSuccesso();
				return;
				
			} catch (DatiIncompletiException e) {
				ViewCLI.stampaErrore(e.getMessage());
				System.out.println("Riprova a inserire i dati.");
				
			} catch (DatabaseNonRaggiungibileException e) {
				ViewCLI.stampaErroreCriticoEChiudi(e.getMessage());
				
			} catch (IllegalStateException e) {
				ViewCLI.stampaErroreSistema(e.getMessage());
				return;
			}
		}
	}
}