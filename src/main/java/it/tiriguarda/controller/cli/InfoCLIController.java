package it.tiriguarda.controller.cli;

import java.util.Scanner;

public class InfoCLIController {
	
	public void avvia(Scanner scanner) {
		ViewCLI.stampaTitolo("Informazioni");
		ViewCLI.stampaMessaggio("Qui ci sono le info!"
				+ "\n..."
				+"\n"
				+ "\nNon è vero, per le vere info accedere alla modalità grafica!");
		
		boolean esci = false;
		
		while (!esci) {
			String input = scanner.nextLine().trim();
			
			if (input.equalsIgnoreCase("q")) {
				esci = true;
			} else {
				ViewCLI.stampaMessaggio("Comando non riconosciuto. Premi 'q' per tornare indietro...");
			}
		}
	}
}