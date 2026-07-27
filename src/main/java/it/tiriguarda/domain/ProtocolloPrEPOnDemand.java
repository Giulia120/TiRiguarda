package it.tiriguarda.domain;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class ProtocolloPrEPOnDemand extends ProtocolloPrEP{
	public ProtocolloPrEPOnDemand(String idProtocollo, Utente utente, LocalDate dataInizio) {
        super(idProtocollo, utente, TipologiaPrEP.ON_DEMAND, dataInizio);
    }
	
    @Override
    public List<LocalDate> calcolaGiorniPromemoria(LocalDate dataInizio){
    	List<LocalDate> promemoria = new ArrayList<>();
    	Utente utente = getUtente();
    	SessoBiologico sessoBiologico = utente.getSessoBiologico();
    	if(sessoBiologico == SessoBiologico.MASCHILE) {
    		promemoria.add(dataInizio.plusDays(1));
        	promemoria.add(dataInizio.plusDays(2));
        	return promemoria;
    	}
    	else {
    		for(int i = 1; i <= 7; i++) {
    			promemoria.add(dataInizio.plusDays(i));
    		}
    		return promemoria;
    	}
    }
    
    public boolean verificaScadenza() {
    	LocalDate oggi = LocalDate.now(ZoneId.systemDefault());
    	
    	if(oggi.isAfter(getDataFine())) {
    		return true;
    	}
    	return false;
    }
    
    public void aggiornaDataFine(LocalDate dataInizio) {
    	Utente utente = getUtente();
    	SessoBiologico sessoBiologico = utente.getSessoBiologico();
    	if(sessoBiologico == SessoBiologico.MASCHILE) {
        	setDataFine(dataInizio.plusDays(2));
    	}
    	else {
    		setDataFine(dataInizio.plusDays(7));
    		}
    }
   
    public List<LocalDate> aggiornaPrEPOnDemand(LocalDate dataRapporto) {
    	aggiornaDataFine(dataRapporto);
    	return calcolaGiorniPromemoria(dataRapporto);
    }
}
