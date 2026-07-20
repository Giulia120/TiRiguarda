package it.tiriguarda.controller.app;

import java.util.UUID;

import it.tiriguarda.dao.RapportoDAO;
import it.tiriguarda.dao.RapportoDAOFactory;
import it.tiriguarda.domain.LivelloRischio;
import it.tiriguarda.domain.Precauzioni;
import it.tiriguarda.domain.ProtocolloPrEP;
import it.tiriguarda.domain.Rapporto;
import it.tiriguarda.domain.Utente;
import it.tiriguarda.dto.RapportoBean;
import it.tiriguarda.exception.DataFuturaException;
import it.tiriguarda.exception.DatiRapportoIncompletiException;
import it.tiriguarda.logic.rischio.CalcoloRischio;
import it.tiriguarda.logic.rischio.PrEPDecorator;
import it.tiriguarda.logic.rischio.PreservativoDecorator;
import it.tiriguarda.logic.rischio.RischioBase;
import it.tiriguarda.service.SessionManager;

public class RegistraRapportoAppController {
	private static RegistraRapportoAppController instance;
	
	private RegistraRapportoAppController() {
	}
	
	public static RegistraRapportoAppController getInstance() {
		if (instance == null) {
			instance = new RegistraRapportoAppController();
		}
		return instance;
	}
	
	public RapportoBean registraRapporto(RapportoBean bean) throws DatiRapportoIncompletiException, DataFuturaException{
		if(bean.getData() == null || bean.getTipo() == null || bean.getTipo().isEmpty()) {
			throw new DatiRapportoIncompletiException("Dati mancanti per registrare il rapporto");
		}
		
        if (bean.getData().after(new java.util.Date())) {
            throw new DataFuturaException("Hai inserito una data futura");
        }
        
		Utente utenteCorrente = SessionManager.getInstance().getUtenteLoggato();
		
		String idRapporto = UUID.randomUUID().toString();
				
		LivelloRischio rischioCalcolato = analizzaRischio(bean, utenteCorrente);
		
		Rapporto nuovoRapporto = new Rapporto(utenteCorrente, idRapporto, bean.getData(), rischioCalcolato);
		
		RapportoDAOFactory factory = new RapportoDAOFactory();
		RapportoDAO dao = factory.createRapportoDAO();
		dao.salvaRapporto(nuovoRapporto);
		
		bean.setRischio(rischioCalcolato);
        bean.setDataFinePeriodoFinestra(nuovoRapporto.getDataFinePeriodoFinestra());
        
		return bean;
	}
	
	private LivelloRischio analizzaRischio(RapportoBean bean, Utente utente) {
		CalcoloRischio calcolatore = new RischioBase(bean.getTipo());
		
		if (bean.getPrecauzioniUsate() == Precauzioni.PRESERVATIVO) {
			calcolatore = new PreservativoDecorator(calcolatore);
		}
		
		ProtocolloPrEP prep = utente.getProtocolloAttivo();
		if (prep != null && prep.getStatoPrEP() == true) {
			calcolatore = new PrEPDecorator(calcolatore);
		}
		
		return calcolatore.calcola();
	}
	
}