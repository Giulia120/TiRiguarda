package it.tiriguarda.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionFactory {
	private static final Logger logger = Logger.getLogger(ConnectionFactory.class.getName());
	private static String connectionUrl;
    private static String dbUser;
    private static String dbPass;

    private ConnectionFactory() {
    	// Costruttore privato per nascondere quello pubblico di default
    }
    
    static {
        try (InputStream input = ConnectionFactory.class.getResourceAsStream("/db.properties")) {
            Properties properties = new Properties();
            properties.load(input);

            connectionUrl = properties.getProperty("CONNECTION_URL");
            dbUser = properties.getProperty("DB_USER");
            dbPass = properties.getProperty("DB_PASS");

        } catch (IOException e) {
            logger.log(Level.SEVERE, "Errore nella connessione al DB ", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(connectionUrl, dbUser, dbPass);
    }
}
