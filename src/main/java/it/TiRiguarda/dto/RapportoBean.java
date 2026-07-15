package it.TiRiguarda.dto;

import java.util.Date;
import java.util.List;

import it.TiRiguarda.domain.Precauzioni;
import it.TiRiguarda.domain.TipoRapporto;

public class RapportoBean {
	private Date data;
    private List<TipoRapporto> tipo;
    private Precauzioni precauzioniUsate;
    
    public RapportoBean(){}

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
    
}
