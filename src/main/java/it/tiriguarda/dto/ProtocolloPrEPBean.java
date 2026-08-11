package it.tiriguarda.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import it.tiriguarda.domain.TipologiaPrEP;
import it.tiriguarda.exception.DatiIncompletiException;

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
		controllaData(dataInizio);
		this.dataInizio = dataInizio;
	}
	public LocalTime getOrario() {
		controllaOra(orario);
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
	private void controllaData(LocalDate data) {
		if (data == null) {
			throw new DatiIncompletiException("Devi inserire la data!");
		}
	}
	
	private void controllaOra(LocalTime ora) {
		if (ora == null) {
			throw new DatiIncompletiException("Devi inserire l'ora!");
			}
	}
}
