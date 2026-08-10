package it.tiriguarda.controller.cli;

import java.util.Scanner;

import it.tiriguarda.controller.app.AnnullaPrEPAppController;
import it.tiriguarda.exception.AnnullamentoPrEPException;
import it.tiriguarda.exception.DatabaseNonRaggiungibileException;


public class AnnullaPrEPCLIController {
	public boolean avvioAnnullamento(Scanner scanner) {
		AnnullaPrEPAppController controller = new AnnullaPrEPAppController();
		try {
			controller.verificaStatoPrEP();
			return confermaAnnullamento(controller, scanner);
		}catch(IllegalStateException e) {
			ViewCLI.stampaErroreSistema(e.getMessage());
			throw e;
		}catch(AnnullamentoPrEPException e) {
			ViewCLI.stampaErrore(e.getMessage());
			return false;
		}catch(DatabaseNonRaggiungibileException e) {
	        ViewCLI.stampaErroreCriticoEChiudi(e.getMessage());
	        return false;
	    }
	}
		@SuppressWarnings("java:S106")
		private boolean confermaAnnullamento(AnnullaPrEPAppController controller, Scanner scanner) {
			ViewCLI.stampaTitolo("Annullamento Prep");
			System.out.println("Sei sicuro di voler annullare il tuo protocollo PrEP?");
			System.out.print("Rispondi si/no: ");
			String risposta = scanner.nextLine();
			if(risposta.equalsIgnoreCase("q")) {
		        return false;
		    }
			if(risposta.equalsIgnoreCase("si")) {
				try {
					controller.annullaPrEP();
					ViewCLI.stampaSuccesso(scanner);
					return true;
				}catch(DatabaseNonRaggiungibileException e) {
		            ViewCLI.stampaErroreCriticoEChiudi(e.getMessage());
		            return false;
				}catch(IllegalStateException e) {
					ViewCLI.stampaErroreSistema(e.getMessage());
					throw e;
					}	
			}
			else {
				System.out.println("Protocollo PrEP ancora attivo.");
				return false;
			}
	}
}
