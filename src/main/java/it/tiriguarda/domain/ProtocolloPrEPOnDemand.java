package it.tiriguarda.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class ProtocolloPrEPOnDemand extends ProtocolloPrEP{
	public ProtocolloPrEPOnDemand(String idProtocollo, String utente, LocalDate dataInizio, boolean statoPrEP, LocalTime ora) {
        super(idProtocollo, utente, TipologiaPrEP.ON_DEMAND, dataInizio, statoPrEP, ora);
    }
	
    @Override
    public List<LocalDateTime> calcolaGiorniPromemoria(LocalDate dataInizio, LocalTime ora, SessoBiologico sesso){
    	List<LocalDateTime> promemoria = new ArrayList<>();
    	String utente = getUtente();
   
    	if(sesso == SessoBiologico.MASCHILE) {
    		promemoria.add(LocalDateTime.of(dataInizio, ora));
    		promemoria.add(LocalDateTime.of(dataInizio.plusDays(1), ora));
        	promemoria.add(LocalDateTime.of(dataInizio.plusDays(2), ora));
        	return promemoria;
    	}
    	else {
    		for(int i = 0; i <= 7; i++) {
    			promemoria.add(LocalDateTime.of(dataInizio.plusDays(i), ora));
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
   
   /* public List<LocalDate> aggiornaPrEPOnDemand(LocalDate dataRapporto, SessoBiologico sesso) {
    	aggiornaDataFine(dataRapporto, sesso);
    	return calcolaGiorniPromemoria(dataRapporto, sesso);
    }*/
}
