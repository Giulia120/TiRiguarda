package it.tiriguarda.controller.cli;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
	        System.out.println("       REGISTRAZIONE NUOVO RAPPORTO     ");
	        System.out.println("========================================");
	        System.out.println("(Digita 'q' in qualsiasi momento per annullare e tornare al menu)\n");

	        java.sql.Date dataRapporto = leggiData(scanner);
	        if (dataRapporto == null) return; 

	        List<TipoRapporto> tipi = leggiTipiRapporto(scanner);
	        if (tipi == null) return;

	        Precauzioni precauzioni = leggiPrecauzioni(scanner);
	        if (precauzioni == null) return;

	        RapportoBean bean = new RapportoBean();
	        bean.setData(dataRapporto);
	        bean.setTipo(tipi);
	        bean.setPrecauzioniUsate(precauzioni);

	        System.out.println("\nSto calcolando il rischio...");

	        try {
	            RegistraRapportoAppController appController = new RegistraRapportoAppController();
	            RapportoBean beanAggiornato = appController.registraRapporto(bean);

	            if (beanAggiornato.getRischio() != LivelloRischio.NULLO) {
	                RichiestaSMSRapportoCLIController smsController = new RichiestaSMSRapportoCLIController();
	                smsController.avvia(beanAggiornato, scanner); 
	            } else {
	                mostraSchermataSuccesso();
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

	private void mostraSchermataSuccesso() {
		System.out.println("\n****************************************");
		System.out.println("*  Rapporto registrato con successo!   *");
		System.out.println("****************************************");
	}


	private java.sql.Date leggiData(Scanner scanner) {
	    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	    format.setLenient(false);
	    
	    while (true) {
	        System.out.print("Inserisci la data (gg/mm/aaaa): ");
	        String input = scanner.nextLine();
	        
	        if (input.equalsIgnoreCase("q")) {
	            return null;
	        }
	        
	        try {
	            java.util.Date dataUtil = format.parse(input);
	            return new java.sql.Date(dataUtil.getTime());
	        } catch (ParseException e) {
	            System.out.println("Formato non valido! Usa gg/mm/aaaa.");
	        }
	    }
	}

	private List<TipoRapporto> leggiTipiRapporto(Scanner scanner) {
	    List<TipoRapporto> tipi = new ArrayList<>();
	    
	    while (tipi.isEmpty()) {
	        System.out.print("È stato un rapporto penetrativo? (si/no): ");
	        String risp1 = scanner.nextLine();
	        if (risp1.equalsIgnoreCase("q")) return java.util.Collections.emptyList();
	        if (risp1.equalsIgnoreCase("si")) tipi.add(TipoRapporto.PENETRATIVO);
	        
	        System.out.print("È stato un rapporto orale? (si/no): ");
	        String risp2 = scanner.nextLine();
	        if (risp2.equalsIgnoreCase("q")) return java.util.Collections.emptyList();
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
	        System.out.print("Scegli un'opzione (1/2/3) oppure 'q' per annullare: ");
	        
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