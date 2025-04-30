package com.biblioteca.modelo;

import java.time.LocalDate;
import java.util.Objects;

public class Prestamo {

    private final Libro libro;
    private final LocalDate fechaPrestamo;

    public Prestamo(Libro libro) {
        this.libro = Objects.requireNonNull(libro, "El libro no puede ser nulo para crear un préstamo.");
        this.fechaPrestamo = LocalDate.now();
    }

    public Libro getLibro() {
        return libro;
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Prestamo prestamo = (Prestamo) o;
        return Objects.equals(libro, prestamo.libro) && Objects.equals(fechaPrestamo, prestamo.fechaPrestamo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(libro, fechaPrestamo);
    }

    @Override
    public String toString() {
        return "Prestamo{" +
                "libro=" + (libro != null ? "'" + libro.getTitulo() + "'" : "null") +
                ", fechaPrestamo=" + fechaPrestamo +
                '}';
    }
}