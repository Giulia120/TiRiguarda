package it.tiriguarda.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import it.tiriguarda.domain.Sms;
import it.tiriguarda.domain.StatoSms;
import it.tiriguarda.domain.TipoSms;
import it.tiriguarda.exception.DatabaseNonRaggiungibileException;

public class SmsDAODB implements SmsDAO {
	@Override
	public void salvaSms(Sms sms) {
		String sql = "insert into `Sms`(`utente`, `idSms`, `testo`, `dataSpedizione`, `stato`, `tipo` ) values(?,?,?,?,?,?)";
		try (Connection conn = ConnectionFactory.getConnection();
				PreparedStatement ps = conn.prepareCall(sql);){
			ps.setString(1, sms.getUtente());
			ps.setString(2, sms.getIdSms());
			ps.setString(3, sms.getTesto());
			ps.setTimestamp(4, java.sql.Timestamp.valueOf(sms.getDataSpedizione()));
			ps.setString(5, sms.getStato().name());
			ps.setString(6, sms.getTipo().name());
			
			ps.executeUpdate();
		}catch (SQLException e) {
			throw new DatabaseNonRaggiungibileException("Impossibile contattare il server.");
		}
	}
	
	@Override
	public void eliminaSmsProgrammati(String username, TipoSms tipoSms) {
		String sql = "delete from `Sms` where (`utente` = ? AND tipo = ? )";
		try (Connection conn = ConnectionFactory.getConnection();
				PreparedStatement ps = conn.prepareCall(sql);){
			ps.setString(1, username);
			ps.setString(2, tipoSms.name());
			
			ps.executeUpdate();
		}catch(SQLException e) {
			throw new DatabaseNonRaggiungibileException("Impossibile contattare il server.");
		}
	}
	
	@Override
	public List<Sms> recuperaSmsDaInviare(){
		String sql = "select * from `Sms` where (dataSpedizione < ? AND stato =  ?)";
		try (Connection conn = ConnectionFactory.getConnection();
				PreparedStatement ps = conn.prepareCall(sql);){
			ps.setTimestamp(1, java.sql.Timestamp.valueOf(LocalDateTime.now()));
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
			throw new DatabaseNonRaggiungibileException("Impossibile contattare il server.");
		}
	}
	
	@Override
    public void aggiornaStato(Sms sms, StatoSms nuovoStato) {}
	@Override
	public void aggiornaData(Sms sms) {}

}
