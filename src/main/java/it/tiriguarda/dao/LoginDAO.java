package it.tiriguarda.dao;

import it.tiriguarda.domain.Utente;
import it.tiriguarda.dto.CredenzialiBean;

public interface LoginDAO {
	public Utente effetuaLogin(CredenzialiBean bean);
}
