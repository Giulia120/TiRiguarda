package it.tiriguarda.controller.cli;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import it.tiriguarda.controller.app.PrEPAppController;
import it.tiriguarda.domain.TipologiaPrEP;
import it.tiriguarda.dto.ProtocolloPrEPBean;
import it.tiriguarda.exception.DatabaseNonRaggiungibileException;
import it.tiriguarda.exception.DatiIncompletiException;
import it.tiriguarda.exception.ProtocolloAttivoException;

public class ConfiguraPrEPCLIController {
	private PrEPAppController controller = new PrEPAppController();
	
	public void avviaConfigurazione(TipologiaPrEP tipoPrEP, Scanner scanner) {
		boolean completato = false;
		while (!completato) {
			ViewCLI.stampaTitolo("CONFIGURAZIONE PrEP");
			ViewCLI.stampaMessaggio("Tipo PrEP selezionato: " + tipoPrEP);
			if (tipoPrEP == TipologiaPrEP.ON_DEMAND) {
				ViewCLI.stampaMessaggio("Prendere 2 pasticche da 2 a 24 ore prima del rapporto");
			}
			LocalDate dataInizio = ViewCLI.leggiData(scanner);
			if (dataInizio == null) return;
            
			LocalTime orario = leggiOrario(scanner);
			if (orario == null) return;

			
			try {
				ProtocolloPrEPBean bean = new ProtocolloPrEPBean();
				bean.setTipoPrEP(tipoPrEP);
				bean.setDataInizio(dataInizio);
				bean.setOrario(orario);
            
                controller.configuraPrEP(bean);
                RichiestaSMSPrEPCLIController smsController = new RichiestaSMSPrEPCLIController();
                smsController.avvia(bean, scanner);
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
}
	
    private LocalTime leggiOrario(Scanner scanner) {
        while(true) {
        	ViewCLI.stampaMessaggio("Inserisci l'orario dei promemoria (HH:mm): ");
            String input = scanner.nextLine();
            if(input.equalsIgnoreCase("q")) {
                return null;
            }
            try{
                return LocalTime.parse(input);
            } catch(DateTimeParseException e) {
            	ViewCLI.stampaMessaggio("[ERRORE] Formato orario non valido.");
            }
        }
    }
	
}

