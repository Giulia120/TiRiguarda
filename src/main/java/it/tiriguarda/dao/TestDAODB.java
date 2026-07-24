package it.tiriguarda.dao;

import it.tiriguarda.domain.Test;

public class TestDAODB implements TestDAO {
	
	@Override
	public void salvaTest(Test test) {
		System.out.println("FAKE: Rapporto salvato DB");
	}

}
