package com.biblioteca.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Usuario {

    private final String nombre;

    private final List<Prestamo> historialPrestamos;

    public Usuario(String nombre) {
        Objects.requireNonNull(nombre, "El nombre no puede ser nulo.");
        if (nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        this.nombre = nombre;
        this.historialPrestamos = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public List<Prestamo> getHistorialPrestamos() {
        return new ArrayList<>(this.historialPrestamos);
    }

    public void agregarPrestamoAlHistorial(Prestamo prestamo) {
        Objects.requireNonNull(prestamo, "El préstamo a añadir al historial no puede ser nulo.");
        this.historialPrestamos.add(prestamo);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(nombre, usuario.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nombre='" + nombre + '\'' +
                ", historialPrestamos(size)=" + historialPrestamos.size() +
                '}';
    }
}