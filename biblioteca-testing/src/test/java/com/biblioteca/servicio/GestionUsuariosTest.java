package com.biblioteca.servicio;

import com.biblioteca.modelo.Libro;
import com.biblioteca.modelo.Prestamo;
import com.biblioteca.modelo.Usuario;
import com.biblioteca.modelo.EstadoLibro;
import com.biblioteca.excepciones.LibroNoDisponibleException;
import com.biblioteca.excepciones.LibroNoEncontradoException;
import com.biblioteca.excepciones.UsuarioNoEncontradoException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class GestionUsuariosTest {

    @Mock
    private SistemaPrestamos sistemaPrestamosMock;

    @InjectMocks
    private GestionUsuarios gestionUsuarios;

    private Usuario usuarioPrueba;
    private Libro libroPrueba;
    private Prestamo prestamoPrueba;
    private String nombreUsuarioPrueba = "TestUser";
    private String isbnPrueba = "999-TEST";

    @BeforeEach
    void setUp() {
        libroPrueba = new Libro(isbnPrueba, "Libro para Test", "Autor Test");
        prestamoPrueba = new Prestamo(libroPrueba);
    }

    @Test
    @DisplayName("registrarPrestamo funciona correctamente en caso de éxito")
    void testRegistrarPrestamoExitoso() {
        String nombreUsuarioExistente = "UsuarioRegistrado";
        String isbnValido = "111-VALIDO";
        Libro libroPrestado = new Libro(isbnValido, "Libro Prestado por Mock", "Mock Autor");
        Prestamo prestamoSimulado = new Prestamo(libroPrestado);

        assertDoesNotThrow(() -> gestionUsuarios.registrarUsuario(nombreUsuarioExistente));

        when(sistemaPrestamosMock.prestarLibro(isbnValido)).thenReturn(prestamoSimulado);

        assertDoesNotThrow(() -> {
            gestionUsuarios.registrarPrestamo(nombreUsuarioExistente, isbnValido);
        });

        verify(sistemaPrestamosMock).prestarLibro(isbnValido);

        Optional<Usuario> usuarioOpt = gestionUsuarios.buscarUsuarioPorNombre(nombreUsuarioExistente);
        assertTrue(usuarioOpt.isPresent(), "El usuario de prueba debería existir.");
        Usuario usuarioActualizado = usuarioOpt.get();

        assertNotNull(usuarioActualizado.getHistorialPrestamos(), "El historial de préstamos no debería ser nulo.");
        assertEquals(1, usuarioActualizado.getHistorialPrestamos().size(), "El historial debería contener 1 préstamo.");
        assertTrue(usuarioActualizado.getHistorialPrestamos().contains(prestamoSimulado), "El historial debería contener el préstamo simulado.");

    }

}