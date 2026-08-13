package it.tiriguarda.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import it.tiriguarda.exception.DatiIncompletiException;

public class ProtocolloPrEPOnDemand extends ProtocolloPrEP{
	public ProtocolloPrEPOnDemand(String idProtocollo, String utente, LocalDate dataInizio, boolean statoPrEP, LocalTime ora) {
        super(idProtocollo, utente, TipologiaPrEP.ON_DEMAND, dataInizio, statoPrEP, ora);
    }
	
	public ProtocolloPrEPOnDemand(String idProtocollo, String utente, LocalDate dataInizio, LocalDate dataFine, boolean statoPrEP) {
	    super(idProtocollo, utente, TipologiaPrEP.ON_DEMAND, dataInizio, dataFine, statoPrEP);
	}
	
    @Override
    public List<LocalDateTime> calcolaGiorniPromemoria(LocalDate dataInizio, LocalTime ora, SessoBiologico sesso){
    	List<LocalDateTime> promemoria = new ArrayList<>();
    	
    	LocalDateTime adesso = LocalDateTime.now(ZoneId.systemDefault());
    	
    	int giorniTot = (sesso == SessoBiologico.MASCHILE) ? 2 : 7;
   
   		for(int i = 0; i <= giorniTot; i++) {
   			LocalDateTime dataPromemoria = LocalDateTime.of(dataInizio.plusDays(i), ora);
   			if (dataPromemoria.isAfter(adesso)) {
   				promemoria.add(dataPromemoria);
   			}
   		}
    		return promemoria;
    }
    
    public boolean verificaScadenza() {
    	LocalDate oggi = LocalDate.now(ZoneId.systemDefault());
    	return oggi.isAfter(getDataFine());
    }
    
    public void aggiornaDataFine(LocalDate dataInizio, SessoBiologico sesso) {
    	if(dataInizio == null) {
    		throw new DatiIncompletiException("Devi inserire la data!");
    	}
    	if(sesso == SessoBiologico.MASCHILE) {
        	setDataFine(dataInizio.plusDays(2));
    	}
    	else {
    		setDataFine(dataInizio.plusDays(7));
    		}
    }
}
