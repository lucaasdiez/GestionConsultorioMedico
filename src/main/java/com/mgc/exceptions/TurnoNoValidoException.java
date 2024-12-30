package com.mgc.exceptions;

public class TurnoNoValidoException extends RuntimeException {
    public TurnoNoValidoException(String messege) {
        super(messege);
    }
}
