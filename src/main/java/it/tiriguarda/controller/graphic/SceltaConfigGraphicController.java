package it.tiriguarda.controller.graphic;

import java.io.IOException;

import it.tiriguarda.config.AppConfig;
import it.tiriguarda.config.AppMode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class SceltaConfigGraphicController {
	
	@FXML
	private Button demoButton;
	@FXML
	private Button fullButton;
	@FXML
	public void onDemoButton(ActionEvent event) throws IOException {
		AppConfig.setCurrentMode(AppMode.DEMO);
		ViewDispatcher.mostraLogin();
	}
	
	@FXML
	public void onFullButton(ActionEvent event) throws IOException {
		AppConfig.setCurrentMode(AppMode.FULL);
		ViewDispatcher.mostraLogin();
	}	
}
