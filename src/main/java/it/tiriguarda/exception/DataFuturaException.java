package it.tiriguarda.exception;

public class DataFuturaException extends TiRiguardaException {
    public DataFuturaException() {
        super("Hai inserito una data futura");
    }
}