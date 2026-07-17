package it.tiriguarda.config;

public class AppConfig {
	
	private AppConfig() {}
	
	private static AppMode currentMode = AppMode.DEMO;

	public static AppMode getCurrentMode() {
		return currentMode;
	}

	public static void setCurrentMode(AppMode currentMode) {
		AppConfig.currentMode = currentMode;
	}

}
