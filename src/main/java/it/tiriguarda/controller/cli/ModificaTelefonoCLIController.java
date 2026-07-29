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
			System.out.print("\nInserisci il nuovo numero di telefono (oppure 'q' per annullare): ");
			String nuovoTelefono = scanner.nextLine().trim();
			
			if (nuovoTelefono.equalsIgnoreCase("q")) {
				System.out.println("\n[INFO] Modifica numero annullata. Torno al profilo...");
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
				System.out.println("\n[ATTENZIONE]: " + e.getMessage());
				System.out.println("Riprova a inserire il dato.");
				
			} catch (DatabaseNonRaggiungibileException e) {
				System.out.println("\n[ERRORE DB]: " + e.getMessage());
				System.out.println("-> Ritorno al menu di configurazione del database...");
				return;
				
			} catch (IllegalStateException e) {
				System.out.println("\n[ERRORE SESSIONE]: " + e.getMessage());
				System.out.println("-> Ritorno alla schermata di Login...");
				return;
			}
		}
	}
}