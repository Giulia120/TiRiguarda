package it.tiriguarda.domain;

import java.time.LocalDate;
import java.time.ZoneId;

public class ProtocolloPrEP {
	private Utente utente;
	private final TipologiaPrEP tipoPrEP;
	private LocalDate dataInizio;
	private boolean statoPrEP;
	private LocalDate dataAnnullamento;
	
	public ProtocolloPrEP(Utente utente, TipologiaPrEP tipoPrEP, LocalDate dataInizio, boolean statoPrEP, LocalDate dataAnnullamento) {
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
	
	public LocalDate getDataInizio() {
		return dataInizio;
	}
	
	public boolean getStatoPrEP() {
		return statoPrEP;
	}
	
	public LocalDate getDataAnnullamento() {
		return dataAnnullamento;
	}
	
	public void setStatoPrEP(boolean statoPrEP) {
		this.statoPrEP = statoPrEP;
	}
	
	public void setDataInizio(LocalDate dataInizio) {
		this.dataInizio = dataInizio;
	}
	
	public void setDataAnnullamento() {
		this.dataAnnullamento = LocalDate.now(ZoneId.systemDefault());
	}
	

}
