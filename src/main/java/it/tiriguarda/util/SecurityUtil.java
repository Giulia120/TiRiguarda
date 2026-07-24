package it.tiriguarda.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class SecurityUtil {
	private SecurityUtil() {
		// Costruttore privato per nascondere quello pubblico di default
	}
    public static String hashPassword(String passwordInChiaro) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(passwordInChiaro.getBytes());
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Errore di configurazione crittografia", e);
        }
    }
}