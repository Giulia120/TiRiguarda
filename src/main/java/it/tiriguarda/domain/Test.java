package it.tiriguarda.domain;

import java.time.LocalDate;

public class Test {
	private final Utente utente;
	private final TipoTest tipo;
	private final LocalDate data;
	
	
	public Test(Utente utente, TipoTest tipo, LocalDate data) {
		this.utente = utente;
		this.tipo = tipo;
		this.data = data;
	}
	public Utente getUtente() {
		return utente;
	}
	public TipoTest getTipo() {
		return tipo;
	}
	public LocalDate getData() {
		return data;
	}
	
	
}
