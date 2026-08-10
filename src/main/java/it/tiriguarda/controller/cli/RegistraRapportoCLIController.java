package it.tiriguarda.controller.cli;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import it.tiriguarda.controller.app.RegistraRapportoAppController;
import it.tiriguarda.domain.LivelloRischio;
import it.tiriguarda.domain.Precauzioni;
import it.tiriguarda.domain.TipoRapporto;
import it.tiriguarda.dto.RapportoBean;
import it.tiriguarda.exception.DataFuturaException;
import it.tiriguarda.exception.DatabaseNonRaggiungibileException;
import it.tiriguarda.exception.DatiIncompletiException;
import it.tiriguarda.logic.observer.RicalcoloSMSPrEPObserver;

public class RegistraRapportoCLIController {

	public void avviaRegistrazioneRapporto(Scanner scanner) {
		boolean completato = false;
		
		while (!completato) {
			ViewCLI.stampaTitolo("Registra Rapporto");
			
			LocalDate dataRapporto = ViewCLI.leggiData(scanner);
			if (dataRapporto == null) return; 

			List<TipoRapporto> tipi = leggiTipiRapporto(scanner);
			if (tipi.isEmpty()) return;

			Precauzioni precauzioni = leggiPrecauzioni(scanner);
			if (precauzioni == null) return;
			
			completato = elaboraESalvaRapporto(dataRapporto, tipi, precauzioni, scanner);
		}
	}
	@SuppressWarnings("java:S106")
	private boolean elaboraESalvaRapporto(LocalDate dataRapporto, List<TipoRapporto> tipi, Precauzioni precauzioni, Scanner scanner) {
		try {
			RapportoBean bean = new RapportoBean();
			bean.setData(dataRapporto);
			bean.setTipo(tipi);
			bean.setPrecauzioniUsate(precauzioni);

			RegistraRapportoAppController appController = new RegistraRapportoAppController();
			new RicalcoloSMSPrEPObserver(appController);
			RapportoBean beanAggiornato = appController.valutaRischio(bean);

			if (beanAggiornato.getRischio() != LivelloRischio.NULLO) {
				RichiestaSMSRapportoCLIController smsController = new RichiestaSMSRapportoCLIController();
				smsController.avvia(beanAggiornato, scanner); 
			} else {
				appController.salvaRapportoDefinitivo(beanAggiornato);
			}
			ViewCLI.stampaSuccesso(scanner);
			return true;

		} catch (DatiIncompletiException | DataFuturaException e) {
			ViewCLI.stampaErrore(e.getMessage());
			System.out.println("Premi INVIO per correggere i dati...");
			scanner.nextLine();
			return false;
			
		} catch (DatabaseNonRaggiungibileException e) {
			ViewCLI.stampaErroreCriticoEChiudi(e.getMessage());
			return true;
			
		} catch (IllegalStateException e) {
			ViewCLI.stampaErroreSistema(e.getMessage());
			throw e;
		}
	}
	@SuppressWarnings("java:S106")
	private List<TipoRapporto> leggiTipiRapporto(Scanner scanner) {
		List<TipoRapporto> tipi = new ArrayList<>();
		
		while (tipi.isEmpty()) {
			System.out.print("È stato un rapporto penetrativo? (si/no): ");
			String risp1 = scanner.nextLine();
			if (risp1.equalsIgnoreCase("q")) return Collections.emptyList();
			if (risp1.equalsIgnoreCase("si")) tipi.add(TipoRapporto.PENETRATIVO);
			
			System.out.print("È stato un rapporto orale? (si/no): ");
			String risp2 = scanner.nextLine();
			if (risp2.equalsIgnoreCase("q")) return Collections.emptyList();
			if (risp2.equalsIgnoreCase("si")) tipi.add(TipoRapporto.ORALE);
			
			if (tipi.isEmpty()) {
				System.out.println("Devi selezionare almeno un tipo di rapporto! Riprova.");
			}
		}
		return tipi;
	}

	private Precauzioni leggiPrecauzioni(Scanner scanner) {
		while (true) {
			System.out.println("Che precauzioni hai usato?");
			System.out.println("1 - Preservativo");
			System.out.println("2 - Coito Interrotto");
			System.out.println("3 - Nessuna (Nulla)");
			System.out.print("Scegli un'opzione: ");
			
			String input = scanner.nextLine();
			if (input.equalsIgnoreCase("q")) return null;
			
			switch (input) {
				case "1": return Precauzioni.PRESERVATIVO;
				case "2": return Precauzioni.COITO_INTERROTTO;
				case "3": return Precauzioni.NULLA;
				default: ViewCLI.stampaInvalido();
			}
		}
	}
}