package it.tiriguarda.controller.app;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import it.tiriguarda.dao.DAOFactory;
import it.tiriguarda.dao.DAOFactoryProvider;
import it.tiriguarda.dao.ProtocolloPrEPDAO;
import it.tiriguarda.domain.ProtocolloPrEP;
import it.tiriguarda.domain.SessoBiologico;
import it.tiriguarda.domain.TipologiaPrEP;
import it.tiriguarda.domain.Utente;
import it.tiriguarda.dto.ProtocolloPrEPBean;
import it.tiriguarda.service.SessionManager;

public class TestConfiguraPrEPAppController {
	private ConfiguraPrEPAppController controller;
	private Utente utenteTest;
	
	@BeforeEach
	void setUp() {
		utenteTest = new Utente("Anna", "pw123", SessoBiologico.FEMMINILE, "34277690");
		SessionManager.getInstance().setUtenteLoggato(utenteTest);
		controller = new ConfiguraPrEPAppController();
	}
	
	@Test
	@DisplayName("Configura una PrEP On Demand con data di fine corretta")
	void testConfiguraPrEPOndemand() throws Exception{
		SessionManager.getInstance();
		ProtocolloPrEPBean beanTest = new ProtocolloPrEPBean();
		
		beanTest.setTipoPrEP(TipologiaPrEP.ON_DEMAND);
		beanTest.setDataInizio(LocalDate.of(2026, 6, 1));
		beanTest.setOrario(LocalTime.of(10, 0));
		
		controller.configuraPrEP(beanTest);
		
		DAOFactory factory = DAOFactoryProvider.getDAOFactory();
		ProtocolloPrEPDAO dao = factory.createProtocolloPrEPDAO();
		ProtocolloPrEP protocollo = dao.trovaProtocolloAttivo(utenteTest.getUsername());
		
		assertEquals(LocalDate.of(2026, 6, 8), protocollo.getDataFine());
	}
	
	@Test
	@DisplayName("")
	void testProtocolloAttivoException() throws Exception{
		SessionManager.getInstance();
		ProtocolloPrEPBean bean = new ProtocolloPrEPBean();
		
		bean.setTipoPrEP(TipologiaPrEP.DAILY);
		bean.setDataInizio(LocalDate.of(2026, 8, 1));
		bean.setOrario(LocalTime.of(10, 0));
		
		controller.configuraPrEP(bean);
		
		ProtocolloPrEPBean beanTest = new ProtocolloPrEPBean();
		
		beanTest.setTipoPrEP(TipologiaPrEP.ON_DEMAND);
		beanTest.setDataInizio(LocalDate.of(2026, 8, 2));
		beanTest.setOrario(LocalTime.of(10, 0));
		
		controller.configuraPrEP(beanTest);
		
		assertThrows(ProtocolloAttivoException.class, () -> controller.configuraPrEP(beanTest));
	}
}
