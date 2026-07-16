package it.tiriguarda.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import it.tiriguarda.domain.LivelloRischio;
import it.tiriguarda.domain.Precauzioni;
import it.tiriguarda.domain.ProtocolloPrEP;
import it.tiriguarda.domain.Rapporto;
import it.tiriguarda.domain.Utente;
import it.tiriguarda.dto.RapportoBean;
import it.tiriguarda.logic.rischio.CalcoloRischio;
import it.tiriguarda.logic.rischio.PrEPDecorator;
import it.tiriguarda.logic.rischio.PreservativoDecorator;
import it.tiriguarda.logic.rischio.RischioBase;

public class RegistraRapportoAppController {
	private static RegistraRapportoAppController instance;
	
	private RegistraRapportoAppController() {
	}
	
	public static RegistraRapportoAppController getInstance() {
		if (instance == null) {
			instance = new RegistraRapportoAppController();
		}
		return instance;
	}
	
	public void registraRapporto(RapportoBean bean) throws Exception{
		if(bean.getData() == null || bean.getTipo() == null || bean.getTipo().isEmpty()) {
			throw new Exception("Dati mancanti per registrare il rapporto"); //aggiungere Exception
		}
		Utente utenteCorrente = new Utente("User01", "Giulia", "3331234567"); //fittizzio poi SessionManager
		
		String idRapporto = UUID.randomUUID().toString();
				
		LivelloRischio rischioCalcolato = analizzaRischio(bean, utenteCorrente);
		
		Date dataFinePeriodoFinestra = calcolaPeriodoFinestra(bean.getData(), rischioCalcolato);
		
		Rapporto nuovoRapporto = new Rapporto(utenteCorrente, idRapporto, bean.getData(), rischioCalcolato, dataFinePeriodoFinestra);
		
		//Parte del DAO
	}
	
	private LivelloRischio analizzaRischio(RapportoBean bean, Utente utente) {
		CalcoloRischio calcolatore = new RischioBase(bean.getTipo());
		
		if (bean.getPrecauzioniUsate() == Precauzioni.PRESERVATIVO) {
			calcolatore = new PreservativoDecorator(calcolatore);
		}
		
		ProtocolloPrEP prep = utente.getProtocolloAttivo();
		if (prep != null && prep.getStatoPrEP() == true) {
			calcolatore = new PrEPDecorator(calcolatore);
		}
		
		return calcolatore.calcola();
	}
	
	private Date calcolaPeriodoFinestra(Date dataRapporto, LivelloRischio rischio) {
		if (rischio == LivelloRischio.NULLO) {
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(dataRapporto);
		cal.add(Calendar.DAY_OF_MONTH, 28);
		return cal.getTime();
	}
	
}