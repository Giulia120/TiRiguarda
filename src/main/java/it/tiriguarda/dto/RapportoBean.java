package it.tiriguarda.dto;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import it.tiriguarda.domain.LivelloRischio;
import it.tiriguarda.domain.Precauzioni;
import it.tiriguarda.domain.TipoRapporto;
import it.tiriguarda.exception.DataFuturaException;
import it.tiriguarda.exception.DatiIncompletiException;

public class RapportoBean {
	private LocalDate data;
    private List<TipoRapporto> tipo;
    private Precauzioni precauzioniUsate;
    private LivelloRischio rischio;
	private LocalDate dataFinePeriodoFinestra;

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) throws DatiIncompletiException, DataFuturaException {
		controllaData(data);
		this.data = data;
	}

	public List<TipoRapporto> getTipo() {
		return tipo;
	}
	
	public void setTipo(List<TipoRapporto> tipo) {
		controllaTipo(tipo);
		this.tipo = tipo;
	}
	
	private void controllaTipo(List<TipoRapporto> tipo) {
		if(tipo == null || tipo.isEmpty()) {
			throw new DatiIncompletiException("Devi selezionare almeno un tipo di rapporto!");
		}
	}

	public Precauzioni getPrecauzioniUsate() {
		return precauzioniUsate;
	}

	public void setPrecauzioniUsate(Precauzioni precauzioniUsate) {
		controllaPrecauzioni(precauzioniUsate);
		this.precauzioniUsate = precauzioniUsate;
	}
	
	private void controllaPrecauzioni(Precauzioni precauzioniUsate) {
		if (precauzioniUsate == null) {
			throw new DatiIncompletiException("Devi selezionare le precauzioni usate!");
		}
	}

	public LivelloRischio getRischio() {
		return rischio;
	}

	public void setRischio(LivelloRischio rischio) {
		this.rischio = rischio;
	}

	public LocalDate getDataFinePeriodoFinestra() {
		return dataFinePeriodoFinestra;
	}

	public void setDataFinePeriodoFinestra(LocalDate dataFinePeriodoFinestra) {
		this.dataFinePeriodoFinestra = dataFinePeriodoFinestra;
	}
	
	private void controllaData(LocalDate data) throws DatiIncompletiException, DataFuturaException {
		if (data == null) {
			throw new DatiIncompletiException("Devi inserire la data!");
		}
		if (data.isAfter(LocalDate.now(ZoneId.systemDefault()))) {
            throw new DataFuturaException();
        }
	}
    
}
