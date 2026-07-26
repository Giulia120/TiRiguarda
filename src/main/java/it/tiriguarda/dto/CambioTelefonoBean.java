package it.tiriguarda.dto;

import it.tiriguarda.exception.DatiIncompletiException;

public class CambioTelefonoBean {
    private String nuovoTelefono;

    public String getNuovoTelefono() {
        return nuovoTelefono;
    }

    public void setNuovoTelefono(String nuovoTelefono) throws DatiIncompletiException {
        controllaTelefono(nuovoTelefono);
        this.nuovoTelefono = nuovoTelefono;
    }

    private void controllaTelefono(String numero) throws DatiIncompletiException {
        if (numero == null || numero.isBlank()) {
            throw new DatiIncompletiException("Devi inserire il nuovo numero di telefono!");
        }
        if (!numero.matches("^\\d{9,11}$")) {
            throw new DatiIncompletiException("Il numero di telefono non è valido (deve contenere solo numeri, tra 9 e 11 cifre)!");
        }
    }
}