package it.tiriguarda.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import it.tiriguarda.domain.TipologiaPrEP;

public class ProtocolloPrEPBean {
	private TipologiaPrEP tipoPrEP;
	private LocalDate dataInizio;
	private LocalTime orario;
	private boolean ricevereSMS;
	
	public TipologiaPrEP getTipoPrEP() {
		return tipoPrEP;
	}
	public void setTipoPrEP(TipologiaPrEP tipoPrEP) {
		this.tipoPrEP = tipoPrEP;
	}
	public LocalDate getDataInizio() {
		return dataInizio;
	}
	public void setDataInizio(LocalDate dataInizio) {
		this.dataInizio = dataInizio;
	}
	public LocalTime getOrario() {
		return orario;
	}
	public void setOrario(LocalTime orario) {
		this.orario = orario;
	}
	public boolean getRicevereSMS() {
		return ricevereSMS;
	}
	public void setRicevereSMS(boolean ricevereSMS) {
		this.ricevereSMS = ricevereSMS;
	}
}
