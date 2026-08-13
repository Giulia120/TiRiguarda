package it.tiriguarda.controller.cli;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import it.tiriguarda.controller.app.QuestionarioAppController;
import it.tiriguarda.domain.LivelloRischio;
import it.tiriguarda.dto.QuestionBean;

public class QuestionarioCLIController {

	public void avviaQuestionario(Scanner scanner) {
		QuestionarioAppController appController = new QuestionarioAppController();
		List<QuestionBean> domande = appController.getQuestionBeans();
		List<Integer> risposteUtente = new ArrayList<>();

		ViewCLI.stampaTitolo("VALUTAZIONE DEL COMPORTAMENTO SESSUALE");

		for (QuestionBean q : domande) {
			Integer risposta = chiediDomanda(scanner, q);
			
			if (risposta == null) {
				return;
			}
			risposteUtente.add(risposta);
		}

		LivelloRischio rischio = appController.valutaRischio(risposteUtente);
		mostraRisultato(rischio);
	}
	
	private Integer chiediDomanda(Scanner scanner, QuestionBean q) {
		while (true) {
			System.out.println("Domanda " + q.getTesto());
			
			List<String> opzioni = q.getOpzioni();
			for (int j = 0; j < opzioni.size(); j++) {
				System.out.println((j + 1) + " - " + opzioni.get(j));
			}
			
			System.out.print("Scegli un'opzione (1-" + opzioni.size() + "): ");
			String input = scanner.nextLine();

			if (input.equalsIgnoreCase("q")) {
				return null;
			}

			try {
				int scelta = Integer.parseInt(input);
				if (scelta >= 1 && scelta <= opzioni.size()) {
					System.out.println();
					return scelta - 1;
				} else {
					ViewCLI.stampaInvalido();
				}
			} catch (NumberFormatException e) {
				ViewCLI.stampaInvalido();
			}
		}
	}
	
	private void mostraRisultato(LivelloRischio rischio) {
		ViewCLI.stampaSeparatore();
		System.out.println("          RISULTATO VALUTAZIONE         ");
		ViewCLI.stampaSeparatore();

		switch (rischio) {
			case NULLO:
				System.out.println("Livello di Rischio: BASSO O NULLO");
				System.out.println("I tuoi comportamenti indicano un'eccellente attenzione alla prevenzione. \nIl rischio biologico attuale e' minimo. \nContinua cosi' e ricordati di inserire il test HIV nella tua normale routine \ndi controlli sanitari in caso di nuovi partner futuri.");
				break;
			case BASSO:
				System.out.println("Livello di Rischio: MODERATO");
				System.out.println("Ci sono alcune abitudini o situazioni episodiche in cui potresti esporti accidentalmente al virus.\nPotrebbe essere utile stabilizzare l'uso del preservativo dall'inizio del rapporto o valutare \nl'inizio della PrEP con un infettivologo se l'uso del profilattico risulta difficile da mantenere.");
				break;
			case ALTO:
				System.out.println("Livello di Rischio: ALTO");
				System.out.println("I tuoi comportamenti ti espongono a una concreta e frequente probabilita' di contrarre l'HIV. \nSi raccomanda vivamente di effettuare un test HIV al piu' presto (se non eseguito di recente) \ne di prenotare un colloquio clinico per accedere alla PrEP o ad altre strategie mirate di riduzione del danno.");
				break;
		}
		
		 ViewCLI.stampaSeparatore();
	}
}