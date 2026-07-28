package it.tiriguarda.domain;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class ProtocolloPrEPOnDemand extends ProtocolloPrEP{
	public ProtocolloPrEPOnDemand(String idProtocollo, String utente, LocalDate dataInizio, boolean statoPrEP) {
        super(idProtocollo, utente, TipologiaPrEP.ON_DEMAND, dataInizio, statoPrEP);
    }
	
    @Override
    public List<LocalDate> calcolaGiorniPromemoria(LocalDate dataInizio, SessoBiologico sesso){
    	List<LocalDate> promemoria = new ArrayList<>();
    	String utente = getUtente();
    	if(sesso == SessoBiologico.MASCHILE) {
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
    
    public void aggiornaDataFine(LocalDate dataInizio, SessoBiologico sesso) {
    	if(sesso == SessoBiologico.MASCHILE) {
        	setDataFine(dataInizio.plusDays(2));
    	}
    	else {
    		setDataFine(dataInizio.plusDays(7));
    		}
    }
   
    public List<LocalDate> aggiornaPrEPOnDemand(LocalDate dataRapporto, SessoBiologico sesso) {
    	aggiornaDataFine(dataRapporto, sesso);
    	return calcolaGiorniPromemoria(dataRapporto, sesso);
    }
}
