package it.tiriguarda.main;

import java.util.Scanner;

import it.tiriguarda.controller.cli.ConfigController;
import it.tiriguarda.controller.cli.LoginCLIController;
import it.tiriguarda.controller.graphic.ViewDispatcher;
import it.tiriguarda.manager.SmsScheduler;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
    	ViewDispatcher.setFinestraPrincipale(primaryStage);
        ViewDispatcher.mostraLogin();
    }
    
    @Override
    public void stop() {
        SmsScheduler.getInstance().arrestaScheduler();
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        ConfigController configurazione = new ConfigController();
        String scelta = configurazione.configuraApp(scanner);
        
        if (scelta.equals("CLI")) {
            System.out.println("\n--- Avvio in modalita' CLI ---\n");
            LoginCLIController loginCLI = new LoginCLIController();
            loginCLI.avviaLogin(scanner); 
            
        } else if (scelta.equals("GUI")) {
            System.out.println("\n--- Avvio in modalita' Grafica ---");
            launch(args);
            }
    }
}