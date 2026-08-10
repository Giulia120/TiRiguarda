package it.tiriguarda.controller.app;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import it.tiriguarda.dao.DAOFactory;
import it.tiriguarda.dao.DAOFactoryProvider;
import it.tiriguarda.dao.ProtocolloPrEPDAO;
import it.tiriguarda.dao.RapportoDAO;
import it.tiriguarda.dao.TestDAO;
import it.tiriguarda.domain.ProtocolloPrEP;
import it.tiriguarda.domain.Rapporto;
import it.tiriguarda.domain.Test;
import it.tiriguarda.domain.Utente;
import it.tiriguarda.dto.EventoRiepilogo;
import it.tiriguarda.dto.RiepilogoBean;
import it.tiriguarda.service.SessionManager;

public class RiepilogoAppController {
	
	public void effettuaRiepilogo(RiepilogoBean bean){
		Utente utenteCorrente = SessionManager.getInstance().getUtenteLoggato();
		if(utenteCorrente == null){
	        throw new IllegalStateException("Errore critico: Nessun utente loggato in sessione.");
	    }
		
		DAOFactory factory = DAOFactoryProvider.getDAOFactory();
		ProtocolloPrEPDAO daoPrEP = factory.createProtocolloPrEPDAO();
		List<ProtocolloPrEP> prep = daoPrEP.riepilogoPrEP(utenteCorrente.getUsername(), bean.getData());
		
		RapportoDAO daoRapporto = factory.createRapportoDAO();
		List<Rapporto> rapporti = daoRapporto.riepilogoRapporti(utenteCorrente.getUsername(), bean.getData());
		
		TestDAO daoTest = factory.createTestDAO();
		List<Test> test = daoTest.riepilogoTest(utenteCorrente.getUsername(), bean.getData());
		
		bean.setPrep(prep);
		bean.setRapporti(rapporti);
		bean.setTest(test);
	}
	
	public List<EventoRiepilogo> getReportRiepilogo(RiepilogoBean bean) {
        effettuaRiepilogo(bean);
        
        List<EventoRiepilogo> eventi = new ArrayList<>();
        
        for (Rapporto r : bean.getRapporti()) {
            eventi.add(new EventoRiepilogo(r.getData(), "Rapporto con rischio " + r.getRischio()));
        }
        
        for (Test t : bean.getTest()) {
            eventi.add(new EventoRiepilogo(t.getData(), "Test " + t.getTipo()));
        }
        
        for (ProtocolloPrEP p : bean.getPrep()) {
            eventi.add(new EventoRiepilogo(p.getDataInizio(), "Inizio PrEP " + p.getTipoPrEP()));
            if (p.getDataFine() != null) {
                eventi.add(new EventoRiepilogo(p.getDataFine(), "Fine PrEP " + p.getTipoPrEP()));
            }
        }
        
        eventi.sort(Comparator.comparing(EventoRiepilogo::getData));
        
        return eventi;
    }
}
