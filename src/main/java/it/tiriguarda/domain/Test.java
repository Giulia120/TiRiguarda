package it.tiriguarda.domain;

import java.util.Date;

public class Test {
	private final Utente utente;
	private final TipoTest tipo;
	private final Date data;
	
	
	public Test(Utente utente, TipoTest tipo, Date data) {
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
	public Date getData() {
		return data;
	}
	
	
}
