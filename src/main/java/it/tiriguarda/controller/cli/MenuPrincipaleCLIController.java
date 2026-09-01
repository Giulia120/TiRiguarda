package it.tiriguarda.controller.cli;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import it.tiriguarda.exception.UtenteNonLoggatoException;
import it.tiriguarda.service.SessionManager;

public class MenuPrincipaleCLIController {

    public void avviaMenu(Scanner scanner) {
        boolean esci = false;
        List<String> opzioniRiservate = Arrays.asList("1", "2", "3", "4", "5");

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
            
            boolean isGuest = SessionManager.getInstance().getUtenteLoggato() == null;

            if (isGuest && opzioniRiservate.contains(scelta)) {
                ViewCLI.stampaErrore("Funzionalita' riservata ad utenti registrati");
                continue;
            }

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
					InfoCLIController infoController = new InfoCLIController();
					infoController.avvia(scanner);
					break;
					
				case "q":
					SessionManager.getInstance().clearSessione();
					return;
					
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