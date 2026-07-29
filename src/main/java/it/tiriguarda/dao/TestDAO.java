package it.tiriguarda.dao;

import java.util.List;

import it.tiriguarda.domain.Test;
import it.tiriguarda.domain.Utente;

public interface TestDAO {
	void salvaTest(Test test);
	List<Test> riepilogoTest(Utente utente);
}
