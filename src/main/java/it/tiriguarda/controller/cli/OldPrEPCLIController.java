package it.tiriguarda.controller.cli;

import java.time.LocalDate;
import java.util.Scanner;

import it.tiriguarda.controller.app.PrEPAppController;
import it.tiriguarda.domain.TipologiaPrEP;
import it.tiriguarda.dto.OldProtocolloPrEPBean;
import it.tiriguarda.exception.DataFuturaException;
import it.tiriguarda.exception.DatabaseNonRaggiungibileException;
import it.tiriguarda.exception.DatiIncompletiException;

public class OldPrEPCLIController {
	
	public void avviaConfigurazione(Scanner scanner) {
		boolean completato = false;
		while (!completato) {
			ViewCLI.stampaTitolo("REGISTRA VECCHIA PrEP");
			
			TipologiaPrEP tipoPrEP = leggiTipoPrEP(scanner);
			if (tipoPrEP == null) return;
			
			ViewCLI.stampaMessaggio("Data di inizio:");
			LocalDate dataInizio = ViewCLI.leggiData(scanner);
			if (dataInizio == null) return;
            
			ViewCLI.stampaMessaggio("Data di fine:");
			LocalDate dataFine = ViewCLI.leggiData(scanner);
			if (dataFine == null) return;

			try {
				OldProtocolloPrEPBean bean = new OldProtocolloPrEPBean();
				bean.setTipoPrEP(tipoPrEP);
				bean.setDataInizio(dataInizio);
				bean.setDataFine(dataFine);
				
				PrEPAppController controller = new PrEPAppController();
				controller.configuraVecchiaPrEP(bean);
				ViewCLI.stampaSuccesso(scanner);
				completato = true;
			} catch (DatiIncompletiException | DataFuturaException e) {
				ViewCLI.stampaErrore(e.getMessage());
			} catch (DatabaseNonRaggiungibileException e) {
				ViewCLI.stampaErroreCriticoEChiudi(e.getMessage());
				throw e;
			}
		}
	}
	
	
	private TipologiaPrEP leggiTipoPrEP(Scanner scanner) {
		while(true) {
			ViewCLI.stampaMessaggio("Che tipo di PrEP hai usato?\n");
			ViewCLI.mostraMenu(
					"Daily",
					"On Demand");
			String risposta = scanner.nextLine().trim();
			
			if (risposta.equalsIgnoreCase("q")) {
				return null;
			}
			if (risposta.equals("1")) {
				return TipologiaPrEP.DAILY;
			}
			if (risposta.equals("2")) {
				return TipologiaPrEP.ON_DEMAND;
			}
			
			ViewCLI.stampaInvalido();
		}
	}
}