package it.tiriguarda.controller.cli;

import java.util.Scanner;

import it.tiriguarda.controller.app.AnnullaPrEPController;
import it.tiriguarda.exception.PrEPAnnullataException;
import it.tiriguarda.exception.PrEPNonEsistenteException;
import it.tiriguarda.exception.TiRiguardaException;


public class AnnullaPrEPCLIController {
	public boolean avvioAnnullamento(Scanner scanner) {
		AnnullaPrEPController controller = new AnnullaPrEPController();
		try {
			System.out.println("...Verifico lo stato del tuo protocollo...");
			controller.verificaStatoPrEP();
			System.out.println("Stato del tuo protocollo attivo");
			return confermaAnnullamento(controller);
		}catch(PrEPNonEsistenteException e) {
			System.out.println("ERRORE: " + e.getMessage());
			return false;
		}catch(PrEPAnnullataException e) {
			System.out.println("ERRORE: " + e.getMessage());
			return false;
		} catch(TiRiguardaException e) {
			System.out.println("ERRORE: " + e.getMessage());
			return false;
		}catch(Exception e) {
			System.out.println("ERRORE: " + e.getMessage());
			return false;
		}
	}
		
		private boolean confermaAnnullamento(AnnullaPrEPController controller) {
			Scanner scanner = new Scanner(System.in);
			System.out.println("Sei sicuro di voler annullare il tuo protocollo PrEP?");
			System.out.print("Rispondi si/no: ");
			String risposta = scanner.nextLine();
			if(risposta.equalsIgnoreCase("q")) {
		        return false;
		    }
			if(risposta.equalsIgnoreCase("si")) {
				try {
					controller.annullaPrEP();
					System.out.println("\n****************************************");
					System.out.println("*  Protocollo annullato con successo!   *");
					System.out.println("****************************************");
					return true;
				}catch(Exception e) {
					System.out.println("ERRORE: " + e.getMessage());
					return false;
				}	
			}
			else {
				System.out.println("Protocollo PrEP ancora attivo.");
				return false;
			}
	}
}
