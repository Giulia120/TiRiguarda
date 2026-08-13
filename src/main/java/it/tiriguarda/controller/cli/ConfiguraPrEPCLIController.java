package it.tiriguarda.controller.cli;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import it.tiriguarda.controller.app.ConfiguraPrEPAppController;
import it.tiriguarda.domain.TipologiaPrEP;
import it.tiriguarda.dto.ProtocolloPrEPBean;
import it.tiriguarda.exception.DatabaseNonRaggiungibileException;
import it.tiriguarda.exception.DatiIncompletiException;
import it.tiriguarda.exception.ProtocolloAttivoException;

public class ConfiguraPrEPCLIController {
	@SuppressWarnings("java:S106")
	public void avviaConfigurazione(TipologiaPrEP tipoPrEP, Scanner scanner) {
		boolean completato = false;
		while (!completato) {
			ViewCLI.stampaTitolo("CONFIGURAZIONE PrEP");
			System.out.println("Tipo PrEP selezionato: " + tipoPrEP);
			LocalDate dataInizio = ViewCLI.leggiData(scanner);
			if (dataInizio == null) return;
            
			LocalTime orario = leggiOrario(scanner);
			if (orario == null) return;

			String rispostaSMS = leggiSMS(scanner);
			
			if (rispostaSMS.equalsIgnoreCase("q")) {
			    return;
			}
			Boolean ricevereSMS = rispostaSMS.equalsIgnoreCase("si");
			try {
				ProtocolloPrEPBean bean = new ProtocolloPrEPBean();
				bean.setTipoPrEP(tipoPrEP);
				bean.setDataInizio(dataInizio);
				bean.setOrario(orario);
				bean.setRicevereSMS(ricevereSMS);
            
                ConfiguraPrEPAppController controller = new ConfiguraPrEPAppController();
                controller.configuraPrEP(bean);
                ViewCLI.stampaSuccesso(scanner);
                completato = true;
            } catch (ProtocolloAttivoException e) {
                ViewCLI.stampaErrore(e.getMessage());
                completato = true;
                return;
            } catch (DatiIncompletiException e) {
            	ViewCLI.stampaErrore(e.getMessage());
            } catch (DatabaseNonRaggiungibileException e) {
            	ViewCLI.stampaErroreCriticoEChiudi(e.getMessage());
            	throw e;
            }
    }
	return;
}
	@SuppressWarnings("java:S106")
    private LocalTime leggiOrario(Scanner scanner) {
        while(true) {
            System.out.print("Inserisci l'orario dei promemoria (HH:mm): ");
            String input = scanner.nextLine();
            if(input.equalsIgnoreCase("q")) {
                return null;
            }
            try{
                return LocalTime.parse(input);
            } catch(DateTimeParseException e) {
                System.out.println("[ERRORE] Formato orario non valido.");
            }
        }
    }
	@SuppressWarnings("java:S106")
    private String leggiSMS(Scanner scanner) {
    	while(true) {
            System.out.print("Vuoi ricevere SMS promemoria? (si/no): ");
            String risposta = scanner.nextLine().trim();
            
            if (risposta.equalsIgnoreCase("q") || 
                risposta.equalsIgnoreCase("si") || 
                risposta.equalsIgnoreCase("no")) {
                return risposta;
            }
            
            ViewCLI.stampaInvalido();
        }
    }
}

