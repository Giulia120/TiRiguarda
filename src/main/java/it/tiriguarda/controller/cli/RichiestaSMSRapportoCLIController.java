package it.tiriguarda.controller.cli;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import it.tiriguarda.controller.app.GestioneSmsAppController;
import it.tiriguarda.controller.app.RegistraRapportoAppController;
import it.tiriguarda.domain.StatoSms;
import it.tiriguarda.domain.TipoSms;
import it.tiriguarda.dto.RapportoBean;
import it.tiriguarda.dto.SmsBean;
import it.tiriguarda.exception.DatabaseNonRaggiungibileException;
import it.tiriguarda.exception.UtenteNonLoggatoException;

public class RichiestaSMSRapportoCLIController {
	
	public void avvia(RapportoBean bean, Scanner scanner) {
		
		stampaIntestazione(bean);

		String risposta = leggiSceltaValida(scanner);
		
		if (risposta.equals("q")) {
			ViewCLI.stampaMessaggio("[INFO] Registrazione rapporto annullata! Torno al menu principale...");
			return;
		}
		
		if (!salvaEConcludi(bean)) {
			return;
		}
		
		if (risposta.equals("si")) {
			programmaNotificaSms(bean);
		} else {
			ViewCLI.stampaMessaggio("[INFO] Hai detto NO agli SMS.");
		}
	}
	
	private void stampaIntestazione(RapportoBean bean) {
		ViewCLI.stampaSeparatore();
		ViewCLI.stampaMessaggio("    ATTENZIONE: RISCHIO RILEVATO  ");
		ViewCLI.stampaSeparatore();
		
		ViewCLI.stampaMessaggio(String.format("Il rapporto e' a %s rischio, quindi ti consigliamo di fare un test allo scadere del periodo finestra.", bean.getRischio().toString()));
		
		if (bean.getDataFinePeriodoFinestra() != null) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			String dataFormattata = bean.getDataFinePeriodoFinestra().format(formatter);
			ViewCLI.stampaMessaggio("Data fine periodo finestra: " + dataFormattata);
		}
	}
	
	private String leggiSceltaValida(Scanner scanner) {
		while (true) {
			ViewCLI.stampaMessaggio("Vuoi attivare le notifiche SMS per ricordarti di fare il test?");
			ViewCLI.stampaMessaggio("Rispondi (si/no, oppure 'q' per annullare TUTTA la registrazione): ");
			
			String risposta = scanner.nextLine().trim().toLowerCase();
			if (risposta.equals("q") || risposta.equals("si") || risposta.equals("no")) {
				return risposta;
			}
			ViewCLI.stampaInvalido();
		}
	}
	
	private void programmaNotificaSms(RapportoBean bean) {
		SmsBean beanSms = new SmsBean();
		beanSms.setTesto("[PROMEMORIA]: E' ora di fare il test!");
		LocalDateTime dataEOra = LocalDateTime.of(bean.getDataFinePeriodoFinestra(), LocalTime.of(10, 00));
		beanSms.setDataSpedizione(dataEOra);
		beanSms.setTipo(TipoSms.TEST);
		beanSms.setStato(StatoSms.DA_INVIARE);
		
		try {
			GestioneSmsAppController smsController = new GestioneSmsAppController();
			smsController.programmaSms(beanSms);
			ViewCLI.stampaMessaggio("[INFO] Hai detto SI agli SMS! Notifica programmata per le ore 10:00.");
		} catch (DatabaseNonRaggiungibileException e) {
			ViewCLI.stampaErroreCriticoEChiudi(e.getMessage());
		} catch (UtenteNonLoggatoException e) {
			ViewCLI.stampaErroreSistema(e.getMessage());
			throw e;
		}
	}
	
	private boolean salvaEConcludi(RapportoBean bean) {
		try {
			RegistraRapportoAppController appController = new RegistraRapportoAppController();
			appController.salvaRapportoDefinitivo(bean);
			return true;
		} catch (DatabaseNonRaggiungibileException e) {
			ViewCLI.stampaErroreCriticoEChiudi(e.getMessage());
			throw e;
		}
	}
}