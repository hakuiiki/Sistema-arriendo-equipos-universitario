package org.utils.excepciones;

public class ClienteNoPuedeArrendarException extends RuntimeException {
    public ClienteNoPuedeArrendarException(String message) {
        super(message);
    }
}
