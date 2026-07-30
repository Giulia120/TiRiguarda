package it.tiriguarda.controller.cli;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import it.tiriguarda.controller.app.GestioneSmsAppController;
import it.tiriguarda.controller.app.RegistraRapportoAppController;
import it.tiriguarda.domain.LivelloRischio;
import it.tiriguarda.domain.StatoSms;
import it.tiriguarda.domain.TipoSms;
import it.tiriguarda.dto.RapportoBean;
import it.tiriguarda.dto.SmsBean;
import it.tiriguarda.exception.DatabaseNonRaggiungibileException;

public class RichiestaSMSRapportoCLIController {

	public void avvia(RapportoBean bean, Scanner scanner) {
		
		System.out.println("\n========================================");
		System.out.println("    ATTENZIONE: RISCHIO RILEVATO  ");
		ViewCLI.stampaSeparatore();
		
		if (bean.getRischio() == LivelloRischio.ALTO) {
			System.out.print("Questo rapporto è un alto rischio, quindi ");
		} else if (bean.getRischio() == LivelloRischio.BASSO) {
			System.out.print("Questo rapporto è un basso rischio, ma ");
		}
		System.out.println("ti consigliamo di fare un test allo scadere del periodo finestra.");
		
		if (bean.getDataFinePeriodoFinestra() != null) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			String dataFormattata = bean.getDataFinePeriodoFinestra().format(formatter);
			System.out.println("Data fine periodo finestra: " + dataFormattata);
		}
		
		while (true) {
			System.out.println("\nVuoi attivare le notifiche SMS per ricordarti di fare il test?");
			System.out.print("Rispondi (si/no, oppure 'q' per annullare TUTTA la registrazione): ");
			
			String risposta = scanner.nextLine().trim().toLowerCase();
			
			if (risposta.equals("q")) {
				System.out.println("\n[INFO] Registrazione rapporto annullata! Torno al menu principale...");
				return;
			}
			
			if (risposta.equals("si")) {
				if (!salvaEConcludi(bean)) {
					return;
				}
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
					return; 
				} catch (IllegalStateException e) {
					ViewCLI.stampaErroreSistema(e.getMessage());
					throw e;
				}
				
				ViewCLI.stampaSuccesso();
				return;
				
			} else if (risposta.equals("no")) {
				if (!salvaEConcludi(bean)) {
					return;
				}
				System.out.println("\n[INFO] Hai detto NO agli SMS.");
				ViewCLI.stampaSuccesso();
				return;
			}
			
			ViewCLI.stampaInvalido();
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