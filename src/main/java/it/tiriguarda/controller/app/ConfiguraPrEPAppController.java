package it.tiriguarda.controller.app;

import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;

import it.tiriguarda.dao.DAOFactory;
import it.tiriguarda.dao.DAOFactoryProvider;
import it.tiriguarda.dao.ProtocolloPrEPDAO;
import it.tiriguarda.domain.ProtocolloPrEP;
import it.tiriguarda.domain.ProtocolloPrEPDaily;
import it.tiriguarda.domain.ProtocolloPrEPOnDemand;
import it.tiriguarda.domain.TipologiaPrEP;
import it.tiriguarda.domain.Utente;
import it.tiriguarda.dto.ProtocolloPrEPBean;
import it.tiriguarda.exception.DatiIncompletiException;
import it.tiriguarda.exception.ProtocolloAttivoException;
import it.tiriguarda.service.SessionManager;

public class ConfiguraPrEPAppController {
	private static final Logger logger = Logger.getLogger(ConfiguraPrEPAppController.class.getName());
	
	public void configuraPrEP(ProtocolloPrEPBean bean) throws ProtocolloAttivoException, DatiIncompletiException {
		if(bean.getDataInizio() == null || bean.getOrario() == null) {
			throw new DatiIncompletiException();
		}
		
		boolean vecchio = false;
		Utente utente = SessionManager.getInstance().getUtenteLoggato();
		if (utente == null) {
	        throw new IllegalStateException("Errore critico: Nessun utente loggato in sessione.");
	    }
		
		ProtocolloPrEP protocollo = utente.getProtocolloAttivo();
		
		if(protocollo != null && protocollo.getStatoPrEP()) {
			throw new ProtocolloAttivoException();
		}
		
		if(bean.getTipoPrEP() == TipologiaPrEP.DAILY) {
		    protocollo = new ProtocolloPrEPDaily(utente, bean.getDataInizio());
		}else {
			ProtocolloPrEPOnDemand protocolloOnDemand = new ProtocolloPrEPOnDemand(utente, bean.getDataInizio());
			protocolloOnDemand.aggiornaDataFine(bean.getDataInizio());
			protocollo = protocolloOnDemand;
		}
		
		DAOFactory factory = DAOFactoryProvider.getDAOFactory();
		ProtocolloPrEPDAO dao = factory.createProtocolloPrEPDAO();
		
		if(protocollo.getDataFine().isBefore(LocalDate.now()) && protocollo.getDataFine() != null) {
			logger.info("Il protocollo inserito ha una data d'inizio nel passato. Viene registrato come già chiuso.");
	        
	        protocollo.setStatoPrEP(false);
	        dao.configuraProtocollo(protocollo);
	       
	        utente.setProtocolloAttivo(null);
		}
		else {
			List<LocalDate> promemoria = protocollo.calcolaGiorniPromemoria(bean.getDataInizio());
			
			if(bean.getRicevereSMS()) {
				attivaSMS(promemoria);
			}
			utente.setProtocolloAttivo(protocollo);
			dao.configuraProtocollo(protocollo);
			logger.info("Protocollo attivo registrato con successo.");
		}
	}
	public void attivaSMS(List<LocalDate> promemoria) {
		//da fare
	}
}
