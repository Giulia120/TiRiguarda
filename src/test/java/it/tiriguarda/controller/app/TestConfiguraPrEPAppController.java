package it.tiriguarda.controller.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import it.tiriguarda.dao.DAOFactory;
import it.tiriguarda.dao.DAOFactoryProvider;
import it.tiriguarda.dao.ProtocolloPrEPDAO;
import it.tiriguarda.dao.UtenteDAO;
import it.tiriguarda.domain.ProtocolloPrEP;
import it.tiriguarda.domain.SessoBiologico;
import it.tiriguarda.domain.TipologiaPrEP;
import it.tiriguarda.domain.Utente;
import it.tiriguarda.dto.ProtocolloPrEPBean;
import it.tiriguarda.exception.ProtocolloAttivoException;
import it.tiriguarda.service.SessionManager;

public class TestConfiguraPrEPAppController {
	private ConfiguraPrEPAppController controller;
	private Utente utenteTest;
	
	@BeforeEach
	public void setUp() {
		controller = new ConfiguraPrEPAppController();
		utenteTest = new Utente("Anna", "password", SessoBiologico.FEMMINILE, "342776990");
		SessionManager.getInstance().setUtenteLoggato(utenteTest);
		UtenteDAO utenteDAO = DAOFactoryProvider.getDAOFactory().createUtenteDAO();
		utenteDAO.registraUtente(utenteTest);
	}
	
	@AfterEach
	public void tearDown() {
		SessionManager.getInstance().clearSessione();
		ProtocolloPrEPDAO dao = DAOFactoryProvider.getDAOFactory().createProtocolloPrEPDAO();
		ProtocolloPrEP protocollo = dao.trovaProtocolloAttivo(utenteTest.getUsername());
		if (protocollo != null) {
			dao.annullaStatoProtocollo(protocollo);
		}
	}
	
	@Test
	@DisplayName("Configura una PrEP On Demand con data di fine corretta")
	public void testConfiguraPrEPOndemand() throws Exception{
		SessionManager.getInstance();
		ProtocolloPrEPBean beanTest = new ProtocolloPrEPBean();
		
		beanTest.setTipoPrEP(TipologiaPrEP.ON_DEMAND);
		LocalDate dataInizio = LocalDate.now().plusDays(1);
		beanTest.setDataInizio(dataInizio);
		beanTest.setOrario(LocalTime.of(10, 0));
		
		controller.configuraPrEP(beanTest);
		
		DAOFactory factory = DAOFactoryProvider.getDAOFactory();
		ProtocolloPrEPDAO dao = factory.createProtocolloPrEPDAO();
		ProtocolloPrEP protocollo = dao.trovaProtocolloAttivo(utenteTest.getUsername());

		assertEquals(dataInizio.plusDays(7), protocollo.getDataFine());
	}
}
