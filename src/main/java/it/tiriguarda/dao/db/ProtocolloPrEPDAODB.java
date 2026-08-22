package it.tiriguarda.dao.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import it.tiriguarda.dao.ConnectionFactory;
import it.tiriguarda.dao.ProtocolloPrEPDAO;
import it.tiriguarda.domain.ProtocolloPrEP;
import it.tiriguarda.domain.ProtocolloPrEPDaily;
import it.tiriguarda.domain.ProtocolloPrEPOnDemand;
import it.tiriguarda.domain.TipologiaPrEP;
import it.tiriguarda.exception.DatabaseNonRaggiungibileException;

public class ProtocolloPrEPDAODB implements ProtocolloPrEPDAO{
	private static final Logger logger = Logger.getLogger(ProtocolloPrEPDAODB.class.getName());
	
	@Override
	public ProtocolloPrEP trovaProtocolloAttivo(String username) {
		String sql = "select `idProtocollo`, `utente`, `tipoPrEP`, `dataInizio`, `statoPrEP`, `dataFine`, `ora` from `ProtocolloPrEP` where `utente` = ? and `statoPrEP` = 1";
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
				logger.log(Level.SEVERE, "Errore SQL durante la ricerca del protocollo attivo", e);
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
			if (protocolloPrEP.getOra() != null) {
				ps.setTime(7, java.sql.Time.valueOf(protocolloPrEP.getOra()));
			}else {
				ps.setNull(7, java.sql.Types.TIME);
			}
			
			ps.executeUpdate();

		}catch(SQLException e) {
			logger.log(Level.SEVERE, "Errore SQL durante la configurazione del protocllo", e);
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
	    	logger.log(Level.SEVERE, "Errore SQL durante l'aggiornamento del protocollo", e);
	        throw new DatabaseNonRaggiungibileException("Impossibile aggiornare il protocollo.");
	    }
	}
	
	@Override
	public void annullaStatoProtocollo(ProtocolloPrEP protocolloPrEP) {
		String sql = "UPDATE ProtocolloPrEP SET statoPrEP = ?, dataFine = ? WHERE idProtocollo = ?";
	    try(Connection conn = ConnectionFactory.getConnection();
	        PreparedStatement ps = conn.prepareStatement(sql)) {

	        ps.setBoolean(1, false);
	        ps.setDate(2, java.sql.Date.valueOf(LocalDate.now(ZoneId.systemDefault())));
	        ps.setString(3, protocolloPrEP.getIdProtocollo());

	        ps.executeUpdate();

	    } catch(SQLException e) {
	        throw new DatabaseNonRaggiungibileException("Errore aggiornamento protocollo.");
	    }
	}
	
	@Override
    public List<ProtocolloPrEP> riepilogoPrEP(String utente, LocalDate data) {
		String sql = "select `idProtocollo`, `utente`, `tipoPrEP`, `dataInizio`, `statoPrEP`, `dataFine`, `ora` from `ProtocolloPrEP` where `utente` = ? and `dataInizio` >= ?";
		List<ProtocolloPrEP> protocolli = new ArrayList<>();
		try (Connection conn = ConnectionFactory.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);) {
			ps.setString(1, utente);
			ps.setDate(2, java.sql.Date.valueOf(data));
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				TipologiaPrEP tipoPrEP = TipologiaPrEP.valueOf(rs.getString("tipoPrEP"));
				String idProtocollo = rs.getString("idProtocollo");
				String username = rs.getString("utente");
				LocalDate dataInizio = rs.getDate("dataInizio").toLocalDate();
				boolean statoPrEP = rs.getBoolean("statoPrEP");
				LocalTime ora = rs.getTime("ora").toLocalTime();
				java.sql.Date dataFine = rs.getDate("dataFine");
				ProtocolloPrEP p;
				if(tipoPrEP == TipologiaPrEP.DAILY) {
	                p = new ProtocolloPrEPDaily(idProtocollo, username, dataInizio, statoPrEP, ora);
	            } else {
	                p = new ProtocolloPrEPOnDemand(idProtocollo, username, dataInizio, statoPrEP, ora);
	            }
				if (dataFine != null) {
					p.setDataFine(dataFine.toLocalDate());
				}
				protocolli.add(p);
			}
			return protocolli;
			}catch(SQLException e) {
				logger.log(Level.SEVERE, "Errore SQL durante il riepilogo (PrEP)", e);
				throw new DatabaseNonRaggiungibileException("Impossibile contattare il server.");
			}
    }
	@Override
	public boolean  esisteProtocollo(String utente, LocalDate data) {
		return esisteProtocollo(utente, data, true);
	}
	
	@Override
	public boolean esisteProtocollo(String utente, LocalDate data, boolean soloAttivi) {
		String sql = "select 1 from `ProtocolloPrEP` where `utente` = ? and `dataInizio` <=  ? and (`dataFine` >=  ? or `dataFine` is null)";
		if(soloAttivi) {
			sql+= " and `statoPrEP` = 1";
		}
		try (Connection conn = ConnectionFactory.getConnection();
		         PreparedStatement ps = conn.prepareStatement(sql)) {
			 	ps.setString(1, utente);
		        ps.setDate(2, java.sql.Date.valueOf(data));
		        ps.setDate(3, java.sql.Date.valueOf(data));
		        try (ResultSet rs = ps.executeQuery()) {
		        	return rs.next();
		        }
			}catch(SQLException e) {
				logger.log(Level.SEVERE, "Errore SQL durante la rierca del protocollo PrEP", e);
				throw new DatabaseNonRaggiungibileException("Impossibile contattare il server.");
			}
	}
	
	
}
