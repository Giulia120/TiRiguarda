package it.tiriguarda.controller.app;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import it.tiriguarda.dao.DAOFactory;
import it.tiriguarda.dao.DAOFactoryProvider;
import it.tiriguarda.dao.RapportoDAO;
import it.tiriguarda.domain.LivelloRischio;
import it.tiriguarda.domain.Precauzioni;
import it.tiriguarda.domain.Rapporto;
import it.tiriguarda.domain.TipologiaPrEP;
import it.tiriguarda.domain.Utente;
import it.tiriguarda.dto.RapportoBean;
import it.tiriguarda.logic.observer.NuoviRapportiSubject;
import it.tiriguarda.logic.observer.NuovoRapportoObserver;
import it.tiriguarda.logic.rischio.CalcoloRischio;
import it.tiriguarda.logic.rischio.PrEPDecorator;
import it.tiriguarda.logic.rischio.PreservativoDecorator;
import it.tiriguarda.logic.rischio.RischioBase;
import it.tiriguarda.service.SessionManager;

public class RegistraRapportoAppController implements NuoviRapportiSubject {
	private static final Logger logger = Logger.getLogger(RegistraRapportoAppController.class.getName());
	
	private List<NuovoRapportoObserver> observers = new ArrayList<>();
	
	private Utente utenteRapportoSalvato; 

    @Override
    public void attach(NuovoRapportoObserver observer) { 
    	observers.add(observer); 
    	}

    @Override
    public void detach(NuovoRapportoObserver observer) { 
    	observers.remove(observer); 
    	}

    @Override
    public void notifyObservers() {
        for (NuovoRapportoObserver obs : observers) {
            obs.update();
        }
    }
    
    public Utente getUtenteRapportoSalvato() {
        return this.utenteRapportoSalvato;
    }
	
	public RapportoBean valutaRischio(RapportoBean bean) {

		Utente utenteCorrente = SessionManager.getInstance().getUtenteLoggato();
		if (utenteCorrente == null) {
	        throw new IllegalStateException("Errore critico: Nessun utente loggato in sessione.");
	    }
		LivelloRischio rischioCalcolato = analizzaRischio(bean, utenteCorrente);
		
		Rapporto rapportoTemporaneo = new Rapporto(utenteCorrente.getUsername(), "temp-id", bean.getData(), rischioCalcolato);
		
		bean.setRischio(rischioCalcolato);
		bean.setDataFinePeriodoFinestra(rapportoTemporaneo.getDataFinePeriodoFinestra());
		
		return bean;
	}
	
	private LivelloRischio analizzaRischio(RapportoBean bean, Utente utente) {
		CalcoloRischio calcolatore = new RischioBase(bean.getTipo());
		
		if (bean.getPrecauzioniUsate() == Precauzioni.PRESERVATIVO) {
			calcolatore = new PreservativoDecorator(calcolatore);
		}
		
		TipologiaPrEP prep = utente.getProtocolloAttivo();
		if (prep != null) {
			calcolatore = new PrEPDecorator(calcolatore);
		}
		
		return calcolatore.calcola();
	}
	
	public void salvaRapportoDefinitivo(RapportoBean bean) {
		Utente utenteCorrente = SessionManager.getInstance().getUtenteLoggato();
		String idRapporto = UUID.randomUUID().toString();
		
		Rapporto nuovoRapporto = new Rapporto(utenteCorrente.getUsername(), idRapporto, bean.getData(), bean.getRischio());
		
		DAOFactory factory = DAOFactoryProvider.getDAOFactory();
		RapportoDAO dao = factory.createRapportoDAO();
		dao.salvaRapporto(nuovoRapporto);
		
		this.utenteRapportoSalvato = utenteCorrente;
		notifyObservers();
		
		logger.info("Rapporto registrato definitivamente con successo.");
	}
	
}