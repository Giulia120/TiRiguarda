package it.tiriguarda.exception;

public class UtenteNonLoggatoException extends TiRiguardaException {
	public UtenteNonLoggatoException() {
		super("Errore critico: Nessun utente loggato in sessione.");
	}
}
