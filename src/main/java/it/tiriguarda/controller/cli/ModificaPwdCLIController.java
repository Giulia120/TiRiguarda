package it.tiriguarda.controller.cli;

import java.util.Scanner;

import it.tiriguarda.controller.app.ModificaPwdAppController;
import it.tiriguarda.dto.CambioPwdBean;
import it.tiriguarda.exception.CredenzialiErrateException;
import it.tiriguarda.exception.DatabaseNonRaggiungibileException;
import it.tiriguarda.exception.DatiIncompletiException;
import it.tiriguarda.exception.UtenteNonLoggatoException;

public class ModificaPwdCLIController {
	
	public void avvia(Scanner scanner) {
		
		ViewCLI.stampaTitolo("Modifica Password");
		
		while (true) {
			System.out.print("\nInserisci la vecchia password: ");
			String vecchiaPwd = scanner.nextLine().trim();
			
			if (vecchiaPwd.equalsIgnoreCase("q")) {
				return;
			}
			
			System.out.print("Inserisci la nuova password: ");
			String nuovaPwd = scanner.nextLine().trim();
			
			if (nuovaPwd.equalsIgnoreCase("q")) {
				return;
			}
			
			try {
				CambioPwdBean bean = new CambioPwdBean();
				bean.setVecchiaPassword(vecchiaPwd);
				bean.setNuovaPassword(nuovaPwd);
				
				ModificaPwdAppController appController = new ModificaPwdAppController();
				appController.cambiaPassword(bean);
				
				ViewCLI.stampaSuccesso(scanner);
				return;
				
			} catch (DatiIncompletiException | CredenzialiErrateException e) {
				ViewCLI.stampaErrore(e.getMessage());
				System.out.println("Premi INVIO per riprovare...");
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