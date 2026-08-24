package it.tiriguarda.dao;

import it.tiriguarda.dao.fs.TestDAOFS;

public class FullFSDAOFactory extends FullDBDAOFactory {
	@Override
	public TestDAO createTestDAO() {
		return new TestDAOFS();
	}

}
