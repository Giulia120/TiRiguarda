package it.tiriguarda.controller.app;

import java.util.List;

import it.tiriguarda.dao.DAOFactory;
import it.tiriguarda.dao.DAOFactoryProvider;
import it.tiriguarda.dao.ProtocolloPrEPDAO;
import it.tiriguarda.dao.RapportoDAO;
import it.tiriguarda.dao.TestDAO;
import it.tiriguarda.domain.ProtocolloPrEP;
import it.tiriguarda.domain.Rapporto;
import it.tiriguarda.domain.Test;
import it.tiriguarda.domain.Utente;
import it.tiriguarda.dto.RiepilogoBean;
import it.tiriguarda.service.SessionManager;

public class RiepilogoAppController {
	
	public void effettuaRiepilogo(RiepilogoBean bean){
		Utente utenteCorrente = SessionManager.getInstance().getUtenteLoggato();
		
		DAOFactory factory = DAOFactoryProvider.getDAOFactory();
		ProtocolloPrEPDAO daoPrEP = factory.createProtocolloPrEPDAO();
		List<ProtocolloPrEP> prep = daoPrEP.riepilogoPrEP(utenteCorrente, bean.getData());
		
		RapportoDAO daoRapporto = factory.createRapportoDAO();
		List<Rapporto> rapporti = daoRapporto.riepilogoRapporti(utenteCorrente, bean.getData());
		
		TestDAO daoTest = factory.createTestDAO();
		List<Test> test = daoTest.riepilogoTest(utenteCorrente, bean.getData());
		
		bean.setPrep(prep);
		bean.setRapporti(rapporti);
		bean.setTest(test);
	}
}
