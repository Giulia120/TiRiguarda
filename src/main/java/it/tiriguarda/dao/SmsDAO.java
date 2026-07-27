package it.tiriguarda.dao;

import java.util.List;

import it.tiriguarda.domain.Sms;
import it.tiriguarda.domain.StatoSms;
import it.tiriguarda.domain.TipoSms;

public interface SmsDAO {
	public void salvaSms(Sms sms);
	public void eliminaSmsProgrammati(String username, TipoSms tipoSms);
	public List<Sms> recuperaSmsDaInviare();
    public void aggiornaStato(Sms sms, StatoSms nuovoStato);
    public void aggiornaData(Sms sms);
}
