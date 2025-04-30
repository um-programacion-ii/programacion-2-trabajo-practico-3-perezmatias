package com.biblioteca.excepciones;

public class LibroNoEncontradoException extends RuntimeException {

    public LibroNoEncontradoException(String message) {
        super(message);
    }
}