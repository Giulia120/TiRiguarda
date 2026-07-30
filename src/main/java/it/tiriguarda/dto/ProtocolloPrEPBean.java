package it.tiriguarda.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;

import it.tiriguarda.domain.TipologiaPrEP;
import it.tiriguarda.exception.DataFuturaException;
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
	
	public void setDataInizio(LocalDate dataInizio) throws DatiIncompletiException {
		controllaData(dataInizio);
		this.dataInizio = dataInizio;
	}
	public LocalTime getOrario() throws DatiIncompletiException {
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
	private void controllaData(LocalDate data) throws DatiIncompletiException {
		if (data == null) {
			throw new DatiIncompletiException("Devi inserire la data!");
		}
	}
	
	private void controllaOra(LocalTime ora) throws DatiIncompletiException{
		if (ora == null) {
			throw new DatiIncompletiException("Devi inserire l'ora!");
			}
	}
}
