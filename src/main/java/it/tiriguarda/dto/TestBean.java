package it.tiriguarda.dto;

import java.util.Date;

import it.tiriguarda.domain.TipoTest;
import it.tiriguarda.domain.Utente;

public class TestBean {
	private Utente utente;
	private TipoTest tipo;
	private Date data;
	public Utente getUtente() {
		return utente;
	}
	public void setUtente(Utente utente) {
		this.utente = utente;
	}
	public TipoTest getTipo() {
		return tipo;
	}
	public void setTipo(TipoTest tipo) {
		this.tipo = tipo;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	
	
}
