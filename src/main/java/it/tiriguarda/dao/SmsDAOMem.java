package it.tiriguarda.dao;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import it.tiriguarda.domain.Sms;
import it.tiriguarda.domain.StatoSms;
import it.tiriguarda.domain.TipoSms;

public class SmsDAOMem implements SmsDAO {
	
	private static List<Sms> smsInMemoria = new ArrayList<>();

	@Override
	public void salvaSms(Sms sms) {
		smsInMemoria.add(sms);
	}

	@Override
	public void eliminaSmsProgrammati(String username, TipoSms tipoSms) {
		smsInMemoria.removeIf(sms -> 
			sms.getUtente().equals(username) && sms.getTipo() == tipoSms
		);
	}

	@Override
	public List<Sms> recuperaSmsDaInviare() {

		List<Sms> smsDaInviare = new ArrayList<>();
		LocalDateTime oraAttuale = LocalDateTime.now(ZoneId.systemDefault());
		
		for (Sms sms : smsInMemoria) {
			if (sms.getDataSpedizione().isBefore(oraAttuale) && sms.getStato() == StatoSms.DA_INVIARE) {
				smsDaInviare.add(sms);
			}
		}
		
		return smsDaInviare;
	}

	@Override
	public void aggiornaStato(Sms sms, StatoSms nuovoStato) {
		for (Sms s : smsInMemoria) {
			if (s.getIdSms().equals(sms.getIdSms())) {
				s.setStato(nuovoStato);
				break;
			}
		}
	}
	
	@Override
	public void aggiornaData(Sms sms) {
		for (Sms s : smsInMemoria) {
			if (s.getIdSms().equals(sms.getIdSms())) {
				LocalDateTime nuovaData = sms.getDataSpedizione().plusDays(1);
				s.setDataSpedizione(nuovaData);
				break;
			}
		}
	}
}