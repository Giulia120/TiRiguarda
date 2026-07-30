package it.tiriguarda.dto;

import java.time.LocalDateTime;

import it.tiriguarda.domain.StatoSms;
import it.tiriguarda.domain.TipoSms;

public class SmsBean {
    private String testo;
    private LocalDateTime dataSpedizione;
    private StatoSms stato;
    private TipoSms tipo;

    public String getTesto() {
        return testo;
    }

    public LocalDateTime getDataSpedizione() {
        return dataSpedizione;
    }

    public StatoSms getStato() {
        return stato;
    }

    public TipoSms getTipo() {
        return tipo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    public void setDataSpedizione(LocalDateTime dataSpedizione) {
        this.dataSpedizione = dataSpedizione;
    }

    public void setStato(StatoSms stato) {
        this.stato = stato;
    }

    public void setTipo(TipoSms tipo) {
        this.tipo = tipo;
    }
}