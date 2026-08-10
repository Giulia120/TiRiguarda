package it.tiriguarda.controller.cli;

import java.util.Scanner;

import it.tiriguarda.controller.app.RiepilogoAppController;
import it.tiriguarda.domain.ProtocolloPrEP;
import it.tiriguarda.domain.Rapporto;
import it.tiriguarda.domain.Test;
import it.tiriguarda.dto.RiepilogoBean;
import it.tiriguarda.exception.DataFuturaException;
import it.tiriguarda.exception.DatabaseNonRaggiungibileException;
import it.tiriguarda.exception.DatiIncompletiException;
import it.tiriguarda.exception.FileSystemNonRaggiungibileException;

public class RiepilogoCLIController {
	@SuppressWarnings("java:S106")
	public void mostraRiepilogo(Scanner scanner) {
		ViewCLI.stampaTitolo("Riepilogo Test");
		try {
			RiepilogoBean bean = new RiepilogoBean();
			
			bean.setData(ViewCLI.leggiData(scanner));			
			
            RiepilogoAppController controller = new RiepilogoAppController();
            controller.effettuaRiepilogo(bean);
            
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
            
        } catch (DatabaseNonRaggiungibileException | FileSystemNonRaggiungibileException e) {
            ViewCLI.stampaErroreCriticoEChiudi(e.getMessage());
        } catch (DatiIncompletiException | DataFuturaException e) {
			ViewCLI.stampaErrore(e.getMessage());
			return;
			}
		
		String input = scanner.nextLine();
		if (input.equalsIgnoreCase("q")) {
			return;
		}
	}
		
}
