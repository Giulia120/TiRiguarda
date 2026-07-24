package it.tiriguarda.controller.app;

import java.util.logging.Logger;

import it.tiriguarda.dao.DAOFactory;
import it.tiriguarda.dao.DAOFactoryProvider;
import it.tiriguarda.dao.ProtocolloPrEPDAO;
import it.tiriguarda.domain.ProtocolloPrEP;
import it.tiriguarda.domain.Utente;
import it.tiriguarda.exception.PrEPAnnullataException;
import it.tiriguarda.exception.PrEPNonEsistenteException;
import it.tiriguarda.service.SessionManager;

public class AnnullaPrEPAppController  {
	private static final Logger logger = Logger.getLogger(AnnullaPrEPAppController.class.getName());
	
	public void verificaStatoPrEP() throws PrEPNonEsistenteException, PrEPAnnullataException {
		Utente utente = SessionManager.getInstance().getUtenteLoggato();

		ProtocolloPrEP protocollo = utente.getProtocolloAttivo();
	
		if(protocollo == null) {
			throw new PrEPNonEsistenteException("Protocollo non esistente");
		}
		
		boolean stato = protocollo.getStatoPrEP();
		
		if(!stato) {
			throw new PrEPAnnullataException("Protocollo già annullato");
		}
	}
	
	public void annullaPrEP() {
		Utente utente = SessionManager.getInstance().getUtenteLoggato();
		ProtocolloPrEP protocollo = utente.getProtocolloAttivo();
		DAOFactory factory = DAOFactoryProvider.getDAOFactory();
		ProtocolloPrEPDAO dao = factory.createProtocolloPrEPDAO();
		dao.annullaStatoProtocollo(protocollo);	
		logger.info("Protocollo PrEP annullato correttamente");
	}
	
}
