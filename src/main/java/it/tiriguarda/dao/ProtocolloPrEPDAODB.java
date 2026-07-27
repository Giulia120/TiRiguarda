package it.tiriguarda.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import it.tiriguarda.domain.ProtocolloPrEP;

public class ProtocolloPrEPDAODB implements ProtocolloPrEPDAO{
	
	@Override
	public void configuraProtocollo(ProtocolloPrEP protocolloPrEP) {
		String sql = "insert into `ProtocolloPrEP`(`idProtocollo`, `utente`, `tipoPrEP`, `dataInizio`, `statoPrEP`,`dataFine`) "
				+ "values(?, ?, ?, ?, ?, ?)";
		try(Connection conn = ConnectionFactory.getConnection();
				PreparedStatement ps = conn.prepareCall(sql);) {
			ps.setString(1, protocolloPrEP.getIdProtocollo());
			ps.setString(2, protocolloPrEP.getUtente().getUsername());
			ps.setString(3, protocolloPrEP.getTipoPrEP().name());
			ps.setDate(4, java.sql.Date.valueOf(protocolloPrEP.getDataInizio()));
			ps.setBoolean(5, protocolloPrEP.getStatoPrEP());
			ps.setDate(6, java.sql.Date.valueOf(protocolloPrEP.getDataFine()));
		}catch(SQLException e) {
			
		}
		
		System.out.println("Salvato nel DB");
	}
	
	@Override
	public void aggiornaProtocollo(ProtocolloPrEP protocolloPrEP) {
		System.out.println("Aggiornato nel DB");
	}
	
	@Override
	public void annullaStatoProtocollo(ProtocolloPrEP protocolloPrEP) {
		System.out.println("Aggiornato nel DB");
	}
}
