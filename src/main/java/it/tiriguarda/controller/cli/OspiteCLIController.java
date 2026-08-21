package it.tiriguarda.controller.cli;

import java.util.Scanner;

public class OspiteCLIController {
	
	public void avvia(Scanner scanner) {
		boolean esci = false;
		while(!esci) {
			ViewCLI.stampaTitolo("Menu' principale ospite");
			ViewCLI.mostraMenu(
					"Questionario",
					"Informazioni");
			
			String scelta = scanner.nextLine();
			
			switch(scelta) {
			case "1":
				QuestionarioCLIController controller = new QuestionarioCLIController();
				controller.avviaQuestionario(scanner);
				break;
			case "2":
				System.out.println("\n[INFO] Sezione Informazioni aperta.");
				break;
			case "q":
				esci = true;
				break;
			default: ViewCLI.stampaInvalido();
			
			}	
		}
	}
}
