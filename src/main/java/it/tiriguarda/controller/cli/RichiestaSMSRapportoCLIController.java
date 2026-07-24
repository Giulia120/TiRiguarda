package it.tiriguarda.controller.cli;

import java.text.SimpleDateFormat;
import java.util.Scanner;
import it.tiriguarda.dto.RapportoBean;

public class RichiestaSMSRapportoCLIController {

	public void avvia(RapportoBean beanAggiornato, Scanner scanner) {
		
		System.out.println("\n========================================");
		System.out.println("          ATTENZIONE: RISCHIO RILEVATO  ");
		System.out.println("========================================");
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String dataFormattata = sdf.format(beanAggiornato.getDataFinePeriodoFinestra());
		
		System.out.println("Data fine periodo finestra: " + dataFormattata);
		System.out.println("Vuoi attivare le notifiche SMS per ricordarti di fare il test?");
		System.out.print("Rispondi (si/no): ");
		
		String risposta = scanner.nextLine();
		
		if (risposta.equalsIgnoreCase("si")) {
			System.out.println("[INFO] Hai detto SI agli SMS! (Notifiche attivate)");
		} else {
			System.out.println("[INFO] Hai detto NO agli SMS.");
		}
		
		mostraSchermataSuccesso();
	}
	
	private void mostraSchermataSuccesso() {
		System.out.println("\n****************************************");
		System.out.println("*  Rapporto registrato con successo!   *");
		System.out.println("****************************************");
	}
}