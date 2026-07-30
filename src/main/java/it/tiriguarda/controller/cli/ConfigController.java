package it.tiriguarda.controller.cli;

import java.util.Scanner;
import it.tiriguarda.config.AppConfig;
import it.tiriguarda.config.AppMode;
import it.tiriguarda.manager.SmsScheduler;

public class ConfigController {
    
    public String configuraApp(Scanner scanner) {
        ViewCLI.stampaSeparatore();
        System.out.println("       BENVENUTO IN TIRIGUARDA          ");
        ViewCLI.stampaSeparatore();
        
        String uiChoice = scegliInterfaccia(scanner);
        scegliModalita(scanner);
        SmsScheduler.getInstance().avviaScheduler();
        
        return uiChoice;
    }

    private String scegliInterfaccia(Scanner scanner) {
        while(true) {
            System.out.println("\nQuale interfaccia vuoi utilizzare?");
            System.out.println("1 - Command Line Interface (Terminale)");
            System.out.println("2 - Interfaccia Grafica (JavaFX)");
            System.out.print("Scegli un'opzione (1 o 2): ");
            
            String scelta = scanner.nextLine();
            if (scelta.equals("1")) return "CLI";
            if (scelta.equals("2")) return "GUI";
            ViewCLI.stampaInvalido();
        }
    }

    private void scegliModalita(Scanner scanner) {
        while (true) {
            System.out.println("\nScegli la modalità di persistenza dei dati:");
            System.out.println("1 - Demo (Dati su memoria volatile)");
            System.out.println("2 - Full (Tutto su Database)");
            System.out.println("3 - Mista (Database + Test su File System)");
            System.out.print("Scegli un'opzione (1, 2 o 3): ");
            
            String scelta = scanner.nextLine();
            switch (scelta) {
                case "1":
                    AppConfig.setCurrentMode(AppMode.DEMO);
                    return;
                case "2":
                    AppConfig.setCurrentMode(AppMode.FULL_DB);
                    return;
                case "3":
                    AppConfig.setCurrentMode(AppMode.FULL_FS);
                    return;
                default:
                    ViewCLI.stampaInvalido();
            }
        }
    }
}