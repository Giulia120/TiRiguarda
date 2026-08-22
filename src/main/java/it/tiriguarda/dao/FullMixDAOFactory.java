package it.tiriguarda.dao;

import it.tiriguarda.dao.fs.TestDAOFS;

public class FullMixDAOFactory extends FullDAOFactory {
	@Override
	public TestDAO createTestDAO() {
		return new TestDAOFS();
	}

}
