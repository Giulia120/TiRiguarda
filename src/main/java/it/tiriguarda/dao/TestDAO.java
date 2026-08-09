package it.tiriguarda.dao;

import java.time.LocalDate;
import java.util.List;

import it.tiriguarda.domain.Test;

public interface TestDAO {
	void salvaTest(Test test);
	List<Test> riepilogoTest(String utente, LocalDate data);
}
