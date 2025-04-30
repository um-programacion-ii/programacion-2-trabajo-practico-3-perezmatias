package com.biblioteca.servicio;

import com.biblioteca.modelo.Libro;
import com.biblioteca.modelo.EstadoLibro;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

class CatalogoTest {

    private Catalogo catalogo;
    private Libro libro1_disponible;
    private Libro libro2_disponible;
    private Libro libro3_prestado;

    @BeforeEach
    void setUp() {
        catalogo = new Catalogo();

        libro1_disponible = new Libro("978-1", "Libro Disponible Uno", "Autor A");
        libro2_disponible = new Libro("978-2", "Libro Disponible Dos", "Autor B");
        libro3_prestado = new Libro("978-3", "Libro Prestado Tres", "Autor C");
        libro3_prestado.setEstado(EstadoLibro.PRESTADO);

        try {
            catalogo.agregarLibro(libro1_disponible);
            catalogo.agregarLibro(libro2_disponible);
            catalogo.agregarLibro(libro3_prestado);
        } catch (IllegalArgumentException e) {
            fail("La configuración (@BeforeEach) falló al agregar libros: " + e.getMessage());
        }
    }

    @Test
    @DisplayName("Agregar un libro nuevo al catálogo funciona correctamente")
    void testAgregarLibroExitoso() {
        Libro libroNuevo = new Libro("978-4", "Libro Nuevo", "Autor Nuevo");
        int cantidadInicial = catalogo.obtenerTodosLosLibros().size();

        assertDoesNotThrow(() -> {
            catalogo.agregarLibro(libroNuevo);
        }, "Agregar un libro válido no debería lanzar excepción.");

        Libro libroEncontrado = catalogo.buscarPorIsbn("978-4");
        assertNotNull(libroEncontrado, "El libro nuevo debería encontrarse en el catálogo después de agregarlo.");
        assertEquals(libroNuevo, libroEncontrado, "El libro encontrado debería ser el mismo que se agregó.");
        assertEquals(cantidadInicial + 1, catalogo.obtenerTodosLosLibros().size(), "La cantidad de libros debería haber aumentado en 1.");
    }

    @Test
    @DisplayName("Agregar un libro con ISBN duplicado lanza IllegalArgumentException")
    void testAgregarLibroDuplicadoLanzaExcepcion() {
        String isbnDuplicado = libro1_disponible.getIsbn();
        Libro libroDuplicado = new Libro(isbnDuplicado, "Otro Título", "Otro Autor");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            catalogo.agregarLibro(libroDuplicado);
        }, "Debería lanzarse IllegalArgumentException al agregar un ISBN duplicado.");

        assertTrue(exception.getMessage().contains(isbnDuplicado), "El mensaje de excepción debería contener el ISBN duplicado.");

        assertEquals(3, catalogo.obtenerTodosLosLibros().size(), "La cantidad de libros no debería cambiar si falla la adición.");
    }

    @Test
    @DisplayName("Buscar por ISBN existente devuelve el libro correcto")
    void testBuscarPorIsbnExistente() {
        String isbnBuscado = "978-1";
        Libro libroEncontrado = catalogo.buscarPorIsbn(isbnBuscado);

        assertNotNull(libroEncontrado, "Debería encontrar un libro con ISBN existente.");
        assertEquals(libro1_disponible.getIsbn(), libroEncontrado.getIsbn(), "El ISBN del libro encontrado no coincide.");
        assertEquals(libro1_disponible.getTitulo(), libroEncontrado.getTitulo(), "El título del libro encontrado no coincide.");
    }

    @Test
    @DisplayName("Buscar por ISBN no existente devuelve null")
    void testBuscarPorIsbnNoExistente() {
        String isbnInexistente = "000-000-000-X";
        Libro libroEncontrado = catalogo.buscarPorIsbn(isbnInexistente);
        assertNull(libroEncontrado, "No debería encontrar un libro con ISBN inexistente.");
    }

}