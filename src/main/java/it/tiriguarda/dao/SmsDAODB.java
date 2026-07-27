package it.tiriguarda.dao;

import java.util.List;

import it.tiriguarda.domain.Sms;
import it.tiriguarda.domain.StatoSms;
import it.tiriguarda.domain.TipoSms;

public class SmsDAODB implements SmsDAO {
	@Override
	public void salvaSms(Sms sms) {}
	@Override
	public void eliminaSmsProgrammati(String username, TipoSms tipoSms) {}
	@Override
	public List<Sms> recuperaSmsDaInviare(){}
	@Override
    public void aggiornaStato(Sms sms, StatoSms nuovoStato) {}
	@Override
	public void aggiornaData(Sms sms) {}

}
