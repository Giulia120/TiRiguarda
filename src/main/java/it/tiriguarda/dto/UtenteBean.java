package it.tiriguarda.dto;

import it.tiriguarda.domain.SessoBiologico;

public class UtenteBean {
	private String username;
	private String password;
	private SessoBiologico sessoBiologico;
	private String numeroTelefono;

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public SessoBiologico getSessoBiologico() {
		return sessoBiologico;
	}

	public String getNumeroTelefono() {
		return numeroTelefono;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setSessoBiologico(SessoBiologico sessoBiologico) {
		this.sessoBiologico = sessoBiologico;
	}

	public void setNumeroTelefono(String numeroTelefono) {
		this.numeroTelefono = numeroTelefono;
	}	
}
