package it.tiriguarda.controller.cli;

import java.util.Scanner;

import it.tiriguarda.controller.app.AnnullaPrEPAppController;
import it.tiriguarda.exception.AnnullamentoPrEPException;
import it.tiriguarda.exception.DatabaseNonRaggiungibileException;


public class AnnullaPrEPCLIController {
	public boolean avvioAnnullamento(Scanner scanner) {
		AnnullaPrEPAppController controller = new AnnullaPrEPAppController();
		try {
			System.out.println("...Verifico lo stato del tuo protocollo...");
			controller.verificaStatoPrEP();
			System.out.println("Stato del tuo protocollo attivo");
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
		
		private boolean confermaAnnullamento(AnnullaPrEPAppController controller, Scanner scanner) {
			System.out.println("Sei sicuro di voler annullare il tuo protocollo PrEP?");
			System.out.print("Rispondi si/no: ");
			String risposta = scanner.nextLine();
			if(risposta.equalsIgnoreCase("q")) {
		        return false;
		    }
			if(risposta.equalsIgnoreCase("si")) {
				try {
					controller.annullaPrEP();
					ViewCLI.stampaSuccesso();
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
