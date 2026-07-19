package it.tiriguarda.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {

            Parent root = FXMLLoader.load(getClass().getResource("/it/tiriguarda/view/RegistraRapportoGUI.fxml"));
            
            Scene scene = new Scene(root);
            
            primaryStage.setTitle("Ti Riguarda - Registra Rapporto (Test)");
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
            
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("Errore nel caricamento del file FXML. Controlla il percorso!");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}