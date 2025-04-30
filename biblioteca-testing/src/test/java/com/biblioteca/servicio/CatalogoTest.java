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

}