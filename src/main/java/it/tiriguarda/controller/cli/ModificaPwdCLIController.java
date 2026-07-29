package it.tiriguarda.controller.cli;

import java.util.Scanner;

import it.tiriguarda.controller.app.ModificaPwdAppController;
import it.tiriguarda.dto.CambioPwdBean;
import it.tiriguarda.exception.CredenzialiErrateException;
import it.tiriguarda.exception.DatabaseNonRaggiungibileException;
import it.tiriguarda.exception.DatiIncompletiException;

public class ModificaPwdCLIController {

	public void avvia(Scanner scanner) {
		
		ViewCLI.stampaTitolo("Modifica Password");
		
		while (true) {
			System.out.print("\nInserisci la vecchia password (oppure 'q' per annullare): ");
			String vecchiaPwd = scanner.nextLine().trim();
			
			if (vecchiaPwd.equalsIgnoreCase("q")) {
				System.out.println("\n[INFO] Modifica password annullata. Torno al profilo...");
				return;
			}
			
			System.out.print("Inserisci la nuova password: ");
			String nuovaPwd = scanner.nextLine().trim();
			
			try {
				CambioPwdBean bean = new CambioPwdBean();
				bean.setVecchiaPassword(vecchiaPwd);
				bean.setNuovaPassword(nuovaPwd);
				
				ModificaPwdAppController appController = new ModificaPwdAppController();
				appController.cambiaPassword(bean);
				
				ViewCLI.stampaSuccesso();
				return;
				
			} catch (DatiIncompletiException | CredenzialiErrateException e) {
				System.out.println("\n[ATTENZIONE]: " + e.getMessage());
				System.out.println("Riprova a inserire i dati.");
				
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