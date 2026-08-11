package it.tiriguarda.dto;

import it.tiriguarda.exception.DatiIncompletiException;

public class CambioPwdBean {
	private String vecchiaPassword;
	private String nuovaPassword;

	public String getVecchiaPassword() {
		return vecchiaPassword;
	}

	public void setVecchiaPassword(String vecchiaPassword) {
		controllaStringa(vecchiaPassword, "Inserisci la vecchia password!");
		this.vecchiaPassword = vecchiaPassword;
	}

	public String getNuovaPassword() {
		return nuovaPassword;
	}

	public void setNuovaPassword(String nuovaPassword) {
		controllaStringa(nuovaPassword, "Inserisci la nuova password!");
		if (nuovaPassword.length() < 6 || nuovaPassword.length() > 128) {
			throw new DatiIncompletiException("La nuova password deve avere almeno 6 caratteri e massimo 128!");
		}
		this.nuovaPassword = nuovaPassword;
	}

	private void controllaStringa(String valore, String messaggio) {
		if (valore == null || valore.isBlank()) {
			throw new DatiIncompletiException(messaggio);
		}
	}
}