package it.tiriguarda.domain;

import it.tiriguarda.util.SecurityUtil;

public class Utente {
	private final String username;
	private String password;
	private final SessoBiologico sessoBiologico;
	private String numeroTelefono;
	private TipologiaPrEP protocolloAttivo;
	
	
	public Utente (String username, String passwordHash, SessoBiologico sessoBiologico, String numeroTelefono) {
		this.username = username;
		this.password = passwordHash;
		this.sessoBiologico = sessoBiologico;
		this.numeroTelefono = numeroTelefono;
	}
	
	public boolean verificaPassword(String passwordInseritaInChiaro) {
        String hashCalcolato = SecurityUtil.hashPassword(passwordInseritaInChiaro);
        return this.password.equals(hashCalcolato);
    } 


	public String getUsername() {
		return username;
	}


	public SessoBiologico getSessoBiologico() {
		return sessoBiologico;
	}

	public String getNumeroTelefono() {
		return numeroTelefono;
	}


	public void setProtocolloAttivo(TipologiaPrEP protocolloAttivo) {
		this.protocolloAttivo = protocolloAttivo;
	}


	public TipologiaPrEP getProtocolloAttivo() {
	    return protocolloAttivo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setNumeroTelefono(String numeroTelefono) {
		this.numeroTelefono = numeroTelefono;
	}
}
