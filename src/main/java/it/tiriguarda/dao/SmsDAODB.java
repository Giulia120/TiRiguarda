package it.tiriguarda.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import it.tiriguarda.domain.Sms;
import it.tiriguarda.domain.StatoSms;
import it.tiriguarda.domain.TipoSms;
import it.tiriguarda.exception.DatabaseNonRaggiungibileException;

public class SmsDAODB implements SmsDAO {
	private static final Logger logger = Logger.getLogger(SmsDAODB.class.getName());
	
	@Override
	public void salvaSms(Sms sms) {
		String sql = "insert into `Sms`(`utente`, `idSms`, `testo`, `dataSpedizione`, `stato`, `tipo` ) values(?,?,?,?,?,?)";
		try (Connection conn = ConnectionFactory.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);){
			ps.setString(1, sms.getUtente());
			ps.setString(2, sms.getIdSms());
			ps.setString(3, sms.getTesto());
			ps.setTimestamp(4, java.sql.Timestamp.valueOf(sms.getDataSpedizione()));
			ps.setString(5, sms.getStato().name());
			ps.setString(6, sms.getTipo().name());
			
			ps.executeUpdate();
		}catch (SQLException e) {
			logger.log(Level.SEVERE, "Errore SQL durante la registrazione dell'sms", e);
			throw new DatabaseNonRaggiungibileException("Impossibile contattare il server.");
		}
	}
	
	@Override
	public void eliminaSmsProgrammati(String username, TipoSms tipoSms) {
		String sql = "delete from `Sms` where (`utente` = ? AND `tipo` = ? )";
		try (Connection conn = ConnectionFactory.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);){
			ps.setString(1, username);
			ps.setString(2, tipoSms.name());
			
			ps.executeUpdate();
		}catch(SQLException e) {
			logger.log(Level.SEVERE, "Errore SQL durante l'eliminazione dell'sms", e);
			throw new DatabaseNonRaggiungibileException("Impossibile contattare il server.");
		}
	}
	
	@Override
	public List<Sms> recuperaSmsDaInviare(){
		String sql = "select `utente`, `idSms`, `testo`, `dataSpedizione`, `stato`, `tipo` from `Sms` where (`dataSpedizione` < ? AND `stato` =  ?)";
		try (Connection conn = ConnectionFactory.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);){
			ps.setTimestamp(1, java.sql.Timestamp.valueOf(LocalDateTime.now(ZoneId.systemDefault())));
			ps.setString(2, StatoSms.DA_INVIARE.name());
			
			ResultSet rs = ps.executeQuery();
			List<Sms> smsDaInviare = new ArrayList<>();
			while(rs.next()) {
				Sms nuovoSms = new Sms(rs.getString("utente"), 
						rs.getString("idSms"), 
						rs.getString("testo"), 
						rs.getObject("dataSpedizione", LocalDateTime.class), 
						TipoSms.valueOf(rs.getString("tipo")));
				smsDaInviare.add(nuovoSms);		
			}
			return smsDaInviare;
		}catch(SQLException e) {
			logger.log(Level.SEVERE, "Errore SQL durante il recupero dell'sms", e);
			throw new DatabaseNonRaggiungibileException("Impossibile contattare il server.");
		}
	}
	
	@Override
    public void aggiornaStato(Sms sms, StatoSms nuovoStato) {
		String sql = "update `Sms` set `stato` = ? where `idSms` = ?";
		try (Connection conn = ConnectionFactory.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);){
			ps.setString(1, nuovoStato.name());
			ps.setString(2, sms.getIdSms());
			
			ps.executeUpdate();
		}catch(SQLException e) {
			logger.log(Level.SEVERE, "Errore SQL durante l'aggiornamento di stato dell'sms", e);
			throw new DatabaseNonRaggiungibileException("Impossibile contattare il server.");
		}
	}
	
	@Override
	public void aggiornaData(Sms sms) {
		String sql = "update `Sms` set `dataSpedizione` = ? where `idSms` = ?";
		try (Connection conn = ConnectionFactory.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);){
			LocalDateTime nuovaData = sms.getDataSpedizione().plusDays(1);
	        ps.setTimestamp(1, java.sql.Timestamp.valueOf(nuovaData));
			ps.setString(2, sms.getIdSms());
			
			ps.executeUpdate();
		}catch(SQLException e) {
			logger.log(Level.SEVERE, "Errore SQL durante l'aggiornamento della data dell'sms", e);
			throw new DatabaseNonRaggiungibileException("Impossibile contattare il server.");
		}
	}

}
