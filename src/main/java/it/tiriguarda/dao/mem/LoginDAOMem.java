package it.tiriguarda.dao.mem;

import it.tiriguarda.dao.LoginDAO;
import it.tiriguarda.domain.Utente;
import it.tiriguarda.dto.CredenzialiBean;

public class LoginDAOMem implements LoginDAO {
	@Override
	public Utente effetuaLogin(CredenzialiBean bean) {
		for (Utente u : utentiInMemoria) {
            if (u.getUsername().equals(username)) {
                Utente utente = new Utente(u.getUsername(), u.getPassword(), u.getSessoBiologico(), u.getNumeroTelefono());
                if (u.getProtocolloAttivo() != null) {
                    utente.setProtocolloAttivo(u.getProtocolloAttivo());
                }
                return utente;
		
	}
}
