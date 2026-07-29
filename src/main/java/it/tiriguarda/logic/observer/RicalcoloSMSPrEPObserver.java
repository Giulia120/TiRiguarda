package it.tiriguarda.logic.observer;

import java.time.LocalDateTime;
import java.util.List;

import it.tiriguarda.controller.app.GestioneSmsAppController;
import it.tiriguarda.controller.app.RegistraRapportoAppController;
import it.tiriguarda.dao.DAOFactoryProvider;
import it.tiriguarda.dao.ProtocolloPrEPDAO;
import it.tiriguarda.domain.ProtocolloPrEP;
import it.tiriguarda.domain.ProtocolloPrEPOnDemand;
import it.tiriguarda.domain.Rapporto;
import it.tiriguarda.domain.StatoSms;
import it.tiriguarda.domain.TipoSms;
import it.tiriguarda.domain.Utente;
import it.tiriguarda.dto.SmsBean;

public class RicalcoloSMSPrEPObserver implements NuovoRapportoObserver {
	private RegistraRapportoAppController subject; 

    public RicalcoloSMSPrEPObserver(RegistraRapportoAppController subject) {
        this.subject = subject;
        this.subject.attach(this);
    }
    
    @Override
    public void update() {
        Utente utente = subject.getUtenteRapportoSalvato();
        Rapporto nuovoRapporto = subject.getUltimoRapportoSalvato();
        
        ProtocolloPrEPDAO dao = DAOFactoryProvider.getDAOFactory().createProtocolloPrEPDAO();
        ProtocolloPrEP protocollo = dao.trovaProtocolloAttivo(utente.getUsername());

        if (protocollo instanceof ProtocolloPrEPOnDemand) {
        	List<LocalDateTime> date = protocollo.calcolaGiorniPromemoria(nuovoRapporto.getData(), protocollo.getOra(), utente.getSessoBiologico());
        	GestioneSmsAppController smsController = new GestioneSmsAppController();
            for (LocalDateTime data : date) {
                SmsBean bean = new SmsBean();
                bean.setTesto("Ricordati di assumere la PrEP");
                bean.setDataSpedizione(data);
                bean.setStato(StatoSms.DA_INVIARE);
                bean.setTipo(TipoSms.PREP_OD);
                smsController.programmaSms(bean);
            }
        } 	
    }
}
