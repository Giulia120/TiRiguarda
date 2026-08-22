package it.tiriguarda.dao.db;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import it.tiriguarda.dao.ConnectionFactory;
import it.tiriguarda.dao.LoginDAO;
import it.tiriguarda.domain.SessoBiologico;
import it.tiriguarda.domain.TipologiaPrEP;
import it.tiriguarda.domain.Utente;
import it.tiriguarda.dto.CredenzialiBean;
import it.tiriguarda.exception.CredenzialiErrateException;
import it.tiriguarda.exception.DatabaseNonRaggiungibileException;

public class LoginDAODB implements LoginDAO {
	private static final Logger logger = Logger.getLogger(LoginDAODB.class.getName());
    
    @Override
    public Utente effetuaLogin(CredenzialiBean bean) {
        Utente utenteTrovato = null;

        try (Connection conn = ConnectionFactory.getConnection();
             CallableStatement cs = conn.prepareCall("{call login(?,?)}")) {
            
            cs.setString(1, bean.getUsername());
            cs.setString(2, bean.getPassword());

            ResultSet rs = cs.executeQuery();
                
                if (rs.next()) {
                    utenteTrovato = new Utente(
                    		rs.getString("username"),
                    		rs.getString("password"),
                    		SessoBiologico.valueOf(rs.getString("sessoBiologico")),
                    		rs.getString("numeroTelefono"));
                    if (rs.getString("protocolloAttivo") != null) {
    					utenteTrovato.setProtocolloAttivo(TipologiaPrEP.valueOf(rs.getString("protocolloAttivo")));
    				}
    				return utenteTrovato;
    			}else {
    				throw new CredenzialiErrateException();
    			}
            } catch (SQLException e) {
            	logger.log(Level.SEVERE, "Errore SQL durante il login", e);
				throw new DatabaseNonRaggiungibileException("Impossibile contattare il server.");
            	}
            }
        }
