package it.tiriguarda.controller.cli;

import java.time.LocalDate;
import java.util.Scanner;

import it.tiriguarda.controller.app.RegistraTestAppController;
import it.tiriguarda.domain.TipoTest;
import it.tiriguarda.dto.TestBean;
import it.tiriguarda.exception.DataFuturaException;
import it.tiriguarda.exception.DatiIncompletiException;
import it.tiriguarda.exception.UtenteNonLoggatoException;

public class RegistraTestCLIController {
	
	public void avviaRegistrazioneTest(Scanner scanner) {
		boolean fine = false;

		while (!fine) {
			ViewCLI.stampaTitolo("registrazione test");

			LocalDate dataTest = ViewCLI.leggiData(scanner);
			if (dataTest == null) return;

			TipoTest tipoScelto = leggiTipoTest(scanner);
			if (tipoScelto == null) return;

			try {
				
				TestBean bean = new TestBean();
				bean.setData(dataTest);
				bean.setTipo(tipoScelto);
				
				RegistraTestAppController appController = new RegistraTestAppController();
				appController.registraTest(bean);
				
				ViewCLI.stampaSuccesso(scanner);
				fine = true;
				
			} catch (DatiIncompletiException | DataFuturaException e) {
				ViewCLI.stampaErrore(e.getMessage());
				ViewCLI.stampaMessaggio("Premi INVIO per riprovare...");
				scanner.nextLine();
			} catch (UtenteNonLoggatoException e) {
				ViewCLI.stampaErroreSistema(e.getMessage());
				throw e;
			}
		}
	}
	
	private TipoTest leggiTipoTest(Scanner scanner) {
		while (true) {
			ViewCLI.stampaMessaggio("Che tipo di test hai effettuato?\n");
			ViewCLI.mostraMenu(
					"Rapido",
					"Prelievo");
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