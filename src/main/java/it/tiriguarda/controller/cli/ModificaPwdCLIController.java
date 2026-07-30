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
			System.out.print("\nInserisci la vecchia password: ");
			String vecchiaPwd = scanner.nextLine().trim();
			
			if (vecchiaPwd.equalsIgnoreCase("q")) {
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