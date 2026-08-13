package it.tiriguarda.dto;

import java.time.LocalDate;

import it.tiriguarda.domain.TipologiaPrEP;
import it.tiriguarda.exception.DatiIncompletiException;

public class OldProcotolloPrEP {
	private TipologiaPrEP tipoPrEP;
	private LocalDate dataInizio;
	private LocalDate dataFine;
	
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
	public LocalDate getDataFine() {
		return dataFine;
	}
	public void setDataFine(LocalDate dataFine) {
		controllaData(dataFine);
		controllaCoerenza (this.dataInizio, dataFine);
		this.dataFine = dataFine;
	}
	
	private void controllaData(LocalDate data) {
		if (data == null) {
			throw new DatiIncompletiException("Devi inserire la data!");
		}
	}
	
	private void controllaCoerenza(LocalDate dataI, LocalDate dataF) {
		if (dataI.isAfter(dataF)) {
			throw new DatiIncompletiException("La data di fine dev'essere postuma alla data di inizio!");
		}
	}
	
}
