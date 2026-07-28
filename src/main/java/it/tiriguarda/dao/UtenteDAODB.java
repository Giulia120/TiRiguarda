package it.tiriguarda.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import it.tiriguarda.domain.SessoBiologico;
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
				throw new DatabaseNonRaggiungibileException("Impossibile contattare il server.");
			}
	}
	
	@Override
	public Utente trovaPerUsername(String usernameDaTrovare) {
		String sql = "select * from `Utente` where `username` = ?";
		try (Connection conn = ConnectionFactory.getConnection();
				PreparedStatement ps = conn.prepareCall(sql);) {
			ps.setString(1, usernameDaTrovare);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				String username = (rs.getString("username"));
				String password = (rs.getString("password"));
				String sesso = (rs.getString("sessoBiologico"));
				String telefono = (rs.getString("numeroTelefono"));
				
				Utente utente = new Utente(username, password, SessoBiologico.valueOf(sesso), telefono);
				return utente;
			}else {
				return null;
			}
			
			}catch(SQLException e) {
				throw new DatabaseNonRaggiungibileException("Impossibile contattare il server.");
		}
	}
	
	@Override 
	public void eliminaProtocolloAttivo(Utente utente) {
		String sql = "update `Utente` set `protocolloAttivo` = NULL where `username` = ? ";
		try (Connection conn = ConnectionFactory.getConnection();
				PreparedStatement ps = conn.prepareCall(sql);) {
			ps.setString(1, utente.getUsername());
			ps.executeUpdate();
			
			}catch(SQLException e) {
				throw new DatabaseNonRaggiungibileException("Impossibile contattare il server.");
		}
	}
	
	@Override
	public void aggiornaPwdUtente(Utente utente) {
		String sql = "update `Utente` set `password` = ? where `username` = ?";
		try (Connection conn = ConnectionFactory.getConnection();
				PreparedStatement ps = conn.prepareCall(sql);) {
			ps.setString(1, utente.getPassword());
			ps.setString(2, utente.getUsername());
			ps.executeUpdate();
			}catch(SQLException e) {
				throw new DatabaseNonRaggiungibileException("Impossibile contattare il server.");
		}
	}
	
	@Override
	public void aggiornaTelUtente(Utente utente) {
		String sql = "update `Utente` set `numeroTelefono` = ? where `username` = ?";
		try (Connection conn = ConnectionFactory.getConnection();
				PreparedStatement ps = conn.prepareCall(sql);) {
			ps.setString(1, utente.getNumeroTelefono());
			ps.setString(2, utente.getUsername());
			ps.executeUpdate();
			}catch(SQLException e) {
				throw new DatabaseNonRaggiungibileException("Impossibile contattare il server.");
		}
	}
	
	@Override
	public String recuperaNumeroTelefono(String username) {
		String sql = "Select `numeroTelefono` from `Utente` where `username` = ?";
		String numeroTelefono = null;
		try (Connection conn = ConnectionFactory.getConnection();
				PreparedStatement ps = conn.prepareCall(sql);) {
			ps.setString(1, username);
			
			try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) {
	               numeroTelefono = rs.getString("numeroTelefono");
	            }
	        }
			}catch(SQLException e) {
				throw new DatabaseNonRaggiungibileException("Impossibile contattare il server.");
		}
		return numeroTelefono;
	}
}
