package com.biblioteca.servicio;

import com.biblioteca.modelo.Libro;
import com.biblioteca.modelo.Prestamo;
import com.biblioteca.modelo.EstadoLibro;
import com.biblioteca.excepciones.LibroNoEncontradoException;
import com.biblioteca.excepciones.LibroNoDisponibleException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SistemaPrestamosTest {

    @Mock
    private Catalogo catalogoMock;

    @InjectMocks
    private SistemaPrestamos sistemaPrestamos;

    @Test
    @DisplayName("Prestar libro existente y disponible funciona correctamente")
    void testPrestarLibroExitoso() {
        String isbnValido = "978-VALIDO";
        Libro libroDePrueba = new Libro(isbnValido, "Libro Disponible para Mock", "Autor Mock");
        assertEquals(EstadoLibro.DISPONIBLE, libroDePrueba.getEstado(), "Setup: Libro de prueba debe iniciar DISPONIBLE");

        when(catalogoMock.buscarPorIsbn(isbnValido)).thenReturn(libroDePrueba);

        Prestamo prestamoResultante = sistemaPrestamos.prestarLibro(isbnValido);

        assertNotNull(prestamoResultante, "El préstamo no debería ser nulo si el libro estaba disponible.");
        assertEquals(libroDePrueba, prestamoResultante.getLibro(), "El libro dentro del préstamo no es el esperado.");

        assertEquals(EstadoLibro.PRESTADO, libroDePrueba.getEstado(), "El estado del libro debería haber cambiado a PRESTADO.");

        verify(catalogoMock, times(1)).buscarPorIsbn(isbnValido);

    }

}