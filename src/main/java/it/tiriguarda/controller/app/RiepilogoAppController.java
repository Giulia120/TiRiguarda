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
	
	public RiepilogoBean effettuaRiepilogo(){
		Utente utenteCorrente = SessionManager.getInstance().getUtenteLoggato();
		
		DAOFactory factory = DAOFactoryProvider.getDAOFactory();
		ProtocolloPrEPDAO daoPrEP = factory.createProtocolloPrEPDAO();
		List<ProtocolloPrEP> prep = daoPrEP.riepilogoPrEP(utenteCorrente);
		
		RapportoDAO daoRapporto = factory.createRapportoDAO();
		List<Rapporto> rapporti = daoRapporto.riepilogoRapporti(utenteCorrente);
		
		TestDAO daoTest = factory.createTestDAO();
		List<Test> test = daoTest.riepilogoTest(utenteCorrente);
		
		RiepilogoBean bean = new RiepilogoBean();
		bean.setPrep(prep);
		bean.setRapporti(rapporti);
		bean.setTest(test);
		
		return bean;
	}
}
