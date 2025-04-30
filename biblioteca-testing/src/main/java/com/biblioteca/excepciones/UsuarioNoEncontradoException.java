package com.biblioteca.excepciones;

public class UsuarioNoEncontradoException extends RuntimeException {

    public UsuarioNoEncontradoException(String message) {
        super(message);
    }
}