package com.biblioteca.excepciones;

public class LibroNoDisponibleException extends RuntimeException {

    public LibroNoDisponibleException(String message) {
        super(message);
    }
}