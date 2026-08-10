package it.tiriguarda.dto;
import java.time.LocalDate;

public class EventoRiepilogo {
    private final LocalDate data;
    private final String descrizione;

    public EventoRiepilogo(LocalDate data, String descrizione) {
        this.data = data;
        this.descrizione = descrizione;
    }
    public LocalDate getData() { 
    	return data;
    }
    public String getDescrizione() {
    	return descrizione;
    }
}