package it.tiriguarda.logic.observer;

<<<<<<< HEAD
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

=======
>>>>>>> d6b650b72e23652ad50a0f9e0a85238ae8bc8fb6
import it.tiriguarda.controller.app.RegistraRapportoAppController;
import it.tiriguarda.domain.ProtocolloPrEPOnDemand;
<<<<<<< HEAD
import it.tiriguarda.domain.Rapporto;
import it.tiriguarda.domain.Sms;
import it.tiriguarda.domain.TipoSms;
=======
import it.tiriguarda.domain.TipologiaPrEP;
>>>>>>> d6b650b72e23652ad50a0f9e0a85238ae8bc8fb6
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
