package it.tiriguarda.dao.mem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import it.tiriguarda.domain.ProtocolloPrEP;
import it.tiriguarda.domain.Rapporto;
import it.tiriguarda.domain.Sms;
import it.tiriguarda.domain.Test;
import it.tiriguarda.domain.Utente;

public class Storage {
	protected static Storage instance;
	
	private final List<ProtocolloPrEP> protocolli = Collections.synchronizedList(new ArrayList<>());
	private final List<Rapporto> rapporti = Collections.synchronizedList(new ArrayList<>());
	private final List<Sms> sms = Collections.synchronizedList(new ArrayList<>());
	private final List<Test> test = Collections.synchronizedList(new ArrayList<>());
	private final List<Utente> utenti = Collections.synchronizedList(new ArrayList<>());


	protected Storage() {
    }

	public static synchronized Storage getInstance() {
		if (instance == null) {
			instance = new Storage();
		}
		return instance;
		}
		
	public List<ProtocolloPrEP> getProtocolli() {
		return protocolli;
	}

	public List<Rapporto> getRapporti() {
		return rapporti;
	}
	public List<Sms> getSms() {
		return sms;
	}
	public List<Test> getTest() {
		return test;
	}
	public List<Utente> getUtenti() {
		return utenti;
	}
	
	
}
