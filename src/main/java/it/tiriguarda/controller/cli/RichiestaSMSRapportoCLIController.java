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

public class RichiestaSMSRapportoCLIController {
	@SuppressWarnings("java:S106")
	public void avvia(RapportoBean bean, Scanner scanner) {
		
		stampaIntestazione(bean);

		String risposta = leggiSceltaValida(scanner);
		
		if (risposta.equals("q")) {
			System.out.println("\n[INFO] Registrazione rapporto annullata! Torno al menu principale...");
			return;
		}
		
		if (!salvaEConcludi(bean)) {
			return;
		}
		
		if (risposta.equals("si")) {
			programmaNotificaSms(bean);
		} else {
			System.out.println("\n[INFO] Hai detto NO agli SMS.");
		}
	}
	@SuppressWarnings("java:S106")
	private void stampaIntestazione(RapportoBean bean) {
		System.out.println("\n========================================");
		System.out.println("    ATTENZIONE: RISCHIO RILEVATO  ");
		ViewCLI.stampaSeparatore();
		
		System.out.println(String.format("Il rapporto è a %s rischio, quindi ti consigliamo di fare un test allo scadere del periodo finestra.", bean.getRischio().toString()));
		
		if (bean.getDataFinePeriodoFinestra() != null) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			String dataFormattata = bean.getDataFinePeriodoFinestra().format(formatter);
			System.out.println("Data fine periodo finestra: " + dataFormattata);
		}
	}
	@SuppressWarnings("java:S106")
	private String leggiSceltaValida(Scanner scanner) {
		while (true) {
			System.out.println("\nVuoi attivare le notifiche SMS per ricordarti di fare il test?");
			System.out.print("Rispondi (si/no, oppure 'q' per annullare TUTTA la registrazione): ");
			
			String risposta = scanner.nextLine().trim().toLowerCase();
			if (risposta.equals("q") || risposta.equals("si") || risposta.equals("no")) {
				return risposta;
			}
			ViewCLI.stampaInvalido();
		}
	}
	@SuppressWarnings("java:S106")
	private void programmaNotificaSms(RapportoBean bean) {
		SmsBean beanSms = new SmsBean();
		beanSms.setTesto("[PROMEMORIA]: È ora di fare il test!");
		LocalDateTime dataEOra = LocalDateTime.of(bean.getDataFinePeriodoFinestra(), LocalTime.of(10, 00));
		beanSms.setDataSpedizione(dataEOra);
		beanSms.setTipo(TipoSms.TEST);
		beanSms.setStato(StatoSms.DA_INVIARE);
		
		try {
			GestioneSmsAppController smsController = new GestioneSmsAppController();
			smsController.programmaSms(beanSms);
			System.out.println("\n[INFO] Hai detto SI agli SMS! Notifica programmata per le ore 10:00.");
		} catch (DatabaseNonRaggiungibileException e) {
			ViewCLI.stampaErroreCriticoEChiudi(e.getMessage());
		} catch (IllegalStateException e) {
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
			ViewCLI.stampaErroreSistema(e.getMessage());
			throw e;
		}
	}
}