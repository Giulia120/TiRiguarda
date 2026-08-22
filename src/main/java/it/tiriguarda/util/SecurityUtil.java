package it.tiriguarda.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import it.tiriguarda.exception.DatiIncompletiException;

public class SecurityUtil {
	private SecurityUtil() {
		// Costruttore privato per nascondere quello pubblico di default
	}
    public static String hashPassword(String passwordInChiaro) {
    	if (passwordInChiaro == null) {
            throw new DatiIncompletiException("La password da decifrare non può essere null");
        }
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(passwordInChiaro.getBytes());
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Errore di configurazione crittografia", e);
        }
    }
}