package it.tiriguarda.controller.cli;

import java.util.List;
import java.util.Scanner;

import it.tiriguarda.controller.app.RiepilogoAppController;
import it.tiriguarda.dto.EventoRiepilogo;
import it.tiriguarda.dto.RiepilogoBean;
import it.tiriguarda.exception.DataFuturaException;
import it.tiriguarda.exception.DatabaseNonRaggiungibileException;
import it.tiriguarda.exception.DatiIncompletiException;
import it.tiriguarda.exception.FileSystemNonRaggiungibileException;

public class RiepilogoCLIController {
	
	public void mostraRiepilogo(Scanner scanner) {
		ViewCLI.stampaTitolo("Riepilogo Attivita'");
		try {
			RiepilogoBean bean = new RiepilogoBean();
			
			bean.setData(ViewCLI.leggiData(scanner));			
			
			RiepilogoAppController controller = new RiepilogoAppController();
            List<EventoRiepilogo> eventi = controller.getReportRiepilogo(bean);
            
            stampaCronologiaEventi(eventi);
            
        } catch (DatabaseNonRaggiungibileException | FileSystemNonRaggiungibileException e) {
            ViewCLI.stampaErroreCriticoEChiudi(e.getMessage());
        } catch (DatiIncompletiException | DataFuturaException e) {
			ViewCLI.stampaErrore(e.getMessage());
		}
	}

	
	private void stampaCronologiaEventi(List<EventoRiepilogo> eventi) {
		ViewCLI.stampaMessaggio("--- REPORT ---");
		if (eventi == null || eventi.isEmpty()) {
			ViewCLI.stampaMessaggio("Nessun dato trovato per la data selezionata.");
		} else {
			for (EventoRiepilogo e : eventi) {
				ViewCLI.stampaMessaggio("- " + e.getData() + " : " + e.getDescrizione());
			}
		}
	}
}