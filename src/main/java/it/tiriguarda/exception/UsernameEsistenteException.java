package it.tiriguarda.exception;

public class UsernameEsistenteException extends TiRiguardaException{
	public UsernameEsistenteException() {
		super("Username gia' in uso");
	}
}
