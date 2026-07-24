package it.tiriguarda.controller.cli;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import it.tiriguarda.controller.app.RegistraTestAppController;
import it.tiriguarda.domain.TipoTest;
import it.tiriguarda.dto.TestBean;
import it.tiriguarda.exception.DataFuturaException;
import it.tiriguarda.exception.DatiIncompletiException;

public class RegistraTestCLIController {

	public void avviaRegistrazioneTest(Scanner scanner) {
		boolean fine = false;
        
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		while (!fine) {
			System.out.println("\n========================================");
			System.out.println("          REGISTRAZIONE TEST            ");
			System.out.println("========================================");
			System.out.println("(Digita 'q' in qualsiasi momento per tornare indietro)");

			System.out.print("Data del test (gg/mm/aaaa): ");
			String dataInput = scanner.nextLine();
			
			if (dataInput.equalsIgnoreCase("q")) {
				return;
			}

			java.sql.Date dataConvertita = null;
			boolean dataValida = false;
            
			try {
				LocalDate localDate = LocalDate.parse(dataInput, formatter);
				dataConvertita = java.sql.Date.valueOf(localDate);
				dataValida = true;
			} catch (DateTimeParseException e) {
				System.out.println("\n[ERRORE] Formato data non valido! Usa il formato gg/mm/aaaa.");
			}

			if (dataValida) {
				System.out.println("Che tipo di test hai effettuato?");
				System.out.println("1) Rapido");
				System.out.println("2) Prelievo");
				System.out.print("Scelta (1 o 2): ");
				String tipoInput = scanner.nextLine();
				
				if (tipoInput.equalsIgnoreCase("q")) {
					return;
				}

				TipoTest tipoScelto = null;
				if (tipoInput.equals("1")) {
					tipoScelto = TipoTest.RAPIDO;
				} else if (tipoInput.equals("2")) {
					tipoScelto = TipoTest.PRELIEVO;
				} else {
					System.out.println("\n[ERRORE] Scelta non valida! Inserisci 1 o 2.");
				}

				if (tipoScelto != null) {
					TestBean bean = new TestBean();
					bean.setData(dataConvertita);
					bean.setTipo(tipoScelto);

					try {
						RegistraTestAppController appController = new RegistraTestAppController();
						appController.registraTest(bean);
						
						System.out.println("\nTest registrato con successo! Torno al menu principale...");
						fine = true;
						
					} catch (DatiIncompletiException | DataFuturaException e) {
						System.out.println("\n[ERRORE] " + e.getMessage());
					}
				}
			}
		}
	}
}