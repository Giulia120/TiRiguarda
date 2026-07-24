package it.tiriguarda.controller.cli;

import java.util.Scanner;
@SuppressWarnings("java:S106")
public class SceltaPrEPCLIController {
	public void avviaPrEP(Scanner scanner) {
		boolean fine = false;
		while(!fine) {
			System.out.println("\n========================================");
			System.out.println("                  PrEP                 ");
			System.out.println("========================================");
			System.out.println("Cosa vuoi fare?");
			System.out.println("1 - Daily");
			System.out.println("2 - On Demand");
			System.out.println("3 - Annulla PrEP");
			System.out.println("(Digita 'q' in qualsiasi momento tornare al menu)\n");
			System.out.print("Scegli un'opzione (1-3): ");
			
			String scelta = scanner.nextLine();
			switch(scelta) {
				case "1":
					System.out.println("scelta PrEP daily");
					break;
				case "2":
					System.out.println("scelta PrEP on demand");
					break;
				case "3":
					AnnullaPrEPCLIController annullaPrEPcontroller = new AnnullaPrEPCLIController();
					boolean tornaMenu = annullaPrEPcontroller.avvioAnnullamento(scanner);
					if(!tornaMenu) {
						return;
					}
					break;
				case "q":
					return;
				default:
					System.out.println("\n[ERRORE] Scelta non valida! Inserisci un numero da 1 a 3.");
			}
		}
	}
}
