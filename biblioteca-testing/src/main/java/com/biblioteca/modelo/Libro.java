package com.biblioteca.modelo;

import java.util.Objects;

public class Libro {

    private final String isbn;
    private final String titulo;
    private final String autor;
    private EstadoLibro estado;

    public Libro(String isbn, String titulo, String autor) {
        this.isbn = Objects.requireNonNull(isbn, "El ISBN no puede ser nulo.");
        this.titulo = Objects.requireNonNull(titulo, "El título не может быть пустым.");
        this.autor = Objects.requireNonNull(autor, "El autor no puede ser nulo.");

        if (isbn.trim().isEmpty() || titulo.trim().isEmpty() || autor.trim().isEmpty()) {
            throw new IllegalArgumentException("ISBN, título y autor no pueden estar vacíos.");
        }

        this.estado = EstadoLibro.DISPONIBLE;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public EstadoLibro getEstado() {
        return estado;
    }

    public void setEstado(EstadoLibro nuevoEstado) {
        this.estado = Objects.requireNonNull(nuevoEstado, "El nuevo estado не может быть пустым.");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Libro libro = (Libro) o;
        return Objects.equals(isbn, libro.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn);
    }

    @Override
    public String toString() {
        return "Libro{" +
                "isbn='" + isbn + '\'' +
                ", titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", estado=" + estado +
                '}';
    }
}