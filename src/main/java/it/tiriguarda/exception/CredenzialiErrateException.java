package it.tiriguarda.exception;

public class CredenzialiErrateException extends TiRiguardaException  {
	public CredenzialiErrateException () {
        super("Credenziali Errate");
    }
	
	public CredenzialiErrateException(String message) {
		super(message);
	}
}
