package it.tiriguarda.controller.cli;

import java.time.LocalDate;
import java.util.Scanner;

import it.tiriguarda.controller.app.ConfiguraPrEPAppController;
import it.tiriguarda.domain.TipologiaPrEP;
import it.tiriguarda.dto.OldProtocolloPrEPBean;
import it.tiriguarda.exception.DataFuturaException;
import it.tiriguarda.exception.DatabaseNonRaggiungibileException;
import it.tiriguarda.exception.DatiIncompletiException;

public class OldPrEPCLIController {

	@SuppressWarnings("java:S106")
	public void avviaConfigurazione(Scanner scanner) {
		boolean completato = false;
		while (!completato) {
			ViewCLI.stampaTitolo("REGISTRA VECCHIA PrEP");
			
			TipologiaPrEP tipoPrEP = leggiTipoPrEP(scanner);
			if (tipoPrEP == null) return;
			
			System.out.println("Data di inizio:");
			LocalDate dataInizio = ViewCLI.leggiData(scanner);
			if (dataInizio == null) return;
            
			System.out.println("Data di fine:");
			LocalDate dataFine = ViewCLI.leggiData(scanner);
			if (dataFine == null) return;

			try {
				OldProtocolloPrEPBean bean = new OldProtocolloPrEPBean();
				bean.setTipoPrEP(tipoPrEP);
				bean.setDataInizio(dataInizio);
				bean.setDataFine(dataFine);
            
				ConfiguraPrEPAppController controller = new ConfiguraPrEPAppController();
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
	
	@SuppressWarnings("java:S106")
	private TipologiaPrEP leggiTipoPrEP(Scanner scanner) {
		while(true) {
			System.out.print("Che tipo di PrEP hai usato? (1 - Daily, 2 - On Demand): ");
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