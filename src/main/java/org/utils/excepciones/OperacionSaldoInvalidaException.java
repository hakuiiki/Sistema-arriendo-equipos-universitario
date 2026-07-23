package org.utils.excepciones;

public class OperacionSaldoInvalidaException extends RuntimeException {
    public OperacionSaldoInvalidaException(String message) {
        super(message);
    }
}
