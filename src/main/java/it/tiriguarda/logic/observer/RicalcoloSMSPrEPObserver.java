package it.tiriguarda.logic.observer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


import it.tiriguarda.controller.app.RegistraRapportoAppController;
import it.tiriguarda.domain.ProtocolloPrEPOnDemand;
import it.tiriguarda.domain.Rapporto;
import it.tiriguarda.domain.Sms;
import it.tiriguarda.domain.TipoSms;
import it.tiriguarda.domain.TipologiaPrEP;
import it.tiriguarda.domain.Utente;

public class RicalcoloSMSPrEPObserver implements NuovoRapportoObserver {
	private RegistraRapportoAppController subject; 

    public RicalcoloSMSPrEPObserver(RegistraRapportoAppController subject) {
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
