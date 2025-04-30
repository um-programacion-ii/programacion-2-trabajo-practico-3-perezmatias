package com.biblioteca.modelo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LibroTest {

    private String isbnValido = "978-3-16-148410-0";
    private String tituloValido = "Clean Code";
    private String autorValido = "Robert C. Martin";
    private Libro libro;

    @Test
    @DisplayName("Crear Libro con datos válidos inicializa correctamente")
    void testCrearLibroValido() {

        libro = new Libro(isbnValido, tituloValido, autorValido);

        assertNotNull(libro, "El libro no debería ser nulo después de crearlo.");
        assertEquals(isbnValido, libro.getIsbn(), "El ISBN obtenido no coincide con el esperado.");
        assertEquals(tituloValido, libro.getTitulo(), "El título obtenido no coincide con el esperado.");
        assertEquals(autorValido, libro.getAutor(), "El autor obtenido no coincide con el esperado.");
        assertEquals(EstadoLibro.DISPONIBLE, libro.getEstado(), "El estado inicial por defecto debe ser DISPONIBLE.");
    }

    @Test
    @DisplayName("Cambiar estado del Libro a PRESTADO funciona")
    void testCambiarEstadoLibroAPrestado() {
        libro = new Libro(isbnValido, tituloValido, autorValido);
        EstadoLibro estadoEsperado = EstadoLibro.PRESTADO;
        libro.setEstado(estadoEsperado); // Cambiamos el estado
        assertEquals(estadoEsperado, libro.getEstado(), "El estado del libro no se actualizó a PRESTADO.");
    }

    @Test
    @DisplayName("Cambiar estado del Libro a DISPONIBLE funciona")
    void testCambiarEstadoLibroADisponible() {
        libro = new Libro(isbnValido, tituloValido, autorValido);
        libro.setEstado(EstadoLibro.PRESTADO);
        EstadoLibro estadoEsperado = EstadoLibro.DISPONIBLE;
        libro.setEstado(estadoEsperado);
        assertEquals(estadoEsperado, libro.getEstado(), "El estado del libro no se actualizó a DISPONIBLE.");
    }
    @Test
    @DisplayName("Crear Libro con ISBN nulo lanza NullPointerException")
    void testCrearLibroIsbnNuloLanzaExcepcion() {
        assertThrows(NullPointerException.class, () -> {
            new Libro(null, tituloValido, autorValido);
        }, "Debería lanzar NullPointerException si el ISBN es nulo.");
    }

    @Test
    @DisplayName("Crear Libro con título vacío lanza IllegalArgumentException")
    void testCrearLibroTituloVacioLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Libro(isbnValido, "  ", autorValido);
        }, "Debería lanzar IllegalArgumentException si el título está vacío.");
    }

}