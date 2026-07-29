package it.tiriguarda.controller.cli;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import it.tiriguarda.controller.app.ConfiguraPrEPAppController;
import it.tiriguarda.domain.TipologiaPrEP;
import it.tiriguarda.dto.ProtocolloPrEPBean;
import it.tiriguarda.exception.DatiIncompletiException;
import it.tiriguarda.exception.ProtocolloAttivoException;

public class ConfiguraPrEPCLIController {
	public void avviaConfigurazione(TipologiaPrEP tipoPrEP, Scanner scanner) {
        boolean completato = false;
        while (!completato) {
            ViewCLI.stampaTitolo("CONFIGURAZIONE PrEP");
            System.out.println("Tipo PrEP selezionato: " + tipoPrEP);
            LocalDate dataInizio = ViewCLI.leggiData(scanner);
            if (dataInizio == null) return;
            
            LocalTime orario = leggiOrario(scanner);
            if (orario == null) return;

            Boolean ricevereSMS = leggiSMS(scanner);
            if (ricevereSMS == null) return;

            ProtocolloPrEPBean bean = new ProtocolloPrEPBean();
            bean.setTipoPrEP(tipoPrEP);
            bean.setDataInizio(dataInizio);
            bean.setOrario(orario);
            bean.setRicevereSMS(ricevereSMS);
            try {
                ConfiguraPrEPAppController controller = new ConfiguraPrEPAppController();
                controller.configuraPrEP(bean);
                System.out.println("\nProtocollo PrEP configurato con successo!");
                completato = true;
            } catch (ProtocolloAttivoException e) {
                System.out.println("\n[ERRORE]: " + e.getMessage());
                completato = true;
            } catch (DatiIncompletiException e) {
                System.out.println("\n[ERRORE DATI]: " + e.getMessage());
                System.out.println("Riprovare...");
            }
        }
    }

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
                System.out.println("Formato orario non valido.");
            }
        }
    }
    private Boolean leggiSMS(Scanner scanner) {
        while(true) {
            System.out.print("Vuoi ricevere SMS promemoria? (si/no): ");
            String risposta = scanner.nextLine();
            if(risposta.equalsIgnoreCase("q")) {
                return null;
            }
            if(risposta.equalsIgnoreCase("si")) {
                return true;
            }
            else if(risposta.equalsIgnoreCase("no")) {
                return false;
            }
            System.out.println("Risposta non valida.");
        }
    }
}

