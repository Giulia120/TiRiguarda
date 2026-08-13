package it.tiriguarda.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public abstract class ProtocolloPrEP {
	private final String idProtocollo;
	private final String utente;
	private final TipologiaPrEP tipoPrEP;
	private LocalDate dataInizio;
	private boolean statoPrEP;
	private LocalDate dataFine;
	private LocalTime ora;
	
	protected ProtocolloPrEP(String idProtocollo, String utente, TipologiaPrEP tipoPrEP, LocalDate dataInizio, boolean statoPrEP, LocalTime ora) {
		this.idProtocollo = idProtocollo;
		this.utente = utente;
		this.tipoPrEP = tipoPrEP;
		this.dataInizio = dataInizio;
		this.statoPrEP = statoPrEP;
		this.dataFine = null;
		this.ora = ora;
	}
	
	protected ProtocolloPrEP(String idProtocollo, String utente, TipologiaPrEP tipoPrEP, LocalDate dataInizio, LocalDate dataFine, boolean statoPrEP) {
	    this.idProtocollo = idProtocollo;
	    this.utente = utente;
	    this.tipoPrEP = tipoPrEP;
	    this.dataInizio = dataInizio;
	    this.statoPrEP = statoPrEP;
	    this.dataFine = dataFine;
	    this.ora = null;
	}
	
	public abstract List<LocalDateTime> calcolaGiorniPromemoria(LocalDate dataInizio, LocalTime ora, SessoBiologico sesso);
	
	public String getIdProtocollo() {
		return idProtocollo;
	}
	
	public String getUtente() {
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

	public LocalTime getOra() {
		return ora;
	}

	public void setOra(LocalTime ora) {
		this.ora = ora;
	}
}
