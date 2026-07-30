package it.tiriguarda.dao;

public class FullMixDAOFactory extends FullDAOFactory {
	@Override
	public TestDAO createTestDAO() {
		return new TestDAOFS();
	}

}
