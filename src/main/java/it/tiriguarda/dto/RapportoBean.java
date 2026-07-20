package it.tiriguarda.dto;

import java.util.Date;
import java.util.List;

import it.tiriguarda.domain.LivelloRischio;
import it.tiriguarda.domain.Precauzioni;
import it.tiriguarda.domain.TipoRapporto;

public class RapportoBean {
	private Date data;
    private List<TipoRapporto> tipo;
    private Precauzioni precauzioniUsate;
    private LivelloRischio rischio;
	private Date dataFinePeriodoFinestra;
    
    public RapportoBean(){
    	// Costruttore vuoto necessario per la specifica JavaBean
    }

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
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

	public Date getDataFinePeriodoFinestra() {
		return dataFinePeriodoFinestra;
	}

	public void setDataFinePeriodoFinestra(Date dataFinePeriodoFinestra) {
		this.dataFinePeriodoFinestra = dataFinePeriodoFinestra;
	}
    
}
