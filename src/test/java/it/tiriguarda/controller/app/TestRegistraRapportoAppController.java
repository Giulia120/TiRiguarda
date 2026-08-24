package it.tiriguarda.controller.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import it.tiriguarda.dao.DAOFactory;
import it.tiriguarda.dao.ProtocolloPrEPDAO;
import it.tiriguarda.domain.LivelloRischio;
import it.tiriguarda.domain.Precauzioni;
import it.tiriguarda.domain.ProtocolloPrEP;
import it.tiriguarda.domain.ProtocolloPrEPDaily;
import it.tiriguarda.domain.SessoBiologico;
import it.tiriguarda.domain.TipoRapporto;
import it.tiriguarda.domain.Utente;
import it.tiriguarda.dto.RapportoBean;
import it.tiriguarda.exception.UtenteNonLoggatoException;
import it.tiriguarda.logic.observer.NuovoRapportoObserver;
import it.tiriguarda.service.SessionManager;

/**
 * Test class for RegistraRapportoAppController.
 * author: Giulia Pace
 */

public class TestRegistraRapportoAppController {
	private RegistraRapportoAppController controllerTest;
	private Utente utenteTest;
	
	@BeforeEach
	public void setUp() {
		controllerTest = new RegistraRapportoAppController();
		utenteTest = new Utente("utenteTest", "passwordHash", SessoBiologico.MASCHILE, "3331234567");
		SessionManager.getInstance().setUtenteLoggato(utenteTest);
	}
	
	@AfterEach
	public void tearDown() {
		SessionManager.getInstance().clearSessione();
		ProtocolloPrEPDAO dao = DAOFactory.getDAOFactory().createProtocolloPrEPDAO();
		ProtocolloPrEP protocolloDiTest = dao.trovaProtocolloAttivo(utenteTest.getUsername()); 
        if (protocolloDiTest!= null) {
        	dao.annullaStatoProtocollo(protocolloDiTest);
        	}
        }
	
	private RapportoBean fakeBean(Precauzioni precauzioni) {
		RapportoBean beanInput = new RapportoBean();
		beanInput.setData(LocalDate.now());
		ArrayList<TipoRapporto> tipo = new ArrayList<>();
		tipo.add(TipoRapporto.PENETRATIVO);
		beanInput.setTipo(tipo);
		beanInput.setPrecauzioniUsate(precauzioni);
		return beanInput;
	}
	
	@Test
	@DisplayName("Se l'utente ha la PrEP attiva e non il preservativo, il rischio calcolato deve essere NULLO")
	public void testValutaRischioConPrEP() {
		ProtocolloPrEPDAO dao = DAOFactory.getDAOFactory().createProtocolloPrEPDAO();
		ProtocolloPrEPDaily protocolloDiTest = new ProtocolloPrEPDaily("id123", "utenteTest", LocalDate.now().minusDays(1), true, LocalTime.NOON); 
        dao.configuraProtocollo(protocolloDiTest);
		RapportoBean beanInput = fakeBean(Precauzioni.NULLA);
		
		RapportoBean risultato = controllerTest.valutaRischio(beanInput);
		
		assertEquals(LivelloRischio.NULLO, risultato.getRischio());
	}
	
	@Test
	@DisplayName("Se l'utente usa solo il preservativo, il rischio calcolato deve essere NULLO")
	public void testValutaRischioConPreservativo() {
		RapportoBean beanInput = fakeBean(Precauzioni.PRESERVATIVO);
		
		RapportoBean risultato = controllerTest.valutaRischio(beanInput);
		
		assertEquals(LivelloRischio.NULLO, risultato.getRischio());
		
	}
	
	@Test
	@DisplayName("Se l'utente non usa precauzioni in un rapp penetrativo, il rischio calcolato deve essere ALTO")
	public void testValutaRischioSenzaPrecauzioni() {
		RapportoBean beanInput = fakeBean(Precauzioni.NULLA);
		
		RapportoBean risultato = controllerTest.valutaRischio(beanInput);
		
		assertEquals(LivelloRischio.ALTO, risultato.getRischio());
	}
	
	@Test
    @DisplayName("Il salvataggio definitivo deve aggiornare lo stato del controller e registrare il rapporto")
    public void testSalvaRapportoDefinitivo() {
        RapportoBean beanInput = fakeBean(Precauzioni.PRESERVATIVO);
        beanInput.setRischio(LivelloRischio.NULLO);

        controllerTest.salvaRapportoDefinitivo(beanInput);

        assertEquals(utenteTest, controllerTest.getUtenteRapportoSalvato());
    }
	
	@Test
    @DisplayName("Il salvataggio definitivo deve notificare gli observer registrati")
    public void testSalvaRapportoNotificaObservers() {
        RapportoBean beanInput = fakeBean(Precauzioni.NULLA);
        beanInput.setRischio(LivelloRischio.ALTO);
        class FintoObserver implements NuovoRapportoObserver {
            boolean aggiornato = false;
            @Override
            public void update() {
                aggiornato = true;
            }
        }
        FintoObserver observerDiTest = new FintoObserver();
        controllerTest.attach(observerDiTest);

        controllerTest.salvaRapportoDefinitivo(beanInput);

        assertEquals(true, observerDiTest.aggiornato);
    }
	
	@Test
	@DisplayName("Se non c'e' un utente loggato, salvaRapportoDefinitivo deve lanciare un'eccezione")
	public void testSalvaRapportoDefinitivoSenzaUtenteLoggato() {
	    SessionManager.getInstance().clearSessione();
	    
	    RapportoBean beanInput = fakeBean(Precauzioni.NULLA);
	    beanInput.setRischio(LivelloRischio.ALTO);
	    
	    assertThrows(UtenteNonLoggatoException.class, () -> {
	        controllerTest.salvaRapportoDefinitivo(beanInput);
	    });
	}
	
	@Test
	@DisplayName("Se non c'e' un utente loggato, valutaRischio deve lanciare un'eccezione")
	public void testValutaRischioSenzaUtenteLoggato() {
	    SessionManager.getInstance().clearSessione();
	    
	    RapportoBean beanInput = fakeBean(Precauzioni.NULLA);
	    
	    assertThrows(UtenteNonLoggatoException.class, () -> {
	        controllerTest.valutaRischio(beanInput);
	    });
	}

}
