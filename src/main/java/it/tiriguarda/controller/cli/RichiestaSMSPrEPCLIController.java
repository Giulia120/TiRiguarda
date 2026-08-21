package it.tiriguarda.controller.cli;

import java.util.Scanner;

import it.tiriguarda.controller.app.GestioneSmsAppController;
import it.tiriguarda.dto.ProtocolloPrEPBean;
import it.tiriguarda.exception.DatabaseNonRaggiungibileException;
import it.tiriguarda.exception.UtenteNonLoggatoException;

public class RichiestaSMSPrEPCLIController {
	public void avvia(ProtocolloPrEPBean bean, Scanner scanner) {
		boolean completato = false;
		while(!completato) {
			ViewCLI.stampaTitolo("Sms Prep");
			ViewCLI.stampaMessaggio(String.format("Gli sms veranno mandati al seguente orario: %s", bean.getOrario().toString()));
			String ricevereSMS = leggiSMS(scanner);
			if(ricevereSMS.equalsIgnoreCase("si")) {
				impostaSMS();
			} else if (ricevereSMS.equalsIgnoreCase("q")) {
				completato = true;
			}
			ViewCLI.stampaSuccesso(scanner);
			completato = true;
		}
	}
		
    private String leggiSMS(Scanner scanner) {
    	while(true) {
    		ViewCLI.stampaMessaggio("Vuoi ricevere SMS promemoria? (si/no): ");
            String risposta = scanner.nextLine().trim();
            
            if (risposta.equalsIgnoreCase("q") || 
                risposta.equalsIgnoreCase("si") || 
                risposta.equalsIgnoreCase("no")) {
                return risposta;
            }
            
            ViewCLI.stampaInvalido();
        }
    }
    
    private void impostaSMS() {
    	try {
    		GestioneSmsAppController controller = new GestioneSmsAppController();
    		controller.programmaPromemoriaPrEP();
    	}catch(DatabaseNonRaggiungibileException e) {
    		ViewCLI.stampaErroreCriticoEChiudi(e.getMessage());
    	}catch (UtenteNonLoggatoException e) {
    		ViewCLI.stampaErroreSistema(e.getMessage());
    		throw e;
    	}
    }

}
