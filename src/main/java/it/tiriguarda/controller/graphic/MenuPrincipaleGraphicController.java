package it.tiriguarda.controller.graphic;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MenuPrincipaleGraphicController {
	
	@FXML private Button TestButton;
	@FXML private Button ProfiloButton;
	@FXML private Button RiepilogoButton;
	@FXML private Button RegistraRapportoButton;
	@FXML private Button PrEPButton;
	@FXML private Button QuestionarioButton;
	@FXML private Button InfoButton;
	@FXML private Button LougoutButton;

	private void cambiaSchermata(ActionEvent event, String fxmlPath) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
			Parent nuovaVista = loader.load();
			Stage finestra = (Stage) ((Node) event.getSource()).getScene().getWindow();
			
			finestra.setScene(new Scene(nuovaVista));
			finestra.show();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Errore nel caricamento della schermata: " + fxmlPath);
		}
	}
	
	@FXML
	private void onTestButton(ActionEvent event) {
		cambiaSchermata(event, "/it/tiriguarda/view/Test.fxml");
	}
	
	@FXML
	private void onProfiloButton(ActionEvent event) {
		cambiaSchermata(event, "/it/tiriguarda/view/Profilo.fxml");
	}
	
	@FXML
	private void onRiepilogoButton(ActionEvent event) {
		cambiaSchermata(event, "/it/tiriguarda/view/Riepilogo.fxml");
	}
	
	@FXML
	private void onRegistraRapportoButton(ActionEvent event) {
		cambiaSchermata(event, "/it/tiriguarda/view/RegistraRapporto.fxml");
	}
	
	@FXML
	private void onPrEPButton(ActionEvent event) {
		cambiaSchermata(event, "/it/tiriguarda/view/SceltaPrEP.fxml");
	}
	
	@FXML
	private void onInfoButton(ActionEvent event) {
		cambiaSchermata(event, "/it/tiriguarda/view/Info.fxml");
	}
	
	@FXML
	private void onLogoutButton(ActionEvent event) {
		cambiaSchermata(event, "/it/tiriguarda/view/Login.fxml");
	}
	
	@FXML
	private void onQuestionarioButton(ActionEvent event) {
		cambiaSchermata(event,"/it/tiriguarda/view/Questionario.fxml");
	}
}