package it.tiriguarda.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import it.tiriguarda.domain.Utente;
import it.tiriguarda.exception.DatabaseNonRaggiungibileException;

public class UtenteDAODB implements UtenteDAO{
	@Override
	public void registraUtente(Utente utente) {
		String sql = "insert into `Utente`(`username`, `password`, `sessoBiologico`, `numeroTelefono`) values(?,?,?,?)";
		try (Connection conn = ConnectionFactory.getConnection();
				PreparedStatement ps = conn.prepareCall(sql);) {
			ps.setString(1, utente.getUsername());
			ps.setString(2, utente.getPassword());
			ps.setString(3, utente.getSessoBiologico().name());
			ps.setString(4, utente.getNumeroTelefono());
			
			ps.executeUpdate();
			
			}catch(SQLException e) {
				e.printStackTrace();
				throw new DatabaseNonRaggiungibileException("Impossibile contattare il server.");
			}
	}
	
	@Override
	public Utente trovaPerUsername(String username) {
		return null;
	}
	
	@Override 
	public void eliminaProtocolloAttivo(Utente utente) {
		System.out.println("Ciao");
	}
	
	@Override
	public void aggiornaUtente(Utente utente) {
		System.out.println("Ciao");
	}
}
