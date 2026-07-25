package it.tiriguarda.dto;

import java.time.LocalDate;

import it.tiriguarda.domain.TipoTest;
import it.tiriguarda.domain.Utente;

public class TestBean {
	private Utente utente;
	private TipoTest tipo;
	private LocalDate data;
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
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	
	
}
