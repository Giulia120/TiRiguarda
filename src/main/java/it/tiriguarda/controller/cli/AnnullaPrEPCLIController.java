package it.tiriguarda.controller.cli;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import it.tiriguarda.controller.app.AnnullaPrEPAppController;
import it.tiriguarda.exception.AnnullamentoPrEPException;


public class AnnullaPrEPCLIController {
	private static final Logger logger = Logger.getLogger(AnnullaPrEPCLIController.class.getName());
	public boolean avvioAnnullamento(Scanner scanner) {
		AnnullaPrEPAppController controller = new AnnullaPrEPAppController();
		try {
			System.out.println("...Verifico lo stato del tuo protocollo...");
			controller.verificaStatoPrEP();
			System.out.println("Stato del tuo protocollo attivo");
			return confermaAnnullamento(controller, scanner);
		}catch(IllegalStateException e) {
			logger.log(Level.WARNING, e.getMessage());
			ViewCLI.stampaErroreSistema(e.getMessage());
			throw e;
		}catch(AnnullamentoPrEPException e) {
			logger.log(Level.WARNING, "Protocollo PrEP gia annullato", e);
			return false;
		}catch(Exception e) {
			logger.log(Level.SEVERE, "Errore imprevisto durante annullamento PrEP", e);
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
				}catch(Exception e) {
					logger.log(Level.SEVERE, "Errore imprevisto durante annullamento PrEP");
					return false;
				}	
			}
			else {
				System.out.println("Protocollo PrEP ancora attivo.");
				return false;
			}
	}
}
