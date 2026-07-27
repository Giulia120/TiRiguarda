package it.tiriguarda.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;

public abstract class ProtocolloPrEP {
	private final String idProtocollo;
	private Utente utente;
	private final TipologiaPrEP tipoPrEP;
	private LocalDate dataInizio;
	private boolean statoPrEP;
	private LocalDate dataFine;
	
	protected ProtocolloPrEP(String idProtocollo, Utente utente, TipologiaPrEP tipoPrEP, LocalDate dataInizio) {
		this.idProtocollo = idProtocollo;
		this.utente = utente;
		this.tipoPrEP = tipoPrEP;
		this.dataInizio = dataInizio;
		this.statoPrEP = true;
		this.dataFine = null;
	}
	
	public abstract List<LocalDate> calcolaGiorniPromemoria(LocalDate dataInizio);
	
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
	
	public LocalDate getDataFine() {
		return dataFine;
	}
	
	public void setStatoPrEP(boolean statoPrEP) {
		this.statoPrEP = statoPrEP;
	}
	
	public void setDataInizio(LocalDate dataInizio) {
		this.dataInizio = dataInizio;
	}
	
	public void setDataFine(LocalDate dataFine) {
		this.dataFine = dataFine;
	}
}
