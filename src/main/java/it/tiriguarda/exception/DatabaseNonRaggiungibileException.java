package it.tiriguarda.exception;

public class DatabaseNonRaggiungibileException extends RuntimeException {
    
    public DatabaseNonRaggiungibileException(String messaggio) {
        super(messaggio);
    }
}
