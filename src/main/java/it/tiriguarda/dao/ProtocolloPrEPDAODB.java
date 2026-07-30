package it.tiriguarda.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import it.tiriguarda.domain.ProtocolloPrEP;
import it.tiriguarda.domain.ProtocolloPrEPDaily;
import it.tiriguarda.domain.ProtocolloPrEPOnDemand;
import it.tiriguarda.domain.TipologiaPrEP;
import it.tiriguarda.domain.Utente;
import it.tiriguarda.exception.DatabaseNonRaggiungibileException;

public class ProtocolloPrEPDAODB implements ProtocolloPrEPDAO{
	
	@Override
	public ProtocolloPrEP trovaProtocolloAttivo(String username) {
		String sql = "select * from `ProtocolloPrEP` where `utente` = ? and `statoPrEP` = 1";
		try (Connection conn = ConnectionFactory.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);) {
			ps.setString(1, username);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				String idProtocollo = (rs.getString("idProtocollo"));
				String utente = (rs.getString("utente"));
				String tipoPrEP = (rs.getString("tipoPrEP"));
				LocalDate dataInizio = (rs.getDate("dataInizio").toLocalDate());
				boolean statoPrEP = (rs.getBoolean("statoPrEP"));
				LocalTime ora = (rs.getTime("ora").toLocalTime());
				
				ProtocolloPrEP prot;
				
				if(tipoPrEP.equals("DAILY")) {
					ProtocolloPrEPDaily protocollo = new ProtocolloPrEPDaily(idProtocollo, utente, dataInizio, statoPrEP, ora);
					prot = protocollo;
				}
				else {
					ProtocolloPrEPOnDemand protocollo = new ProtocolloPrEPOnDemand(idProtocollo, utente, dataInizio, statoPrEP, ora);
					prot = protocollo;
				}
				return prot;
			}else {
				return null;
			}
			
			}catch(SQLException e) {
				throw new DatabaseNonRaggiungibileException("Impossibile contattare il server.");
		}
	}
	
	@Override
	public void configuraProtocollo(ProtocolloPrEP protocolloPrEP) {
		String sql = "insert into `ProtocolloPrEP`(`idProtocollo`, `utente`, `tipoPrEP`, `dataInizio`, `statoPrEP`,`dataFine`, `ora`) "
				+ "values(?, ?, ?, ?, ?, ?, ?)";
		try(Connection conn = ConnectionFactory.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);) {
			ps.setString(1, protocolloPrEP.getIdProtocollo());
			ps.setString(2, protocolloPrEP.getUtente());
			ps.setString(3, protocolloPrEP.getTipoPrEP().name());
			ps.setDate(4, java.sql.Date.valueOf(protocolloPrEP.getDataInizio()));
			ps.setBoolean(5, protocolloPrEP.getStatoPrEP());
			if (protocolloPrEP.getDataFine() != null) {
			    ps.setDate(6, java.sql.Date.valueOf(protocolloPrEP.getDataFine()));
			} else {
			    ps.setNull(6, java.sql.Types.DATE);
			}
			ps.setTime(7, java.sql.Time.valueOf(protocolloPrEP.getOra()));
			
			ps.executeUpdate();

		}catch(SQLException e) {
			throw new DatabaseNonRaggiungibileException("Impossibile contattare il server.");
		}
	}
	
	@Override
	public void aggiornaProtocollo(ProtocolloPrEP protocolloPrEP) {
		String sql = "UPDATE `ProtocolloPrEP` SET `dataFine` = ? WHERE `idProtocollo` = ?";
	    try (Connection conn = ConnectionFactory.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {
	        ps.setDate(1, java.sql.Date.valueOf(protocolloPrEP.getDataFine()));
	        ps.setString(2, protocolloPrEP.getIdProtocollo());
	        ps.executeUpdate();
	    } catch(SQLException e) {
	        throw new DatabaseNonRaggiungibileException("Impossibile aggiornare il protocollo.");
	    }
	}
	
	@Override
	public void annullaStatoProtocollo(ProtocolloPrEP protocolloPrEP) {
		String sql = "UPDATE ProtocolloPrEP SET statoPrEP = ?, dataFine = ? WHERE idProtocollo = ?";
	    try(Connection conn = ConnectionFactory.getConnection();
	        PreparedStatement ps = conn.prepareStatement(sql)) {

	        ps.setBoolean(1, false);
	        ps.setDate(2, java.sql.Date.valueOf(protocolloPrEP.getDataFine()));
	        ps.setString(3, protocolloPrEP.getIdProtocollo());

	        ps.executeUpdate();

	    } catch(SQLException e) {
	        throw new DatabaseNonRaggiungibileException("Errore aggiornamento protocollo.");
	    }
	}
	
	@Override
    public List<ProtocolloPrEP> riepilogoPrEP(Utente utente) {
		String sql = "select * from `ProtocolloPrEP` where `utente` = ?";
		List<ProtocolloPrEP> protocolli = new ArrayList<>();
		try (Connection conn = ConnectionFactory.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);) {
			ps.setString(1, utente.getUsername());
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				TipologiaPrEP tipoPrEP = TipologiaPrEP.valueOf(rs.getString("tipoPrEP"));
				if(tipoPrEP == TipologiaPrEP.DAILY) {
					ProtocolloPrEP p = new ProtocolloPrEPDaily(rs.getString("idProtocollo"), rs.getString("utente"), rs.getDate("data").toLocalDate(), rs.getBoolean("statoPrEP"), rs.getTime("ora").toLocalTime());
					protocolli.add(p);
				}else {
					ProtocolloPrEP p = new ProtocolloPrEPOnDemand(rs.getString("idProtocollo"), rs.getString("utente"), rs.getDate("data").toLocalDate(), rs.getBoolean("statoPrEP"), rs.getTime("ora").toLocalTime());
					protocolli.add(p);
				}
			}
			return protocolli;
			}catch(SQLException e) {
				throw new DatabaseNonRaggiungibileException("Impossibile contattare il server.");
			}
    }
}
