package it.tiriguarda.controller.cli;

import java.time.LocalDate;
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
			ViewCLI.stampaTitolo("registrazione test");

			LocalDate dataTest = ViewCLI.leggiData(scanner);

			TipoTest tipoScelto = leggiTipoTest(scanner);

			try {
				
				TestBean bean = new TestBean();
				bean.setData(dataTest);
				bean.setTipo(tipoScelto);
				
				RegistraTestAppController appController = new RegistraTestAppController();
				appController.registraTest(bean);
				
				ViewCLI.stampaSuccesso();
				fine = true;
				
			} catch (DatiIncompletiException | DataFuturaException e) {
				ViewCLI.stampaErrore(e.getMessage());
				System.out.println("Premi INVIO per riprovare...");
				scanner.nextLine();
			} catch (IllegalStateException e) {
				ViewCLI.stampaErroreSistema(e.getMessage());
				throw e;
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
				ViewCLI.stampaInvalido();
			}
		}
	}
}