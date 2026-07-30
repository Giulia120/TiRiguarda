package it.tiriguarda.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import it.tiriguarda.domain.Utente;

public class UtenteDAOMem implements UtenteDAO {
    private static final Logger logger = Logger.getLogger(UtenteDAOMem.class.getName());
    private static List<Utente> utentiInMemoria = new ArrayList<>();
    
    @Override
    public void registraUtente(Utente utente) {
        utentiInMemoria.add(utente);
    }
    
    @Override
    public Utente trovaPerUsername(String username) {
        logger.info(() ->"Ricerca utente in memoria per username: " + username);
        for (Utente u : utentiInMemoria) {
            if (u.getUsername().equals(username)) {
                Utente utente = new Utente(u.getUsername(), u.getPassword(), u.getSessoBiologico(), u.getNumeroTelefono());
                if (u.getProtocolloAttivo() != null) {
                    utente.setProtocolloAttivo(u.getProtocolloAttivo());
                }
                return utente;
            }
        }
        logger.warning(() ->"Nessun utente trovato in memoria con username: " + username);
        return null;
    }
    
    @Override
    public void eliminaProtocolloAttivo(Utente utente) {
        for (Utente u : utentiInMemoria) {
            if (u.getUsername().equals(utente.getUsername())) {
                u.setProtocolloAttivo(null);
                return;
            }
        }
        logger.warning("Impossibile eliminare protocollo: utente non trovato in memoria (" + utente.getUsername() + ")");
    }
    
    @Override
    public void aggiornaPwdUtente(Utente utente) {
        for (Utente u : utentiInMemoria) {
            if (u.getUsername().equals(utente.getUsername())) {
                u.setPassword(utente.getPassword());
                return;
            }
        }
        logger.warning("Impossibile aggiornare password: utente non trovato in memoria (" + utente.getUsername() + ")");
    }
    
    @Override
    public void aggiornaTelUtente(Utente utente) {
        for (Utente u : utentiInMemoria) {
            if (u.getUsername().equals(utente.getUsername())) {
                u.setNumeroTelefono(utente.getNumeroTelefono());
                return;
            }
        }
        logger.warning("Impossibile aggiornare telefono: utente non trovato in memoria (" + utente.getUsername() + ")");
    }
    
    @Override
    public String recuperaNumeroTelefono(String username) {
        for (Utente u : utentiInMemoria) {
            if (u.getUsername().equals(username)) {
                return u.getNumeroTelefono();
            }
        }
        logger.warning(() ->"Impossibile recuperare telefono: utente non trovato in memoria ( " + username + " )");
        return null;
    }
    
    @Override
    public void aggiornaProtocolloAttivo(Utente utente) {
        for (Utente u : utentiInMemoria) {
            if (u.getUsername().equals(utente.getUsername())) {
                u.setProtocolloAttivo(utente.getProtocolloAttivo());
                return;
            }
        }
        logger.warning("Impossibile aggiornare protocollo: utente non trovato in memoria (" + utente.getUsername() + ")");
    }
}