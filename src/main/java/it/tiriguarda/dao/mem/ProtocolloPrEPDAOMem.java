package it.tiriguarda.dao.mem;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import it.tiriguarda.dao.ProtocolloPrEPDAO;
import it.tiriguarda.domain.ProtocolloPrEP;

public class ProtocolloPrEPDAOMem implements ProtocolloPrEPDAO{
	
	private static List<ProtocolloPrEP> protocolliInMemoria = Storage.getInstance().getProtocolli();
	
	@Override
	public ProtocolloPrEP trovaProtocolloAttivo(String username) {
		for (ProtocolloPrEP p : protocolliInMemoria) {
			if (p.getUtente().equals(username)) {
				return p;
			}
		}
		return null;
	}
	@Override
	public void configuraProtocollo(ProtocolloPrEP protocolloPrEP) {
		protocolliInMemoria.add(protocolloPrEP);
	}
	
	@Override
	public void aggiornaProtocollo(ProtocolloPrEP protocolloPrEP) {
		int index = -1;
	  
	    for (int i = 0; i < protocolliInMemoria.size(); i++) {
	        ProtocolloPrEP p = protocolliInMemoria.get(i);
	        if (p.getStatoPrEP()) {
	            index = i;
	            break; 
	        }
	    }
	    protocolliInMemoria.set(index, protocolloPrEP);
	}
	
	
	@Override
	public void annullaStatoProtocollo(ProtocolloPrEP protocolloPrEP) {
		protocolloPrEP.setStatoPrEP(false);
		protocolloPrEP.setDataFine(LocalDate.now(ZoneId.systemDefault()));
	}
	
	@Override
    public List<ProtocolloPrEP> riepilogoPrEP(String utente, LocalDate data) {
		List<ProtocolloPrEP> protocolli = new ArrayList<>();
	    for (ProtocolloPrEP p : protocolliInMemoria) {
	    	boolean stessoUtente = p.getUtente() != null && p.getUtente().equals(utente);
            boolean dataValida = p.getDataInizio().isBefore(data);
	        if (stessoUtente && dataValida) {
	            protocolli.add(p);
	        }
	    }
	    return protocolli;
    }
	
	@Override
	public boolean esisteProtocollo(String utente, LocalDate data) {
		return esisteProtocollo(utente, data, true);
	}
	
	@Override
	public boolean esisteProtocollo(String utente, LocalDate data, boolean soloAttivi) {
	    for (ProtocolloPrEP p : protocolliInMemoria) {
	        boolean stessoUtente = p.getUtente() != null && p.getUtente().equals(utente);
	        boolean statoValido = !soloAttivi || p.getStatoPrEP();
	        boolean dataInizioValida = p.getDataInizio().isBefore(data);
	        boolean dataFineValida = p.getDataFine() == null || !p.getDataFine().isBefore(data);
	        if (stessoUtente && statoValido && dataInizioValida && dataFineValida) {
	            return true;
	        }
	    }
	    return false;
	}
}
