package it.tiriguarda.controller.app;

import it.tiriguarda.domain.Utente;
import it.tiriguarda.dto.DatiProfiloBean;
import it.tiriguarda.service.SessionManager;

public class ProfiloAppController {

    public DatiProfiloBean getDatiProfilo() {
        Utente utente = SessionManager.getInstance().getUtenteLoggato();
        if (utente == null) {
        	throw new IllegalStateException();
        }
        
        DatiProfiloBean bean = new DatiProfiloBean();
        
        bean.setUsername(utente.getUsername());
        bean.setNumTelefono(utente.getNumeroTelefono());
        bean.setSesso(utente.getSessoBiologico());
        
        return bean;
    }
}
