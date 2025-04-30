package com.biblioteca.servicio;

import com.biblioteca.modelo.Usuario;
import com.biblioteca.modelo.Prestamo;
import com.biblioteca.excepciones.UsuarioNoEncontradoException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class GestionUsuarios {

    private final Map<String, Usuario> usuariosRegistrados = new HashMap<>();

    private final SistemaPrestamos sistemaPrestamos;

    public GestionUsuarios(SistemaPrestamos sistemaPrestamos) {
        this.sistemaPrestamos = Objects.requireNonNull(sistemaPrestamos, "SistemaPrestamos no puede ser nulo.");
    }

    public void registrarUsuario(String nombre) {
        System.out.println(">>> Lógica de registrarUsuario PENDIENTE <<<");
        throw new UnsupportedOperationException("registrarUsuario no implementado todavía.");
    }

    public void registrarPrestamo(String nombreUsuario, String isbn) {
        System.out.println(">>> Lógica de registrarPrestamo PENDIENTE <<<");
        throw new UnsupportedOperationException("registrarPrestamo no implementado todavía.");
    }

    public Optional<Usuario> buscarUsuarioPorNombre(String nombre) {
        throw new UnsupportedOperationException("buscarUsuarioPorNombre no implementado todavía.");
    }

}