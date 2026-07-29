package it.tiriguarda.domain;

import java.util.ArrayList;
import java.util.List;

public class Question {
    
    private final String testo;
    private final List<String> opzioni;
    private final List<Integer> pesi;

    public Question(String testo, List<String> opzioni, List<Integer> pesi) {
        if (opzioni == null || pesi == null || opzioni.size() != pesi.size()) {
            throw new IllegalArgumentException("Le opzioni e i pesi devono avere la stessa lunghezza e non essere nulli.");
        }
        
        this.testo = testo;
        this.opzioni = new ArrayList<>(opzioni);
        this.pesi = new ArrayList<>(pesi);
    }

    public String getTesto() {
        return testo;
    }

    public List<String> getOpzioni() {
        return new ArrayList<>(opzioni);
    }

    public String getTestoOpzione(int index) {
        return opzioni.get(index);
    }

    public int getPesi(int index) {
        return pesi.get(index);
    }
}