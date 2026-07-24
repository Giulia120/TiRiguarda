package it.tiriguarda.main;

import java.util.Scanner;

import it.tiriguarda.controller.cli.LoginCLIController;
import it.tiriguarda.controller.graphic.ViewDispatcher;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
    	ViewDispatcher.setFinestraPrincipale(primaryStage);
        ViewDispatcher.mostraSceltaConfig();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("========================================");
        System.out.println("       BENVENUTO IN TIRIGUARDA          ");
        System.out.println("========================================");
        System.out.println("Quale interfaccia vuoi utilizzare?");
        System.out.println("1 - Command Line Interface (Terminale)");
        System.out.println("2 - Interfaccia Grafica (JavaFX)");
        System.out.print("Inserisci la tua scelta (1 o 2): ");
        
        String scelta = scanner.nextLine();
        
        if (scelta.equals("1")) {
            System.out.println("\n--- Avvio in modalità CLI ---\n");
            
            LoginCLIController loginCLI = new LoginCLIController();
            loginCLI.avviaLogin(scanner); 
            
        } else if (scelta.equals("2")) {
            System.out.println("\n--- Avvio in modalità Grafica ---");
            
            launch(args);
            
        } else {
            System.out.println("\nScelta non valida. Il programma verrà chiuso.");
        }
        if (!scelta.equals("1")) {
             scanner.close();
        }
    }
}