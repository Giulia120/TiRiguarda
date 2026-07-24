package it.tiriguarda.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ProtocolloPrEPDaily extends ProtocolloPrEP{
	
	public ProtocolloPrEPDaily(Utente utente, LocalDate dataInizio) {
        super(utente, TipologiaPrEP.DAILY, dataInizio);
    }

    @Override
    public List<LocalDateTime> calcolaGiorniPromemoria(LocalDate dataInizio, LocalTime ora) {
    	List<LocalDateTime> promemoria = new ArrayList<>();

        for(int i = 0; i < 30; i++) {
            LocalDate giorno = getDataInizio().plusDays(i);
            promemoria.add(LocalDateTime.of(giorno, ora));
        }
        return promemoria;
    }
}
