package it.tiriguarda.dto;

import it.tiriguarda.domain.SessoBiologico;

public class DatiProfiloBean {
	private String username;
	private String numTelefono;
	private SessoBiologico sesso;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getNumTelefono() {
		return numTelefono;
	}
	public void setNumTelefono(String numTelefono) {
		this.numTelefono = numTelefono;
	}
	public SessoBiologico getSesso() {
		return sesso;
	}
	public void setSesso(SessoBiologico sesso) {
		this.sesso = sesso;
	} 
	

}
