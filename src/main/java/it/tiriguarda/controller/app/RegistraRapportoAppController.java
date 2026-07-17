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
	
	public void registraRapporto(RapportoBean bean) throws Exception{
		if(bean.getData() == null || bean.getTipo() == null || bean.getTipo().isEmpty()) {
			throw new Exception("Dati mancanti per registrare il rapporto"); //aggiungere Exception
		}
		Utente utenteCorrente = SessionManager.getInstance().getUtenteLoggato();
		
		String idRapporto = UUID.randomUUID().toString();
				
		LivelloRischio rischioCalcolato = analizzaRischio(bean, utenteCorrente);
		
		Rapporto nuovoRapporto = new Rapporto(utenteCorrente, idRapporto, bean.getData(), rischioCalcolato);
		
		RapportoDAOFactory factory = new RapportoDAOFactory();
		RapportoDAO dao = factory.createRapportoDAO();
		dao.salvaRapporto(nuovoRapporto);
		
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