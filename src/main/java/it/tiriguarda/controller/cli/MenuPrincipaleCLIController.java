package it.tiriguarda.controller.cli;

import java.util.Scanner;

import it.tiriguarda.exception.UtenteNonLoggatoException;

public class MenuPrincipaleCLIController {
	
	public void avviaMenu(Scanner scanner) {
		boolean esci = false;

		while (!esci) {
			ViewCLI.stampaTitolo("Menu Principale");
			ViewCLI.mostraMenu(
					"Registra Rapporto", 
					"Registra Test", 
					"Visualizza Profilo",
					"Riepilogo",
					"PrEP",
					"Questionario",
					"Informazioni" );

			String scelta = scanner.nextLine();

			try{
				switch (scelta) {
				case "1":
					RegistraRapportoCLIController registraRapportoController = new RegistraRapportoCLIController();
					registraRapportoController.avviaRegistrazioneRapporto(scanner);
					break;
					
				case "2":
					RegistraTestCLIController registraTestController = new RegistraTestCLIController();
					registraTestController.avviaRegistrazioneTest(scanner);
					break;
					
				case "3":
					ProfiloCLIController profiloController = new ProfiloCLIController();
					profiloController.avviaProfilo(scanner);
					break;
					
				case "4":
					RiepilogoCLIController riepilogoController = new RiepilogoCLIController();
					riepilogoController.mostraRiepilogo(scanner);
					break;
					
				case "5":
					SceltaPrEPCLIController prEPController = new SceltaPrEPCLIController();
					prEPController.avviaPrEP(scanner);
					break;
					
				case "6":
					QuestionarioCLIController questionarioController = new QuestionarioCLIController();
					questionarioController.avviaQuestionario(scanner);
					break;
					
				case "7":
					System.out.println("\n[INFO] Sezione Informazioni aperta.");
					break;
					
				case "q":
					ViewCLI.stampaMessaggio("Chiusura sessione CLI");
					esci = true;
					break;
					
				default:
					ViewCLI.stampaInvalido();
					}
			}catch(UtenteNonLoggatoException e) {
				esci = true;
				return;
			}
		}
	}
}