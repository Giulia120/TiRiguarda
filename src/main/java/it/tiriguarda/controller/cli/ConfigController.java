package it.tiriguarda.controller.cli;

import java.util.Scanner;
import it.tiriguarda.config.AppConfig;
import it.tiriguarda.config.AppMode;
import it.tiriguarda.manager.SmsScheduler;

public class ConfigController {
	
    public String configuraApp(Scanner scanner) {
        ViewCLI.stampaSeparatore();
        ViewCLI.stampaMessaggio("       BENVENUTO IN TIRIGUARDA          ");
        ViewCLI.stampaSeparatore();
        
        String uiChoice = scegliInterfaccia(scanner);
        scegliModalita(scanner);
        SmsScheduler.getInstance().avviaScheduler();
        
        return uiChoice;
    }
    
    private String scegliInterfaccia(Scanner scanner) {
        while(true) {
        	ViewCLI.stampaMessaggio("Quale interfaccia vuoi utilizzare?");
        	ViewCLI.stampaMessaggio("1 - Command Line Interface (Terminale)");
        	ViewCLI.stampaMessaggio("2 - Interfaccia Grafica (JavaFX)");
        	ViewCLI.stampaMessaggio("Scegli un'opzione (1 o 2): ");
            
            String scelta = scanner.nextLine();
            if (scelta.equals("1")) return "CLI";
            if (scelta.equals("2")) return "GUI";
            ViewCLI.stampaInvalido();
        }
    }
    
    private void scegliModalita(Scanner scanner) {
        while (true) {
        	ViewCLI.stampaMessaggio("Scegli la modalita' di persistenza dei dati:");
        	ViewCLI.stampaMessaggio("1 - Demo (Dati su memoria volatile)");
        	ViewCLI.stampaMessaggio("2 - Full (Tutto su Database)");
        	ViewCLI.stampaMessaggio("3 - Mista (Database + Test su File System)");
        	ViewCLI.stampaMessaggio("Scegli un'opzione (1, 2 o 3): ");
            
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