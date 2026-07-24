package it.tiriguarda.controller.app;

import java.time.LocalDateTime;
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
			protocollo = new ProtocolloPrEPOnDemand(utente, bean.getDataInizio());
		}
		
		List<LocalDateTime> promemoria = protocollo.calcolaGiorniPromemoria(bean.getDataInizio(), bean.getOrario());
		
		if(bean.getRicevereSMS()) {
			attivaSMS(promemoria);
		}
		utente.setProtocolloAttivo(protocollo);
		
		DAOFactory factory = DAOFactoryProvider.getDAOFactory();
		ProtocolloPrEPDAO dao = factory.createProtocolloPrEPDAO();
		dao.configuraProtocollo(protocollo);
		logger.info("Protocollo registrato con successo.");
	}
	public void attivaSMS(List<LocalDateTime> promemoria) {
		
	}
}
