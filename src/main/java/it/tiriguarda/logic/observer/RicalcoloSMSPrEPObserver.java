package it.tiriguarda.logic.observer;

import it.tiriguarda.controller.app.RegistraRapportoAppController;
import it.tiriguarda.domain.ProtocolloPrEPOnDemand;
import it.tiriguarda.domain.TipologiaPrEP;
import it.tiriguarda.domain.Utente;

public class RicalcoloSMSPrEPObserver implements NuovoRapportoObserver {
	private RegistraRapportoAppController subject; 

    public RicalcoloSmsPrEPObserver(RegistraRapportoAppController subject) {
        this.subject = subject;
        this.subject.attach(this);
    }
    
    @Override
    public void update() {
        Utente utente = subject.getUtenteRapportoSalvato();
        
        TipologiaPrEP protocollo = utente.getProtocolloAttivo();

        if (protocollo == TipologiaPrEP.ON_DEMAND) {
        	
        }
    }
}
