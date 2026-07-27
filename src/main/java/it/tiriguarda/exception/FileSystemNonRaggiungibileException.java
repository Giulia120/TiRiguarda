package it.tiriguarda.exception;

public class FileSystemNonRaggiungibileException extends RuntimeException {
	public FileSystemNonRaggiungibileException(String messaggio) {
        super(messaggio);
    }
}
