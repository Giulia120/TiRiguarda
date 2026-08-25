package it.tiriguarda.controller.cli;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import it.tiriguarda.exception.UtenteNonLoggatoException;
import it.tiriguarda.service.SessionManager;

public class MenuPrincipaleCLIController {

    private RegistraRapportoCLIController registraRapportoController = new RegistraRapportoCLIController();
    private RegistraTestCLIController registraTestController = new RegistraTestCLIController();
    private ProfiloCLIController profiloController = new ProfiloCLIController();
    private RiepilogoCLIController riepilogoController = new RiepilogoCLIController();
    private SceltaPrEPCLIController prEPController = new SceltaPrEPCLIController();
    private QuestionarioCLIController questionarioController = new QuestionarioCLIController();
    private InfoCLIController infoController = new InfoCLIController();

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
                    registraRapportoController.avviaRegistrazioneRapporto(scanner);
                    break;
                    
                case "2":
                    registraTestController.avviaRegistrazioneTest(scanner);
                    break;
                    
                case "3":
                    profiloController.avviaProfilo(scanner);
                    break;
                    
                case "4":
                    riepilogoController.mostraRiepilogo(scanner);
                    break;
                    
                case "5":
                    prEPController.avviaPrEP(scanner);
                    break;
                    
                case "6":
                    questionarioController.avviaQuestionario(scanner);
                    break;
                    
                case "7":
                    infoController.avvia(scanner);
                    break;
                    
                case "q":
                    SessionManager.getInstance().clearSessione();
                    return;
                    
                default:
                    ViewCLI.stampaInvalido();
                }
            } catch(UtenteNonLoggatoException e) {
                esci = true;
                return;
            }
        }
    }
}