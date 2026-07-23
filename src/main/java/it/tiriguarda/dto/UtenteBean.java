package it.tiriguarda.dto;

public class UtenteBean {
	private String username;
	private String password;
	private String numeroTelefono;
	
	public UtenteBean(){
		// Costruttore vuoto necessario per la specifica JavaBean
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getNumeroTelefono() {
		return numeroTelefono;
	}
	
	
}
