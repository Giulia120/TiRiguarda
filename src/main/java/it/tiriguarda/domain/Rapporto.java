package it.tiriguarda.domain;

import java.time.LocalDate;

public class Rapporto {
	private final String utente;
	private final String idRapporto;
	private final LocalDate data;
	private final LivelloRischio rischio;
	private final LocalDate dataFinePeriodoFinestra;
	
	public Rapporto (String utente, String idRapporto, LocalDate data, LivelloRischio rischio) {
		this.utente = utente;
		this.idRapporto = idRapporto;
		this.data = data;
		this.rischio = rischio;
		this.dataFinePeriodoFinestra = calcolaPeriodoFinestra(data, rischio);
	}
	
	private LocalDate calcolaPeriodoFinestra(LocalDate data, LivelloRischio rischio) {
		if (rischio == LivelloRischio.NULLO) {
			return null;
		}
		return data.plusDays(28);
	}

	public String getUtente() {
		return utente;
	}

	public String getIdRapporto() {
		return idRapporto;
	}

	public LocalDate getData() {
		return data;
	}

	public LivelloRischio getRischio() {
		return rischio;
	}

	public LocalDate getDataFinePeriodoFinestra() {
		return dataFinePeriodoFinestra;
	}

	
}
