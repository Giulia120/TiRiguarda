package it.tiriguarda.dto;

import it.tiriguarda.exception.DatiIncompletiException;

public class CredenzialiBean {
	private String username;
	private String password;
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		controllaStringa(username, "Inserisci l'username!");
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		controllaStringa(password, "Inserisci la password!");
		this.password = password;
	}
	
	private void controllaStringa(String valore, String messaggioErrore) {
		if (valore == null || valore.isBlank()) {
			throw new DatiIncompletiException(messaggioErrore);
		}
	}
	

}
