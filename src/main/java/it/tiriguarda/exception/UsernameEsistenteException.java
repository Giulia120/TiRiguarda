package it.tiriguarda.exception;

public class UsernameEsistenteException extends TiRiguardaException{
	public UsernameEsistenteException() {
		super("username gia' in uso");
	}
}
