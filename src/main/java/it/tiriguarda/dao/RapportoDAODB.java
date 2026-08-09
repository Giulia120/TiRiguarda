package it.tiriguarda.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import it.tiriguarda.domain.LivelloRischio;
import it.tiriguarda.domain.Rapporto;
import it.tiriguarda.exception.DatabaseNonRaggiungibileException;

public class RapportoDAODB implements RapportoDAO {
	private static final Logger logger = Logger.getLogger(RapportoDAODB.class.getName());
	
	@Override
	public void salvaRapporto (Rapporto rapporto) {
		String sql = "insert into `Rapporto` (`utente`, `idRapporto`, `data`,`rischio`, `dataFinePeriodoFinestra`) values (?,?,?,?,?)";
		try (Connection conn = ConnectionFactory.getConnection();
				PreparedStatement ps = conn.prepareCall(sql);) {
			ps.setString(1, rapporto.getUtente());
			ps.setString(2, rapporto.getIdRapporto());
			ps.setDate(3, java.sql.Date.valueOf(rapporto.getData()));
			ps.setString(4, rapporto.getRischio().name());
			if (rapporto.getDataFinePeriodoFinestra() != null) {
			    ps.setDate(5, java.sql.Date.valueOf(rapporto.getDataFinePeriodoFinestra()));
			} else {
			    ps.setNull(5, java.sql.Types.DATE);
			}
			
			ps.executeUpdate();
			
			}catch(SQLException e) {
				logger.log(Level.SEVERE, "Errore SQL durante la registrazione del rapporto", e);
				throw new DatabaseNonRaggiungibileException("Impossibile contattare il server.");
			}
	}
	
	@Override
    public List<Rapporto> riepilogoRapporti(String utente, LocalDate data) {
		String sql = "select `utente`, `idRapporto`, `data`, `rischio`, `dataFinePeriodoFinestra` from `Rapporto` where `utente` = ? and `data` >= ?";
		List<Rapporto> rapporti = new ArrayList<>();
		try (Connection conn = ConnectionFactory.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);) {
			ps.setString(1, utente);
			ps.setDate(2, java.sql.Date.valueOf(data));
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Rapporto r = new Rapporto(rs.getString("utente"), rs.getString("idRapporto"), rs.getDate("data").toLocalDate(), LivelloRischio.valueOf(rs.getString("rischio")));
				rapporti.add(r);
			}
			return rapporti;
			
			}catch(SQLException e) {
				logger.log(Level.SEVERE, "Errore SQL durante il riepilogo (rapporti)", e);
				throw new DatabaseNonRaggiungibileException("Impossibile contattare il server.");
			}
    }
}
