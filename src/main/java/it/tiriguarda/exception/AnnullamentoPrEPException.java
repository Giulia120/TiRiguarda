package it.tiriguarda.exception;

public class AnnullamentoPrEPException extends TiRiguardaException{
	public AnnullamentoPrEPException() {
		super("Non esiste un protocollo PrEP attivo.");
	}
}
