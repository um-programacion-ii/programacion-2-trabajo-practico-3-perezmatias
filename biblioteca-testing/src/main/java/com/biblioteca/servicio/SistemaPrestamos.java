package com.biblioteca.servicio;

import com.biblioteca.modelo.Libro;
import com.biblioteca.modelo.Prestamo;
import com.biblioteca.excepciones.LibroNoEncontradoException;
import com.biblioteca.excepciones.LibroNoDisponibleException;
import java.util.Objects;

import com.biblioteca.modelo.EstadoLibro;

public class SistemaPrestamos {

    private final Catalogo catalogo;

    public SistemaPrestamos(Catalogo catalogo) {
        this.catalogo = Objects.requireNonNull(catalogo, "El catálogo no puede ser nulo.");
    }

    public Prestamo prestarLibro(String isbn) {
        Objects.requireNonNull(isbn, "El ISBN no puede ser nulo para prestar.");
        Libro libro = catalogo.buscarPorIsbn(isbn);

        if (libro == null) {
            throw new LibroNoEncontradoException("No se encontró ningún libro con el ISBN: " + isbn);
        }

        if (libro.getEstado() != EstadoLibro.DISPONIBLE) {
            throw new LibroNoDisponibleException("El libro '" + libro.getTitulo() + "' (ISBN: " + isbn
                    + ") no está disponible para préstamo. Estado actual: " + libro.getEstado());
        }

        libro.setEstado(EstadoLibro.PRESTADO);
        System.out.println("INFO: Libro '" + libro.getTitulo() + "' cambiado a estado PRESTADO.");

        Prestamo nuevoPrestamo = new Prestamo(libro);
        System.out.println("INFO: Préstamo creado para '" + libro.getTitulo() + "'.");
        return nuevoPrestamo;
    }

    public void devolverLibro(Prestamo prestamo) {
        System.out.println(">>> Lógica de devolverLibro PENDIENTE <<<");
        throw new UnsupportedOperationException("devolverLibro no implementado todavía.");
    }

}