package it.tiriguarda.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class ProtocolloPrEPOnDemand extends ProtocolloPrEP{
	public ProtocolloPrEPOnDemand(Utente utente, LocalDate dataInizio) {
        super(utente, TipologiaPrEP.ON_DEMAND, dataInizio);
    }

    @Override
    public List<LocalDateTime> calcolaGiorniPromemoria(LocalDate dataInizio, LocalTime ora) {
    	List<LocalDateTime> promemoria = new ArrayList<>();
    	
    	promemoria.add(LocalDateTime.of(dataInizio.plusDays(1), ora));
    	promemoria.add(LocalDateTime.of(dataInizio.plusDays(2), ora));
    	  
        return promemoria;
    }
    
    public void verificaScadenza() {
    	LocalDate oggi = LocalDate.now(ZoneId.systemDefault());
    	
    	if(oggi.isAfter(getDataInizio().plusDays(2))) {
    		setStatoPrEP(false);
    		setDataAnnullamento();
    	}
    }
}
