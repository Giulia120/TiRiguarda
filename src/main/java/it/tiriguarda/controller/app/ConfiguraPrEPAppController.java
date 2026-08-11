package it.tiriguarda.controller.app;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.UUID;
import java.util.logging.Logger;

import it.tiriguarda.dao.DAOFactory;
import it.tiriguarda.dao.DAOFactoryProvider;
import it.tiriguarda.dao.ProtocolloPrEPDAO;
import it.tiriguarda.dao.UtenteDAO;
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
	
	public void configuraPrEP(ProtocolloPrEPBean bean) {
		if(bean.getDataInizio() == null || bean.getOrario() == null) {
			throw new DatiIncompletiException();
		}
	
		Utente utente = SessionManager.getInstance().getUtenteLoggato();
		if (utente == null) {
	        throw new IllegalStateException("Errore critico: Nessun utente loggato in sessione.");
	    }
		
		DAOFactory factory = DAOFactoryProvider.getDAOFactory();
		ProtocolloPrEPDAO dao = factory.createProtocolloPrEPDAO();
		
		if(dao.esisteProtocollo(utente.getUsername(), bean.getDataInizio(), false)) {
			throw new ProtocolloAttivoException();
		}
		String idProtocollo = UUID.randomUUID().toString();
		
		ProtocolloPrEP protocollo;
		
		if(bean.getTipoPrEP() == TipologiaPrEP.DAILY) {
		    ProtocolloPrEPDaily protocolloDaily = new ProtocolloPrEPDaily(idProtocollo, utente.getUsername(), bean.getDataInizio(), true, bean.getOrario());
		    protocollo = protocolloDaily;
		    utente.setProtocolloAttivo(TipologiaPrEP.DAILY);
		}else {
			ProtocolloPrEPOnDemand protocolloOnD = new ProtocolloPrEPOnDemand(idProtocollo, utente.getUsername(), bean.getDataInizio(), true, bean.getOrario());
			protocolloOnD.aggiornaDataFine(bean.getDataInizio(), utente.getSessoBiologico());
			protocollo = protocolloOnD;
			if (protocollo.getDataFine().isAfter(LocalDate.now(ZoneId.systemDefault()))) {
				utente.setProtocolloAttivo(TipologiaPrEP.ON_DEMAND);
			}
		}
				
		if(protocollo.getDataFine() != null && protocollo.getDataFine().isBefore(LocalDate.now(ZoneId.systemDefault()))) {
			logger.info("Il protocollo inserito ha una data di fine nel passato. Viene registrato come gia' chiuso.");
	        protocollo.setStatoPrEP(false);
	        dao.configuraProtocollo(protocollo);
	       
	        utente.setProtocolloAttivo(null);
		}
		else {
			if(bean.getRicevereSMS()) {
				GestioneSmsAppController smsController = new GestioneSmsAppController();
			    smsController.programmaPromemoriaPrEP(protocollo, utente);
			}
			utente.setProtocolloAttivo(protocollo.getTipoPrEP());
			dao.configuraProtocollo(protocollo);
			UtenteDAO daoUtente = factory.createUtenteDAO();
			daoUtente.aggiornaProtocolloAttivo(utente);
			logger.info("Protocollo attivo registrato con successo.");
		}
	}

}
