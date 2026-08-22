package it.tiriguarda.dao.mem;

import java.util.List;

import it.tiriguarda.dao.LoginDAO;
import it.tiriguarda.domain.Utente;
import it.tiriguarda.dto.CredenzialiBean;
import it.tiriguarda.exception.CredenzialiErrateException;

public class LoginDAOMem implements LoginDAO {
	
	private static List<Utente> utentiInMemoria = Storage.getInstance().getUtenti();

	@Override
	public Utente effetuaLogin(CredenzialiBean bean) {
		for (Utente u : utentiInMemoria) {
            if (u.getUsername().equals(bean.getUsername()) && u.getPassword().equals(bean.getPassword())) {
                Utente utenteTrovato = new Utente(u.getUsername(), u.getPassword(), u.getSessoBiologico(), u.getNumeroTelefono());
                if (u.getProtocolloAttivo() != null) {
                    utenteTrovato.setProtocolloAttivo(u.getProtocolloAttivo());
                }
                return utenteTrovato;
                }
            }
		throw new CredenzialiErrateException();
		
	}
}
