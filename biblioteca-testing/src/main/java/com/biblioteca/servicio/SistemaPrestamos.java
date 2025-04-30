package com.biblioteca.servicio;

import com.biblioteca.modelo.Libro;
import com.biblioteca.modelo.Prestamo;
import com.biblioteca.excepciones.LibroNoEncontradoException;
import com.biblioteca.excepciones.LibroNoDisponibleException;
import java.util.Objects;

public class SistemaPrestamos {

    private final Catalogo catalogo;

    public SistemaPrestamos(Catalogo catalogo) {
        this.catalogo = Objects.requireNonNull(catalogo, "El catálogo no puede ser nulo.");
    }

    public Prestamo prestarLibro(String isbn) {
        System.out.println(">>> Lógica de prestarLibro PENDIENTE <<<");
        throw new UnsupportedOperationException("prestarLibro no implementado todavía.");
    }

    public void devolverLibro(Prestamo prestamo) {
        System.out.println(">>> Lógica de devolverLibro PENDIENTE <<<");
        throw new UnsupportedOperationException("devolverLibro no implementado todavía.");
    }

}