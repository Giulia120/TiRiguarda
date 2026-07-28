package it.tiriguarda.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import it.tiriguarda.domain.Rapporto;
import it.tiriguarda.exception.DatabaseNonRaggiungibileException;

public class RapportoDAODB implements RapportoDAO {
	
	@Override
	public void salvaRapporto (Rapporto rapporto) {
		String sql = "insert into `Rapporto` (`utente`, `idRapporto`, `data`,`rischio`, `dataFinePeriodoFinestra`) values (?,?,?,?,?)";
		try (Connection conn = ConnectionFactory.getConnection();
				PreparedStatement ps = conn.prepareCall(sql);) {
			ps.setString(1, rapporto.getUtente());
			ps.setString(2, rapporto.getIdRapporto());
			ps.setDate(3, java.sql.Date.valueOf(rapporto.getData()));
			ps.setString(4, rapporto.getRischio().name());
			ps.setDate(5, java.sql.Date.valueOf(rapporto.getDataFinePeriodoFinestra()));
			
			ps.executeUpdate();
			
			}catch(SQLException e) {
				throw new DatabaseNonRaggiungibileException("Impossibile contattare il server.");
			}
	}
}
