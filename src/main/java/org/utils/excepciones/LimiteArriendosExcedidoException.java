package org.utils.excepciones;

public class LimiteArriendosExcedidoException extends RuntimeException {
    public LimiteArriendosExcedidoException(String message) {
        super(message);
    }
}
