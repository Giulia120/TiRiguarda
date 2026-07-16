package it.TiRiguarda.domain;

import java.util.List;

public class Utente {
	private final String idUtente;
	private final String username;
	private final String numeroTelefono;
	private List<Rapporto> rapporti;
	private ProtocolloPrEP protocolloAttivo;
	
	public Utente (String idUtente, String username, String numeroTelefono) {
		this.idUtente = idUtente;
		this.username = username;
		this.numeroTelefono = numeroTelefono;
	}
	
	
	public List<Rapporto> getRapporti() {
		return rapporti;
	}


	public void setRapporti(List<Rapporto> rapporti) {
		this.rapporti = rapporti;
	}


	public String getIdUtente() {
		return idUtente;
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
