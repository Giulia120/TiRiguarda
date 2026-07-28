package it.tiriguarda.domain;

import java.time.LocalDateTime;

public class Sms {
	private final String utente;
	private final String idSms;
    private final String testo;
    private final LocalDateTime dataSpedizione;
    private StatoSms stato; 
    private final TipoSms tipo;
	
    public Sms(String utente, String idSms, String testo, LocalDateTime dataSpedizione, TipoSms tipo) {
		this.utente = utente;
		this.idSms = idSms;
		this.testo = testo;
		this.dataSpedizione = dataSpedizione;
		this.stato = StatoSms.DA_INVIARE;
		this.tipo = tipo;
	}

	public String getUtente() {
		return utente;
	}

	public String getIdSms() {
		return idSms;
	}

	public String getTesto() {
		return testo;
	}

	public LocalDateTime getDataSpedizione() {
		return dataSpedizione;
	}

	public StatoSms getStato() {
		return stato;
	}

	public void setStato(StatoSms stato) {
		this.stato = stato;
	}

	public TipoSms getTipo() {
		return tipo;
	}
    
    
}
