package it.tiriguarda.controller.app;

import java.util.UUID;
import java.util.logging.Logger;

import it.tiriguarda.dao.DAOFactory;
import it.tiriguarda.dao.DAOFactoryProvider;
import it.tiriguarda.dao.RapportoDAO;
import it.tiriguarda.domain.LivelloRischio;
import it.tiriguarda.domain.Precauzioni;
import it.tiriguarda.domain.ProtocolloPrEP;
import it.tiriguarda.domain.Rapporto;
import it.tiriguarda.domain.Utente;
import it.tiriguarda.dto.RapportoBean;
import it.tiriguarda.logic.rischio.CalcoloRischio;
import it.tiriguarda.logic.rischio.PrEPDecorator;
import it.tiriguarda.logic.rischio.PreservativoDecorator;
import it.tiriguarda.logic.rischio.RischioBase;
import it.tiriguarda.service.SessionManager;

public class RegistraRapportoAppController {
	private static final Logger logger = Logger.getLogger(RegistraRapportoAppController.class.getName());
	
	public RapportoBean valutaRischio(RapportoBean bean) {

		Utente utenteCorrente = SessionManager.getInstance().getUtenteLoggato();
		LivelloRischio rischioCalcolato = analizzaRischio(bean, utenteCorrente);
		
		Rapporto rapportoTemporaneo = new Rapporto(utenteCorrente, "temp-id", bean.getData(), rischioCalcolato);
		
		bean.setRischio(rischioCalcolato);
		bean.setDataFinePeriodoFinestra(rapportoTemporaneo.getDataFinePeriodoFinestra());
		
		return bean;
	}
	
	private LivelloRischio analizzaRischio(RapportoBean bean, Utente utente) {
		CalcoloRischio calcolatore = new RischioBase(bean.getTipo());
		
		if (bean.getPrecauzioniUsate() == Precauzioni.PRESERVATIVO) {
			calcolatore = new PreservativoDecorator(calcolatore);
		}
		
		ProtocolloPrEP prep = utente.getProtocolloAttivo();
		if (prep != null && bean.getData().isAfter(prep.getDataInizio())) {
			calcolatore = new PrEPDecorator(calcolatore);
		}
		
		return calcolatore.calcola();
	}
	
	public void salvaRapportoDefinitivo(RapportoBean bean) {
		Utente utenteCorrente = SessionManager.getInstance().getUtenteLoggato();
		String idRapporto = UUID.randomUUID().toString();
		
		Rapporto nuovoRapporto = new Rapporto(utenteCorrente, idRapporto, bean.getData(), bean.getRischio());
		
		DAOFactory factory = DAOFactoryProvider.getDAOFactory();
		RapportoDAO dao = factory.createRapportoDAO();
		dao.salvaRapporto(nuovoRapporto);
		
		/*ProtocolloPrEP prep = utenteCorrente.getProtocolloAttivo();
	    if (prep != null) {
	        prep.aggiornaFinestraOnDemand(bean.getData());
	        ProtocolloPrEPDAO prepDao = factory.createProtocolloPrEPDAO();
	        prepDao.aggiornaProtocollo(prep);
	    }*/
		
		logger.info("Rapporto registrato definitivamente con successo.");
	}
	
}