package it.tiriguarda.controller.app;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.UUID;
import java.util.logging.Logger;

import it.tiriguarda.dao.DAOFactory;
import it.tiriguarda.dao.ProtocolloPrEPDAO;
import it.tiriguarda.dao.UtenteDAO;
import it.tiriguarda.domain.ProtocolloPrEP;
import it.tiriguarda.domain.ProtocolloPrEPDaily;
import it.tiriguarda.domain.ProtocolloPrEPOnDemand;
import it.tiriguarda.domain.TipologiaPrEP;
import it.tiriguarda.domain.Utente;
import it.tiriguarda.dto.OldProtocolloPrEPBean;
import it.tiriguarda.dto.ProtocolloPrEPBean;
import it.tiriguarda.exception.ProtocolloAttivoException;
import it.tiriguarda.exception.UtenteNonLoggatoException;
import it.tiriguarda.service.SessionManager;

public class PrEPAppController {
	private static final Logger logger = Logger.getLogger(PrEPAppController.class.getName());
	
	public void configuraPrEP(ProtocolloPrEPBean bean) {
	
		Utente utente = SessionManager.getInstance().getUtenteLoggato();
		if (utente == null) {
	        throw new UtenteNonLoggatoException();
	    }
		
		DAOFactory factory = DAOFactory.getDAOFactory();
		ProtocolloPrEPDAO dao = factory.createProtocolloPrEPDAO();
		
		if(dao.esisteProtocollo(utente.getUsername(), bean.getDataInizio(), false)) {
			throw new ProtocolloAttivoException();
		}
		ProtocolloPrEP protocollo = creaProtocolloPrEP(bean, utente);
		if(protocollo.getDataFine() != null && protocollo.getDataFine().isBefore(LocalDate.now(ZoneId.systemDefault()))) {
			logger.info("Il protocollo inserito ha data di fine nel passato, viene ragistrato come non attivo.");
			
			utente.setProtocolloAttivo(null);
			protocollo.setStatoPrEP(false);
		}else {
			utente.setProtocolloAttivo(protocollo.getTipoPrEP());
			UtenteDAO daoUtente = factory.createUtenteDAO();
			daoUtente.aggiornaProtocolloAttivo(utente);
			logger.info("Protocollo attivo registrato con successo.");
		}
		dao.configuraProtocollo(protocollo);
		logger.info("Protocollo registrato con successo.");
		}
	
	public void configuraVecchiaPrEP(OldProtocolloPrEPBean bean) {
		Utente utente = SessionManager.getInstance().getUtenteLoggato();
		if (utente == null) {
	        throw new UtenteNonLoggatoException();
	    }
		
		DAOFactory factory = DAOFactory.getDAOFactory();
		ProtocolloPrEPDAO dao = factory.createProtocolloPrEPDAO();
		
		ProtocolloPrEP oldProtocollo;
	
		String idProtocollo = UUID.randomUUID().toString();
        boolean statoPrEP = false; 
        
        if(bean.getTipoPrEP() == TipologiaPrEP.DAILY) {
        	oldProtocollo = new ProtocolloPrEPDaily(idProtocollo, utente.getUsername(), bean.getDataInizio(), bean.getDataFine(), statoPrEP);
        } else {
        	oldProtocollo = new ProtocolloPrEPOnDemand(idProtocollo, utente.getUsername(), bean.getDataInizio(), bean.getDataFine(), statoPrEP);
        }
		
		dao.configuraProtocollo(oldProtocollo);
		logger.info("Protocollo registrato con successo.");
	}
	
	private ProtocolloPrEP creaProtocolloPrEP(ProtocolloPrEPBean bean, Utente utente) {
		String idProtocollo = UUID.randomUUID().toString();
		
		if(bean.getTipoPrEP() == TipologiaPrEP.DAILY) {
            ProtocolloPrEPDaily protocolloDaily = new ProtocolloPrEPDaily(idProtocollo, utente.getUsername(), bean.getDataInizio(), true, bean.getOrario());
            utente.setProtocolloAttivo(TipologiaPrEP.DAILY);
            return protocolloDaily;
        } else {
            ProtocolloPrEPOnDemand protocolloOnD = new ProtocolloPrEPOnDemand(idProtocollo, utente.getUsername(), bean.getDataInizio(), true, bean.getOrario());
            protocolloOnD.aggiornaDataFine(bean.getDataInizio(), utente.getSessoBiologico());
            if (protocolloOnD.getDataFine().isAfter(LocalDate.now(ZoneId.systemDefault()))) {
                utente.setProtocolloAttivo(TipologiaPrEP.ON_DEMAND);
            }
            return protocolloOnD;
        }
	}
		
}
