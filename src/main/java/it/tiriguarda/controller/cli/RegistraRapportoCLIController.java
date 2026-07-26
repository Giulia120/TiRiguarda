package it.tiriguarda.controller.cli;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import it.tiriguarda.controller.app.RegistraRapportoAppController;
import it.tiriguarda.domain.LivelloRischio;
import it.tiriguarda.domain.Precauzioni;
import it.tiriguarda.domain.TipoRapporto;
import it.tiriguarda.dto.RapportoBean;
import it.tiriguarda.exception.DataFuturaException;
import it.tiriguarda.exception.DatiIncompletiException;

public class RegistraRapportoCLIController {

	public void avviaRegistrazioneRapporto(Scanner scanner) {
		boolean completato = false;
		
		while (!completato) {
			System.out.println("\n========================================");
			System.out.println("        REGISTRAZIONE NUOVO RAPPORTO      ");
			System.out.println("========================================");
			System.out.println("(Digita 'q' in qualsiasi momento per annullare e tornare al menu)\n");

			LocalDate dataRapporto = leggiData(scanner);
			if (dataRapporto == null) return; 

			List<TipoRapporto> tipi = leggiTipiRapporto(scanner);
			if (tipi == null) return;

			Precauzioni precauzioni = leggiPrecauzioni(scanner);
			if (precauzioni == null) return;

			System.out.println("\nSto elaborando i dati e calcolando il rischio...");

			try {
				RapportoBean bean = new RapportoBean();
				bean.setData(dataRapporto);
				bean.setTipo(tipi);
				bean.setPrecauzioniUsate(precauzioni);

				RegistraRapportoAppController appController = new RegistraRapportoAppController();
				RapportoBean beanAggiornato = appController.valutaRischio(bean);

				if (beanAggiornato.getRischio() != LivelloRischio.NULLO) {
					RichiestaSMSRapportoCLIController smsController = new RichiestaSMSRapportoCLIController();
					smsController.avvia(beanAggiornato, scanner); 
				} else {
					appController.salvaRapportoDefinitivo(beanAggiornato);
					System.out.println("\nRapporto registrato con successo! Torno al menu principale...");
				}
				
				completato = true;

			} catch (DatiIncompletiException | DataFuturaException e) {
				System.out.println("\n[ERRORE DI VALIDAZIONE]: " + e.getMessage());
				System.out.println("Premi INVIO per correggere i dati...");
				scanner.nextLine(); 
				
			} catch (Exception e) {
				System.out.println("\n[ERRORE DI SISTEMA]: Impossibile salvare il rapporto. " + e.getMessage());
				completato = true; 
			}
		}
	}

	private LocalDate leggiData(Scanner scanner) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		while (true) {
			System.out.print("Inserisci la data (gg/mm/aaaa): ");
			String input = scanner.nextLine();
			
			if (input.equalsIgnoreCase("q")) {
				return null;
			}
			
			try {
				return LocalDate.parse(input, formatter);
			} catch (DateTimeParseException e) {
				System.out.println("Formato o data non valido! Usa gg/mm/aaaa.");
			}
		}
	}

	private List<TipoRapporto> leggiTipiRapporto(Scanner scanner) {
		List<TipoRapporto> tipi = new ArrayList<>();
		
		while (tipi.isEmpty()) {
			System.out.print("È stato un rapporto penetrativo? (si/no): ");
			String risp1 = scanner.nextLine();
			if (risp1.equalsIgnoreCase("q")) return null;
			if (risp1.equalsIgnoreCase("si")) tipi.add(TipoRapporto.PENETRATIVO);
			
			System.out.print("È stato un rapporto orale? (si/no): ");
			String risp2 = scanner.nextLine();
			if (risp2.equalsIgnoreCase("q")) return null;
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
				default: System.out.println("Opzione non valida, riprova!");
			}
		}
	}
}