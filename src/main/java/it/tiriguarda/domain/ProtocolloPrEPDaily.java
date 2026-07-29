package it.tiriguarda.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ProtocolloPrEPDaily extends ProtocolloPrEP{
	
	public ProtocolloPrEPDaily(String idProtocollo, String utente, LocalDate dataInizio, boolean statoPrEP, LocalTime ora) {
        super(idProtocollo, utente, TipologiaPrEP.DAILY, dataInizio, statoPrEP, ora);
    }

    @Override
    public List<LocalDateTime> calcolaGiorniPromemoria(LocalDate dataInizio, LocalTime ora, SessoBiologico sesso) {
    	List<LocalDateTime> promemoria = new ArrayList<>();
    	promemoria.add(LocalDateTime.of(dataInizio, ora));
        return promemoria;
    }
    
    /*public void ricalcolo() {
    	LocalDate oggi = LocalDate.now(ZoneId.systemDefault());
    	LocalDate fineMese = getDataInizio().plusDays(30);
    	if(oggi.isAfter(fineMese)) {
    		calcolaGiorniPromemoria(fineMese);
    	}
    } */
}
