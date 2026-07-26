package it.tiriguarda.controller.cli;

import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import it.tiriguarda.controller.app.RegistraRapportoAppController;
import it.tiriguarda.domain.LivelloRischio;
import it.tiriguarda.dto.RapportoBean;

public class RichiestaSMSRapportoCLIController {

	public void avvia(RapportoBean bean, Scanner scanner) {
		
		System.out.println("\n========================================");
		System.out.println("    ATTENZIONE: RISCHIO RILEVATO  ");
		System.out.println("========================================");
		
		if (bean.getRischio() == LivelloRischio.ALTO) {
			System.out.print("Questo rapporto è ad ALTO rischio, quindi ");
		} else if (bean.getRischio() == LivelloRischio.BASSO) {
			System.out.print("Questo rapporto è a BASSO rischio, ma ");
		}
		System.out.println("ti consigliamo di fare un test allo scadere del periodo finestra.");
		
		if (bean.getDataFinePeriodoFinestra() != null) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			String dataFormattata = bean.getDataFinePeriodoFinestra().format(formatter);
			System.out.println("Data fine periodo finestra: " + dataFormattata);
		}
		
		while (true) {
			System.out.println("\nVuoi attivare le notifiche SMS per ricordarti di fare il test?");
			System.out.print("Rispondi (si/no, oppure 'q' per annullare TUTTA la registrazione): ");
			
			String risposta = scanner.nextLine().trim().toLowerCase();
			
			if (risposta.equals("q")) {
				System.out.println("\n[INFO] Registrazione rapporto annullata! Torno al menu principale...");
				return;
			}
			if (risposta.equals("si") || risposta.equals("no")) {
				if (risposta.equals("si")) {
					System.out.println("\n[INFO] Hai detto SI agli SMS! (Notifiche attivate)");
					System.out.println("Daje");
				} else {
					System.out.println("\n[INFO] Hai detto NO agli SMS.");
				}
				
				RegistraRapportoAppController appController = new RegistraRapportoAppController();
				appController.salvaRapportoDefinitivo(bean);
				
				System.out.println("\nRapporto registrato definitivamente con successo! Torno al menu principale...");
				return;
			}
			
			System.out.println("Risposta non valida. Inserisci 'si', 'no' o 'q'.");
		}
	}
}