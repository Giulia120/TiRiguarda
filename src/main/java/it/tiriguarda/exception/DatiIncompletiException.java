package it.tiriguarda.exception;

public class DatiIncompletiException extends TiRiguardaException {
    
	public DatiIncompletiException() {
        super("Dati Incompleti");
    }
    
    public DatiIncompletiException(String message) {
        super(message);
    }
}
