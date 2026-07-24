package it.tiriguarda.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import it.tiriguarda.domain.Test;

public class TestDAOMem implements TestDAO {
	private static final Logger logger = Logger.getLogger(TestDAOMem.class.getName());
	
private static List<Test> testInMemoria = new ArrayList<>();
	
	@Override
	public void salvaTest (Test test) {
		testInMemoria.add(test);
		logger.info("Test salvato correttamente");
	}

}
