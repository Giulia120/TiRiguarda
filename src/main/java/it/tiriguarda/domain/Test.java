package it.tiriguarda.domain;

import java.time.LocalDate;

public class Test {
	private final Utente utente;
	private final String idTest;
	private final TipoTest tipo;
	private final LocalDate data;
	
	
	public Test(Utente utente, String idTest, TipoTest tipo, LocalDate data) {
		this.utente = utente;
		this.idTest = idTest;
		this.tipo = tipo;
		this.data = data;
	}
	public Utente getUtente() {
		return utente;
	}
	public String getidTest() {
		return idTest;
	}
	public TipoTest getTipo() {
		return tipo;
	}
	public LocalDate getData() {
		return data;
	}
	
	
}
