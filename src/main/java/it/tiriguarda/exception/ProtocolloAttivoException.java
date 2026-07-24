package it.tiriguarda.exception;

public class ProtocolloAttivoException extends TiRiguardaException{
	public ProtocolloAttivoException() {
		super("Esiste un protocollo PrEP gia attivo.");
	}
}