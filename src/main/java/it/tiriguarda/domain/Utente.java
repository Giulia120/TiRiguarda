package it.tiriguarda.domain;

import java.util.List;

import it.tiriguarda.util.SecurityUtil;

public class Utente {
	private final String username;
	private final String password;
	private final String numeroTelefono;
	private List<Rapporto> rapporti;
	private ProtocolloPrEP protocolloAttivo;
	
	public Utente (String username, String passwordHash, String numeroTelefono) {
		this.username = username;
		this.password = passwordHash;
		this.numeroTelefono = numeroTelefono;
	}
	
	public boolean verificaPassword(String passwordInseritaInChiaro) {
        String hashCalcolato = SecurityUtil.hashPassword(passwordInseritaInChiaro);
        return this.password.equals(hashCalcolato);
    } 
	
	public List<Rapporto> getRapporti() {
		return rapporti;
	}


	public void setRapporti(List<Rapporto> rapporti) {
		this.rapporti = rapporti;
	}


	public String getUsername() {
		return username;
	}


	public String getNumeroTelefono() {
		return numeroTelefono;
	}


	public void setProtocolloAttivo(ProtocolloPrEP protocolloAttivo) {
		this.protocolloAttivo = protocolloAttivo;
	}


	public ProtocolloPrEP getProtocolloAttivo() {
	    return protocolloAttivo;
	}
}
