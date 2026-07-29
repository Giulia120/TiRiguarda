package it.tiriguarda.controller.cli;

import java.util.Scanner;

import it.tiriguarda.controller.app.RiepilogoAppController;
import it.tiriguarda.domain.ProtocolloPrEP;
import it.tiriguarda.domain.Rapporto;
import it.tiriguarda.domain.Test;
import it.tiriguarda.dto.RiepilogoBean;

public class RiepilogoCLIController {
	public void mostraRiepilogo(Scanner scanner) {
		System.out.println("\n========================================");
		System.out.println("          RIEPILOGO TEST                ");
		System.out.println("========================================");
		System.out.println("(Digita 'q' in qualsiasi momento per tornare al menu)\n");
		
		RiepilogoAppController controller = new RiepilogoAppController();
		RiepilogoBean bean = controller.effettuaRiepilogo();
		
		System.out.println("\n--- PROTOCOLLO PrEP ---");
		if (bean.getPrep() == null || bean.getPrep().isEmpty()) {
			System.out.println("Nessun protocollo PrEP registrato.");
		} else {
			for (ProtocolloPrEP p : bean.getPrep()) {
				System.out.println("ID: " + p.getIdProtocollo() + " | Tipo: " + p.getTipoPrEP() + " | Data Inizio: " + p.getDataInizio());
			}
		}
		
		System.out.println("\n--- RAPPORTI ---");
		if (bean.getRapporti() == null || bean.getRapporti().isEmpty()) {
			System.out.println("Nessun rapporto registrato.");
		} else {
			for (Rapporto r : bean.getRapporti()) {
				System.out.println("ID: " + r.getIdRapporto() + " | Data: " + r.getData() + " | Rischio: " + r.getRischio());
			}
		}
		
		System.out.println("\n--- TEST ---");
		if (bean.getTest() == null || bean.getTest().isEmpty()) {
			System.out.println("Nessun test registrato.");
		} else {
			for (Test t : bean.getTest()) {
				System.out.println("ID: " + t.getidTest() + " | Tipo: " + t.getTipo() + " | Data: " + t.getData());
			}
		}
		String input = scanner.nextLine();
		if (input.equalsIgnoreCase("q")) {
			return;
		}
	}
		
}
