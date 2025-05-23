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

    @Test
    @DisplayName("Prestar libro con ISBN inexistente lanza LibroNoEncontradoException")
    void testPrestarLibroNoEncontrado() {
        String isbnInexistente = "ISBN-QUE-NO-EXISTE";

        when(catalogoMock.buscarPorIsbn(isbnInexistente)).thenReturn(null);

        LibroNoEncontradoException exception = assertThrows(LibroNoEncontradoException.class, () -> {
            sistemaPrestamos.prestarLibro(isbnInexistente);
        }, "Debería lanzarse LibroNoEncontradoException si el ISBN no existe.");

        assertTrue(exception.getMessage().contains(isbnInexistente),
                "El mensaje de la excepción debería mencionar el ISBN no encontrado.");

        verify(catalogoMock).buscarPorIsbn(isbnInexistente);

    }

    @Test
    @DisplayName("Prestar libro existente pero NO disponible lanza LibroNoDisponibleException")
    void testPrestarLibroNoDisponible() {
        String isbnPrestado = "978-PRESTADO";
        Libro libroDePruebaPrestado = new Libro(isbnPrestado, "Libro Ya Prestado", "Autor Ocupado");
        libroDePruebaPrestado.setEstado(EstadoLibro.PRESTADO);

        when(catalogoMock.buscarPorIsbn(isbnPrestado)).thenReturn(libroDePruebaPrestado);

        LibroNoDisponibleException exception = assertThrows(LibroNoDisponibleException.class, () -> {
            sistemaPrestamos.prestarLibro(isbnPrestado);
        }, "Debería lanzarse LibroNoDisponibleException si el libro no está DISPONIBLE.");

        assertTrue(exception.getMessage().contains(isbnPrestado) || exception.getMessage().contains(libroDePruebaPrestado.getTitulo()),
                "El mensaje de excepción debería mencionar el libro no disponible.");
        assertTrue(exception.getMessage().contains(EstadoLibro.PRESTADO.toString()),
                "El mensaje de la excepción debería mencionar el estado PRESTADO.");

        verify(catalogoMock).buscarPorIsbn(isbnPrestado);

        assertEquals(EstadoLibro.PRESTADO, libroDePruebaPrestado.getEstado(),
                "El estado del libro no debería cambiar si el préstamo falló.");
    }

}