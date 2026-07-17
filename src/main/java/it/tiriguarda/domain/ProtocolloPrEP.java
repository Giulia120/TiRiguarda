package it.tiriguarda.domain;

import java.util.Date;

public class ProtocolloPrEP {
	private Utente utente;
	private final TipologiaPrEP tipoPrEP;
	private final Date dataInizio;
	private boolean statoPrEP;
	private final Date dataAnnullamento;
	
	public ProtocolloPrEP(Utente utente, TipologiaPrEP tipoPrEP, Date dataInizio, boolean statoPrEP, Date dataAnnullamento) {
		this.utente = utente;
		this.tipoPrEP = tipoPrEP;
		this.dataInizio = dataInizio;
		this.statoPrEP = statoPrEP;
		this.dataAnnullamento = dataAnnullamento;
	}
	
	public Utente getUtente() {
		return utente;
	}
	
	public TipologiaPrEP getTipoPrEP() {
		return tipoPrEP;
	}
	
	public boolean getStatoPrEP() {
		return statoPrEP;
	}
	
	
}
