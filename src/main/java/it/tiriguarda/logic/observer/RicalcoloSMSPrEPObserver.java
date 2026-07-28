package it.tiriguarda.logic.observer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import it.tiriguarda.controller.app.RegistraRapportoAppController;
import it.tiriguarda.dao.DAOFactory;
import it.tiriguarda.dao.DAOFactoryProvider;
import it.tiriguarda.dao.SmsDAO;
import it.tiriguarda.domain.ProtocolloPrEP;
import it.tiriguarda.domain.ProtocolloPrEPOnDemand;
import it.tiriguarda.domain.Rapporto;
import it.tiriguarda.domain.Sms;
import it.tiriguarda.domain.TipoSms;
import it.tiriguarda.domain.Utente;

public class RicalcoloSMSPrEPObserver implements NuovoRapportoObserver {
	private RegistraRapportoAppController subject; 

    public RicalcoloSmsPrEPObserver(RegistraRapportoAppController subject) {
        this.subject = subject;
        this.subject.attach(this);
    }
    
    @Override
    public void update() {
        Rapporto nuovoRapporto = subject.getUltimoRapportoSalvato();
        
        Utente utente = nuovoRapporto.getUtente();
        ProtocolloPrEP protocollo = utente.getProtocolloAttivo();

        if (protocollo instanceof ProtocolloPrEPOnDemand) {
        	
        } 	
    }
}
