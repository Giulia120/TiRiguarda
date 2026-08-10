package it.tiriguarda.controller.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
import it.tiriguarda.domain.ProtocolloPrEP;
import it.tiriguarda.domain.SessoBiologico;
import it.tiriguarda.domain.TipologiaPrEP;
import it.tiriguarda.domain.Utente;
import it.tiriguarda.dto.ProtocolloPrEPBean;
import it.tiriguarda.exception.ProtocolloAttivoException;
import it.tiriguarda.service.SessionManager;


/**
 * Test class for ConfiguraPrEPAppController.
 * author: Caterina Spinelli
 */

	public class TestConfiguraPrEPAppController {
		private ConfiguraPrEPAppController controller;
		private Utente utenteTest;
		
		@BeforeEach
		public void setUp() {
			controller = new ConfiguraPrEPAppController();
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
		private void fakeUtente(String username) {
			utenteTest = new Utente(username , "password", SessoBiologico.FEMMINILE, "342776990");
			SessionManager.getInstance().setUtenteLoggato(utenteTest);
		}
		
		@Test
		@DisplayName("Configura una PrEP On Demand con data di fine corretta")
		public void testConfiguraPrEPOndemand() throws Exception{
			fakeUtente("Utente1");
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
		
		@Test
		@DisplayName("Lancia eccezione se si tenta di configurare una PrEP con un protocollo già attivo")
		public void testConfiguraPrEPConProtocolloAttivoLanciaEccezione() throws Exception {
			fakeUtente("Utente2");
			ProtocolloPrEPBean beanPrimo = new ProtocolloPrEPBean();
			beanPrimo.setTipoPrEP(TipologiaPrEP.ON_DEMAND);
			beanPrimo.setDataInizio(LocalDate.now().plusDays(1));
			beanPrimo.setOrario(LocalTime.of(10, 0));
			
			controller.configuraPrEP(beanPrimo);
			
			ProtocolloPrEPBean beanSecondo = new ProtocolloPrEPBean();
			beanSecondo.setTipoPrEP(TipologiaPrEP.ON_DEMAND);
			beanSecondo.setDataInizio(LocalDate.now().plusDays(2));
			beanSecondo.setOrario(LocalTime.of(14, 0));
			
			assertThrows(ProtocolloAttivoException.class, () -> controller.configuraPrEP(beanSecondo));
		}
		
		@Test
		@DisplayName("Lancia eccezione se si tenta di configurare una PrEP senza specificare l'orario")
		public void testConfiguraPrEPSenzaOrarioLanciaEccezione() throws Exception{
			fakeUtente("Utente3");
			ProtocolloPrEPBean beanTest = new ProtocolloPrEPBean();
			beanTest.setTipoPrEP(TipologiaPrEP.ON_DEMAND);
			beanTest.setDataInizio(LocalDate.now().plusDays(1));
			
			assertThrows(Exception.class, () -> controller.configuraPrEP(beanTest));
		}
		
		@Test
		@DisplayName("Configura una PrEP con fine nel passato: il protocollo non risulta attivo")
		public void testConfiguraPrEPConFineNelPassatoVieneAnnullata() throws Exception {
			fakeUtente("Utente4");
			ProtocolloPrEPBean bean = new ProtocolloPrEPBean();
			bean.setTipoPrEP(TipologiaPrEP.ON_DEMAND);
			LocalDate dataInizioPassata = LocalDate.now().minusDays(10);
			bean.setDataInizio(dataInizioPassata);
			bean.setOrario(LocalTime.of(10, 0));
			controller.configuraPrEP(bean);
			DAOFactory factory = DAOFactoryProvider.getDAOFactory();
			ProtocolloPrEPDAO dao = factory.createProtocolloPrEPDAO();
			ProtocolloPrEP protocolloAttivo = dao.trovaProtocolloAttivo(utenteTest.getUsername());
			assertEquals(false, protocolloAttivo.getStatoPrEP());
		}
}
