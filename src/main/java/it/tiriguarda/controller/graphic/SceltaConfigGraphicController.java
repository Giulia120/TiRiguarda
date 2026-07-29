package it.tiriguarda.controller.graphic;

import it.tiriguarda.config.AppConfig;
import it.tiriguarda.config.AppMode;
import it.tiriguarda.manager.SmsScheduler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class SceltaConfigGraphicController {
	
	@FXML
	private Button demoButton;
	@FXML
	private Button fullButton;
	@FXML
	public void onDemoButton(ActionEvent event) {
		AppConfig.setCurrentMode(AppMode.DEMO);
		SmsScheduler.getInstance().avviaScheduler();
		ViewDispatcher.mostraLogin();
	}
	
	@FXML
	public void onFullButton(ActionEvent event) {
		AppConfig.setCurrentMode(AppMode.FULL);
		SmsScheduler.getInstance().avviaScheduler();
		ViewDispatcher.mostraLogin();
	}	
}
