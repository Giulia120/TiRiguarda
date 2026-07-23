package it.tiriguarda.controller.app;

import java.util.UUID;
import java.util.logging.Logger;

import it.tiriguarda.dao.DAOFactory;
import it.tiriguarda.dao.DAOFactoryProvider;
import it.tiriguarda.dao.RapportoDAO;
import it.tiriguarda.domain.LivelloRischio;
import it.tiriguarda.domain.Precauzioni;
import it.tiriguarda.domain.Rapporto;
import it.tiriguarda.domain.Utente;
import it.tiriguarda.dto.RapportoBean;
import it.tiriguarda.exception.DataFuturaException;
import it.tiriguarda.exception.DatiIncompletiException;
import it.tiriguarda.logic.rischio.CalcoloRischio;
import it.tiriguarda.logic.rischio.PreservativoDecorator;
import it.tiriguarda.logic.rischio.RischioBase;
import it.tiriguarda.service.SessionManager;

public class RegistraRapportoAppController {
	private static RegistraRapportoAppController instance;
	private static final Logger logger = Logger.getLogger(RegistraRapportoAppController.class.getName());
	
	private RegistraRapportoAppController() {
	}
	
	public static RegistraRapportoAppController getInstance() {
		if (instance == null) {
			instance = new RegistraRapportoAppController();
		}
		return instance;
	}
	
	public RapportoBean registraRapporto(RapportoBean bean) throws DatiIncompletiException, DataFuturaException{
		if(bean.getData() == null || bean.getTipo() == null || bean.getTipo().isEmpty()) {
			throw new DatiIncompletiException();
		}
		
        if (bean.getData().after(new java.util.Date())) {
            throw new DataFuturaException();
        }
        
		Utente utenteCorrente = SessionManager.getInstance().getUtenteLoggato();
		
		String idRapporto = UUID.randomUUID().toString();
				
		LivelloRischio rischioCalcolato = analizzaRischio(bean, utenteCorrente);
		
		Rapporto nuovoRapporto = new Rapporto(utenteCorrente, idRapporto, bean.getData(), rischioCalcolato);
		
		DAOFactory factory = DAOFactoryProvider.getDAOFactory();
		RapportoDAO dao = factory.createRapportoDAO();
		dao.salvaRapporto(nuovoRapporto);
		logger.info("Rapporto registrato con successo.");
		bean.setRischio(rischioCalcolato);
        bean.setDataFinePeriodoFinestra(nuovoRapporto.getDataFinePeriodoFinestra());
        
		return bean;
	}
	
	private LivelloRischio analizzaRischio(RapportoBean bean, Utente utente) {
		CalcoloRischio calcolatore = new RischioBase(bean.getTipo());
		
		if (bean.getPrecauzioniUsate() == Precauzioni.PRESERVATIVO) {
			calcolatore = new PreservativoDecorator(calcolatore);
		}
		
		/*ProtocolloPrEP prep = utente.getProtocolloAttivo();
		if (prep != null && prep.getStatoPrEP() == true) {
			calcolatore = new PrEPDecorator(calcolatore);
		}*/
		
		return calcolatore.calcola();
	}
	
}