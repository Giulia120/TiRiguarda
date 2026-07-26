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

		while (!fine) {
			System.out.println("\n========================================");
			System.out.println("          REGISTRAZIONE TEST            ");
			System.out.println("========================================");
			System.out.println("(Digita 'q' in qualsiasi momento per tornare indietro)");

			LocalDate dataTest = leggiData(scanner);

			TipoTest tipoScelto = leggiTipoTest(scanner);

			try {
				
				TestBean bean = new TestBean();
				bean.setData(dataTest);
				bean.setTipo(tipoScelto);
				
				RegistraTestAppController appController = new RegistraTestAppController();
				appController.registraTest(bean);
				
				System.out.println("\nTest registrato con successo! Torno al menu principale...");
				fine = true;
				
			} catch (DatiIncompletiException | DataFuturaException e) {
				System.out.println("\n[ERRORE DI VALIDAZIONE]: " + e.getMessage());
				System.out.println("Premi INVIO per riprovare...");
				scanner.nextLine();
			} catch (Exception e) {
				System.out.println("\n[ERRORE DI SISTEMA]: Impossibile salvare il test. " + e.getMessage());
				fine = true; 
			}
		}
	}

	private LocalDate leggiData(Scanner scanner) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	    
	    while (true) {
	        System.out.print("Inserisci la data (gg/mm/aaaa): ");
	        String input = scanner.nextLine();
	        
	        if (input.equalsIgnoreCase("q")) {
	            return null;
	        }
	        
	        try {
	        	return LocalDate.parse(input, formatter);
	        } catch (DateTimeParseException e) {
	            System.out.println("Formato o data non valido! Usa gg/mm/aaaa.");
	        }
	    }
	}


	private TipoTest leggiTipoTest(Scanner scanner) {
		while (true) {
			System.out.println("\nChe tipo di test hai effettuato?");
			System.out.println("1) Rapido");
			System.out.println("2) Prelievo");
			System.out.print("Scegli un'opzione: ");
			String input = scanner.nextLine();
			
			if (input.equalsIgnoreCase("q")) {
				return null;
			}

			if (input.equals("1")) {
				return TipoTest.RAPIDO;
			} else if (input.equals("2")) {
				return TipoTest.PRELIEVO;
			} else {
				System.out.println("[ERRORE] Scelta non valida! Inserisci 1 o 2.");
			}
		}
	}
}