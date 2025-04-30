package com.biblioteca.servicio;

import com.biblioteca.modelo.Usuario;
import com.biblioteca.modelo.Prestamo;
import com.biblioteca.excepciones.UsuarioNoEncontradoException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import com.biblioteca.modelo.Usuario;
import com.biblioteca.excepciones.LibroNoEncontradoException;
import com.biblioteca.excepciones.LibroNoDisponibleException;

public class GestionUsuarios {

    private final Map<String, Usuario> usuariosRegistrados = new HashMap<>();

    private final SistemaPrestamos sistemaPrestamos;

    public GestionUsuarios(SistemaPrestamos sistemaPrestamos) {
        this.sistemaPrestamos = Objects.requireNonNull(sistemaPrestamos, "SistemaPrestamos no puede ser nulo.");
    }

    public void registrarUsuario(String nombre) {
        Objects.requireNonNull(nombre, "El nombre de usuario no puede ser nulo.");
        if (nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de usuario no puede estar vacío.");
        }

        if (usuariosRegistrados.containsKey(nombre)) {
            throw new IllegalArgumentException("El nombre de usuario '" + nombre + "' ya está registrado.");
        }

        Usuario nuevoUsuario = new Usuario(nombre);

        usuariosRegistrados.put(nombre, nuevoUsuario);

        System.out.println("INFO: Usuario '" + nombre + "' registrado exitosamente.");
    }

    public void registrarPrestamo(String nombreUsuario, String isbn) {
        Objects.requireNonNull(nombreUsuario, "El nombre de usuario no puede ser nulo.");
        Objects.requireNonNull(isbn, "El ISBN no puede ser nulo.");

        Usuario usuario = usuariosRegistrados.get(nombreUsuario);

        if (usuario == null) {
            throw new UsuarioNoEncontradoException("Usuario con nombre '" + nombreUsuario + "' no encontrado.");
        }

        Prestamo prestamoRealizado;
        try {
            prestamoRealizado = sistemaPrestamos.prestarLibro(isbn);
        } catch (LibroNoEncontradoException | LibroNoDisponibleException e) {
            System.err.println("Error al intentar prestar libro para el usuario '" + nombreUsuario + "': " + e.getMessage());
            throw e;
        } catch (Exception e) {
            System.err.println("Error inesperado en sistemaPrestamos.prestarLibro: " + e.getMessage());
            throw new RuntimeException("Error inesperado durante el proceso de préstamo.", e);
        }

        if (prestamoRealizado != null) {
            usuario.agregarPrestamoAlHistorial(prestamoRealizado);
            System.out.println("INFO: Préstamo (Libro: " + isbn + ") registrado en el historial de Usuario: " + nombreUsuario);
        } else {
            System.err.println("ADVERTENCIA: sistemaPrestamos.prestarLibro devolvió null sin lanzar excepción.");
        }
    }

    public Optional<Usuario> buscarUsuarioPorNombre(String nombre) {
        return Optional.ofNullable(usuariosRegistrados.get(nombre));
    }

}