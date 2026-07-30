package it.tiriguarda.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import it.tiriguarda.domain.Test;
import it.tiriguarda.domain.TipoTest;
import it.tiriguarda.domain.Utente;
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
	
	@Override
	public List<Test> riepilogoTest(Utente utente, LocalDate data) {
	    String sql = "select * from `Test` where `utente` = ? and `data` >= ?";
	    List<Test> test = new ArrayList<>();
	    try (Connection conn = ConnectionFactory.getConnection();
	            PreparedStatement ps = conn.prepareStatement(sql);) {
	        ps.setString(1, utente.getUsername());
	        ps.setDate(2, java.sql.Date.valueOf(data));
	        
	        ResultSet rs = ps.executeQuery();
	        while(rs.next()) {
	            Test t = new Test(rs.getString("utente"), rs.getString("idTest"), TipoTest.valueOf(rs.getString("tipoTest")), rs.getDate("data").toLocalDate());
	            test.add(t);
	        }
	        return test;
	        
	    } catch(SQLException e) {
	        throw new DatabaseNonRaggiungibileException("Impossibile contattare il server.");
	    }
	}
}
