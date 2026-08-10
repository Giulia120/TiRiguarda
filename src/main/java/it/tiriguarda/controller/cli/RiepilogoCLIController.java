package it.tiriguarda.controller.cli;

import java.util.List;
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
            
            stampaSezionePrep(bean.getPrep());
			stampaSezioneRapporti(bean.getRapporti());
			stampaSezioneTest(bean.getTest());
            
        } catch (DatabaseNonRaggiungibileException | FileSystemNonRaggiungibileException e) {
            ViewCLI.stampaErroreCriticoEChiudi(e.getMessage());
        } catch (DatiIncompletiException | DataFuturaException e) {
			ViewCLI.stampaErrore(e.getMessage());
			return;
			}
	}
	@SuppressWarnings("java:S106")
	private void stampaSezionePrep(List<ProtocolloPrEP> listaPrep) {
		System.out.println("\n--- PROTOCOLLO PrEP ---");
		if (listaPrep == null || listaPrep.isEmpty()) {
			System.out.println("Nessun protocollo PrEP registrato.");
		} else {
			for (ProtocolloPrEP p : listaPrep) {
				System.out.println("ID: " + p.getIdProtocollo() + " | Tipo: " + p.getTipoPrEP() + " | Data Inizio: " + p.getDataInizio());
			}
		}
	}
	@SuppressWarnings("java:S106")
	private void stampaSezioneRapporti(List<Rapporto> listaRapporti) {
		System.out.println("\n--- RAPPORTI ---");
		if (listaRapporti == null || listaRapporti.isEmpty()) {
			System.out.println("Nessun rapporto registrato.");
		} else {
			for (Rapporto r : listaRapporti) {
				System.out.println("ID: " + r.getIdRapporto() + " | Data: " + r.getData() + " | Rischio: " + r.getRischio());
			}
		}
	}
	@SuppressWarnings("java:S106")
	private void stampaSezioneTest(List<Test> listaTest) {
		System.out.println("\n--- TEST ---");
		if (listaTest == null || listaTest.isEmpty()) {
			System.out.println("Nessun test registrato.");
		} else {
			for (Test t : listaTest) {
				System.out.println("ID: " + t.getidTest() + " | Tipo: " + t.getTipo() + " | Data: " + t.getData());
			}
		}
	}
		
}
