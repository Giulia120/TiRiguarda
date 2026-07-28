package it.tiriguarda.domain;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class ProtocolloPrEPDaily extends ProtocolloPrEP{
	
	public ProtocolloPrEPDaily(String idProtocollo, String utente, LocalDate dataInizio) {
        super(idProtocollo, utente, TipologiaPrEP.DAILY, dataInizio);
    }

    @Override
    public List<LocalDate> calcolaGiorniPromemoria(LocalDate dataInizio, SessoBiologico sesso) {
    	List<LocalDate> promemoria = new ArrayList<>();

        for(int i = 0; i < 30; i++) {
            LocalDate giorno = getDataInizio().plusDays(i);
            promemoria.add(giorno);
        }
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
