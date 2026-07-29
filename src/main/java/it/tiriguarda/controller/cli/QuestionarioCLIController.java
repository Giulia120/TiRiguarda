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

		System.out.println("\n========================================");
		System.out.println("        VALUTAZIONE RISCHIO HIV         ");
		System.out.println("========================================");
		System.out.println("(Digita 'q' per tornare indietro)\n");

		for (int i = 0; i < domande.size(); i++) {
			QuestionBean q = domande.get(i);
			
			while (true) {
				System.out.println("Domanda " + q.getTesto());
				
				List<String> opzioni = q.getOpzioni();
				for (int j = 0; j < opzioni.size(); j++) {
					System.out.println((j + 1) + " - " + opzioni.get(j));
				}
				
				System.out.print("Scegli un'opzione (1-" + opzioni.size() + "): ");
				String input = scanner.nextLine();

				if (input.equalsIgnoreCase("q")) {
					return;
				}

				try {
					int scelta = Integer.parseInt(input);
					if (scelta >= 1 && scelta <= opzioni.size()) {
						risposteUtente.add(scelta - 1);
						System.out.println();
						break;
					} else {
						System.out.println("[ERRORE] Opzione non valida, riprova!\n");
					}
				} catch (NumberFormatException e) {
					System.out.println("[ERRORE] Inserisci un numero valido!\n");
				}
			}
		}

		LivelloRischio rischio = appController.valutaRischio(risposteUtente);
		mostraRisultato(rischio);
	}

	private void mostraRisultato(LivelloRischio rischio) {
		System.out.println("========================================");
		System.out.println("          RISULTATO VALUTAZIONE         ");
		System.out.println("========================================");

		switch (rischio) {
			case NULLO:
				System.out.println("Livello di Rischio: BASSO O NULLO");
				System.out.println("I tuoi comportamenti indicano un'eccellente attenzione alla prevenzione. \nIl rischio biologico attuale è minimo. \nContinua così e ricordati di inserire il test HIV nella tua normale routine \ndi controlli sanitari in caso di nuovi partner futuri.");
				break;
			case BASSO:
				System.out.println("Livello di Rischio: MODERATO");
				System.out.println("Ci sono alcune abitudini o situazioni episodiche in cui potresti esporti accidentalmente al virus.\nPotrebbe essere utile stabilizzare l'uso del preservativo dall'inizio del rapporto o valutare \nl'inizio della PrEP con un infettivologo se l'uso del profilattico risulta difficile da mantenere.");
				break;
			case ALTO:
				System.out.println("Livello di Rischio: ALTO");
				System.out.println("I tuoi comportamenti ti espongono a una concreta e frequente probabilità di contrarre l'HIV. \nSi raccomanda vivamente di effettuare un test HIV al più presto (se non eseguito di recente) \ne di prenotare un colloquio clinico per accedere alla PrEP o ad altre strategie mirate di riduzione del danno.");
				break;
		}
		
		System.out.println("========================================\n");
	}
}