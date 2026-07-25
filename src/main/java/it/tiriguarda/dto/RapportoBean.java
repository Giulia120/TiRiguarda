package it.tiriguarda.dto;

import java.time.LocalDate;
import java.util.List;

import it.tiriguarda.domain.LivelloRischio;
import it.tiriguarda.domain.Precauzioni;
import it.tiriguarda.domain.TipoRapporto;

public class RapportoBean {
	private LocalDate data;
    private List<TipoRapporto> tipo;
    private Precauzioni precauzioniUsate;
    private LivelloRischio rischio;
	private LocalDate dataFinePeriodoFinestra;

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public List<TipoRapporto> getTipo() {
		return tipo;
	}

	public void setTipo(List<TipoRapporto> tipo) {
		this.tipo = tipo;
	}

	public Precauzioni getPrecauzioniUsate() {
		return precauzioniUsate;
	}

	public void setPrecauzioniUsate(Precauzioni precauzioniUsate) {
		this.precauzioniUsate = precauzioniUsate;
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
    
}
