package it.tiriguarda.dao.mem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import it.tiriguarda.dao.TestDAO;
import it.tiriguarda.domain.Test;

public class TestDAOMem implements TestDAO {
	
private static List<Test> testInMemoria = Storage.getInstance().getTest();
	
	@Override
	public void salvaTest (Test test) {
		testInMemoria.add(test);
	}
	
	@Override
	public List<Test> riepilogoTest(String utente, LocalDate data) {
	    List<Test> test = new ArrayList<>();
	    for (Test t : testInMemoria) {
	        boolean stessoUtente = t.getUtente() != null && t.getUtente().equals(utente);
	        boolean dataValida = t.getData() != null && !t.getData().isBefore(data);
	        
	        if (stessoUtente && dataValida) {
	            test.add(t);
	        }
	    }
	    return test;
	}

}
