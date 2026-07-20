package it.tiriguarda.main;

import java.util.Scanner;

import it.tiriguarda.controller.cli.MenuPrincipaleCLIController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

// Quando lo creerai, toglierai il commento a questo import!
// import it.tiriguarda.controller.cli.MenuPrincipaleCLIController;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/it/tiriguarda/view/MenuPrincipale.fxml"));
            Parent root = loader.load();
            
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Ti Riguarda");
            primaryStage.setResizable(false);
            primaryStage.show();
            
        } catch(Exception e) {
            System.out.println("Errore fatale durante l'avvio dell'interfaccia grafica!");
            e.printStackTrace();
        }
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
            
            MenuPrincipaleCLIController menuCLI = new MenuPrincipaleCLIController();
            menuCLI.avvia(scanner); 
            
        } else if (scelta.equals("2")) {
            System.out.println("\n--- Avvio in modalità Grafica ---");
            System.out.println("Guarda la finestra che si sta aprendo!");
            
            launch(args);
            
        } else {
            System.out.println("\nScelta non valida. Il programma verrà chiuso.");
        }
        if (!scelta.equals("1")) {
             scanner.close();
        }
    }
}