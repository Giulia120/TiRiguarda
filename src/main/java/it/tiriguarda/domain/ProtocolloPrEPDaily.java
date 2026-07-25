package it.tiriguarda.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ProtocolloPrEPDaily extends ProtocolloPrEP{
	
	public ProtocolloPrEPDaily(Utente utente, LocalDate dataInizio, LocalDate dataFine) {
        super(utente, TipologiaPrEP.DAILY, dataInizio, null);
    }

    @Override
    public List<LocalDate> calcolaGiorniPromemoria(LocalDate dataInizio) {
    	List<LocalDate> promemoria = new ArrayList<>();

        for(int i = 0; i < 30; i++) {
            LocalDate giorno = getDataInizio().plusDays(i);
            promemoria.add(giorno);
        }
        return promemoria;
    }
}
