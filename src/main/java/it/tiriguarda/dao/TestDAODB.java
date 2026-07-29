package it.tiriguarda.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import it.tiriguarda.domain.Test;
import it.tiriguarda.exception.DatabaseNonRaggiungibileException;

public class TestDAODB implements TestDAO {
	
	@Override
	public void salvaTest(Test test) {
		String sql = "insert into `Test` (`utente`, `idTest`, `tipoTest`,`data`) values (?,?,?,?)";
		try (Connection conn = ConnectionFactory.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);) {
			ps.setString(1, test.getUtente());
			ps.setString(2, test.getidTest());
			ps.setString(3, test.getTipo().name());
			ps.setDate(4, java.sql.Date.valueOf(test.getData()));
			
			ps.executeUpdate();
			
			}catch(SQLException e) {
				throw new DatabaseNonRaggiungibileException("Impossibile contattare il server.");
			}
	}

}
