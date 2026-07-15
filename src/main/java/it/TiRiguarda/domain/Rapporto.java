package it.TiRiguarda.domain;

import java.util.Date;

public class Rapporto {
	private Utente utente;
	private final String idRapporto;
	private final Date data;
	private final LivelloRischio rischio;
	private final Date dataFinePeriodoFinestra;
	
	public Rapporto (Utente utente, String idRapporto, Date data, LivelloRischio rischio, Date dataFinePeriodoFinestra) {
		this.utente = utente;
		this.idRapporto = idRapporto;
		this.data = data;
		this.rischio = rischio;
		this.dataFinePeriodoFinestra = dataFinePeriodoFinestra;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public String getIdRapporto() {
		return idRapporto;
	}

	public Date getData() {
		return data;
	}

	public LivelloRischio getRischio() {
		return rischio;
	}

	public Date getDataFinePeriodoFinestra() {
		return dataFinePeriodoFinestra;
	}

	
}
