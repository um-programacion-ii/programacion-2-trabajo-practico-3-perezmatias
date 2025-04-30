package com.biblioteca.servicio;

import com.biblioteca.modelo.Libro;
import com.biblioteca.modelo.EstadoLibro;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Catalogo {

    private final Map<String, Libro> libros = new HashMap<>();

    public void agregarLibro(Libro libro) {
        Objects.requireNonNull(libro, "El libro a agregar no puede ser nulo.");
        if (libros.containsKey(libro.getIsbn())) {
            throw new IllegalArgumentException("Ya existe un libro con el ISBN: " + libro.getIsbn());
        }
        libros.put(libro.getIsbn(), libro);
    }

    public Libro buscarPorIsbn(String isbn) {
        return libros.get(isbn);
    }

    public List<Libro> obtenerTodosLosLibrosDisponibles() {
        return libros.values()
                .stream()
                .filter(libro -> libro.getEstado() == EstadoLibro.DISPONIBLE)
                .collect(Collectors.toList());
    }
    public List<Libro> obtenerTodosLosLibros() {
        return new ArrayList<>(libros.values());
    }
}