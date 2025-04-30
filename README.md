[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/jpLeSJQi)
# Sistema de Gestión de Biblioteca - Práctica de Testing

![Java](https://img.shields.io/badge/Java-21-orange)
![JUnit5](https://img.shields.io/badge/JUnit-5.9.2-green)
![Mockito](https://img.shields.io/badge/Mockito-5.3.1-blue)
![Maven](https://img.shields.io/badge/Maven-3.9.0-red)

## Tiempo Estimado y Recomendaciones
- **Tiempo estimado de realización:** 15 horas
- **Recomendación:** Se sugiere leer la consigna completa antes de comenzar con el desarrollo para tener una visión general del proyecto y planificar adecuadamente el trabajo.

## Identificación del Alumno
- **Nombre:** Matias Agustin
- **Apellido:** Perez
- **Legajo:** 61218

## Importante
- La rama `main` está protegida y no se pueden hacer commits directos sobre ella
- Todo el trabajo debe realizarse en ramas feature siguiendo el patrón `feature/issue-numero-descripcion`
- Los cambios deben ser enviados mediante Pull Requests
- Cada Pull Request debe estar asociado a un Issue específico

## Objetivo
Este proyecto tiene como objetivo que los estudiantes practiquen y desarrollen habilidades en testing unitario utilizando JUnit5 y Mockito en Java. A través de la implementación de un sistema de gestión de biblioteca, los estudiantes aprenderán a escribir pruebas unitarias efectivas y a utilizar mocks para simular dependencias.

## Requisitos Previos
- Java 21 o superior
- Maven
- Conocimientos básicos de Java y programación orientada a objetos

## Estructura del Proyecto
El proyecto está dividido en etapas incrementales, cada una enfocada en diferentes aspectos del testing. Cada etapa corresponde a un milestone en GitHub.

### Milestone 1: Configuración Inicial y Pruebas Básicas
**Objetivos de Aprendizaje:**
- Familiarizarse con la configuración de un proyecto Maven
- Aprender a escribir pruebas unitarias básicas con JUnit5
- Entender el concepto de assertions y su uso

**Issues Sugeridos:**
1. #1 Configuración inicial del proyecto Maven
2. #2 Implementación de la clase Libro
3. #3 Pruebas unitarias básicas para Libro

**Tareas:**
1. Configurar el proyecto Maven con las dependencias necesarias
2. Implementar la clase `Libro` con los siguientes atributos:
   - ISBN (String)
   - Título (String)
   - Autor (String)
   - Estado (Enum: DISPONIBLE, PRESTADO)
3. Escribir pruebas unitarias para:
   - Creación de un libro con datos válidos
   - Cambio de estado del libro

**Ejemplo de Guía:**
```java
@Test
void testCrearLibroValido() {
    Libro libro = new Libro("978-3-16-148410-0", "Clean Code", "Robert C. Martin");
    assertEquals("978-3-16-148410-0", libro.getIsbn());
    assertEquals("Clean Code", libro.getTitulo());
    assertEquals("Robert C. Martin", libro.getAutor());
    assertEquals(Estado.DISPONIBLE, libro.getEstado());
}
```

### Milestone 2: Implementación del Catálogo
**Objetivos de Aprendizaje:**
- Practicar el uso de colecciones en Java
- Aprender a escribir pruebas para métodos que manejan colecciones

**Issues Sugeridos:**
1. #4 Implementación de la clase Catalogo
2. #5 Pruebas para métodos de búsqueda

**Tareas:**
1. Implementar la clase `Catalogo` con los siguientes métodos:
   - Agregar libro
   - Buscar libro por ISBN
   - Obtener todos los libros disponibles
2. Escribir pruebas unitarias que incluyan:
   - Pruebas con múltiples libros
   - Búsquedas exitosas y fallidas

**Ejemplo de Guía:**
```java
@BeforeEach
void setUp() {
    catalogo = new Catalogo();
    libro1 = new Libro("978-3-16-148410-0", "Clean Code", "Robert C. Martin");
    libro2 = new Libro("978-0-13-235088-4", "Clean Architecture", "Robert C. Martin");
    catalogo.agregarLibro(libro1);
    catalogo.agregarLibro(libro2);
}

@Test
void testBuscarPorIsbn() {
    Libro libro = catalogo.buscarPorIsbn("978-3-16-148410-0");
    assertNotNull(libro);
    assertEquals("Clean Code", libro.getTitulo());
}
```

### Milestone 3: Sistema de Préstamos
**Objetivos de Aprendizaje:**
- Aprender a usar Mockito para simular dependencias
- Practicar el uso de mocks en pruebas unitarias

**Issues Sugeridos:**
1. #6 Implementación de la clase Prestamo
2. #7 Implementación de SistemaPrestamos
3. #8 Pruebas con mocks

**Tareas:**
1. Implementar las clases:
   - `Prestamo` (fecha de préstamo, libro)
   - `SistemaPrestamos` (gestión de préstamos)
2. Escribir pruebas que utilicen mocks para:
   - Simular el catálogo de libros
   - Probar el flujo de préstamo

**Ejemplo de Guía:**
```java
@Mock
private Catalogo catalogo;

@InjectMocks
private SistemaPrestamos sistemaPrestamos;

@Test
void testPrestarLibro() {
    Libro libro = new Libro("978-3-16-148410-0", "Clean Code", "Robert C. Martin");
    when(catalogo.buscarPorIsbn("978-3-16-148410-0")).thenReturn(libro);
    
    Prestamo prestamo = sistemaPrestamos.prestarLibro("978-3-16-148410-0");
    
    assertNotNull(prestamo);
    verify(catalogo).buscarPorIsbn("978-3-16-148410-0");
    assertEquals(Estado.PRESTADO, libro.getEstado());
}
```

### Milestone 4: Sistema de Usuarios
**Objetivos de Aprendizaje:**
- Practicar el uso de múltiples mocks en una prueba
- Aprender a manejar excepciones en pruebas

**Issues Sugeridos:**
1. #9 Implementación de la clase Usuario
2. #10 Implementación de GestionUsuarios
3. #11 Pruebas con múltiples mocks

**Tareas:**
1. Implementar las clases:
   - `Usuario` (nombre, historial de préstamos)
   - `GestionUsuarios` (registro de usuarios)
2. Escribir pruebas que:
   - Utilicen múltiples mocks
   - Prueben el manejo de excepciones

**Ejemplo de Guía:**
```java
@Mock
private Catalogo catalogo;

@Mock
private SistemaPrestamos sistemaPrestamos;

@InjectMocks
private GestionUsuarios gestionUsuarios;

@Test
void testRegistrarPrestamo() {
    Usuario usuario = new Usuario("usuario1");
    Libro libro = new Libro("978-3-16-148410-0", "Clean Code", "Robert C. Martin");
    
    when(catalogo.buscarPorIsbn("978-3-16-148410-0")).thenReturn(libro);
    when(sistemaPrestamos.prestarLibro("978-3-16-148410-0"))
        .thenReturn(new Prestamo(libro));
    
    gestionUsuarios.registrarPrestamo("usuario1", "978-3-16-148410-0");
    
    verify(sistemaPrestamos).prestarLibro("978-3-16-148410-0");
    assertEquals(1, usuario.getHistorialPrestamos().size());
}
```

## Entregables
Cada etapa debe ser entregada a través de un Pull Request que incluya:
1. Código fuente implementado
2. Pruebas unitarias correspondientes
3. Documentación de los cambios realizados
4. Evidencia de que las pruebas pasan correctamente

## 📝 Resumen de Cambios por Issue

A continuación se detallan las principales implementaciones y modificaciones realizadas para cada Issue del proyecto, siguiendo los milestones definidos:

### Milestone 1: Configuración Inicial y Pruebas Básicas

* **Issue #1: Configuración inicial del proyecto Maven**
    * Se creó la estructura del proyecto Maven.
    * Se configuró el archivo `pom.xml` para utilizar **Java 21**.
    * Se añadieron las dependencias requeridas para **JUnit 5 (5.9.2)** (API, Engine, Params) y **Mockito (5.3.1)** (Core, JUnit Jupiter) con el scope `test`.
    * Se configuraron los plugins `maven-compiler-plugin` (para Java 21) y `maven-surefire-plugin` (para ejecutar tests JUnit 5).
    * Se creó el archivo `.gitignore` para excluir la carpeta `target/` y archivos de IDE.

* **Issue #2: Implementación de la clase Libro**
    * Se creó el paquete `com.biblioteca.modelo`.
    * Se definió el `enum EstadoLibro` con valores `DISPONIBLE` y `PRESTADO`.
    * Se implementó la clase `Libro` con atributos privados (`isbn`, `titulo`, `autor`, `estado`), constructor público (con validaciones y estado inicial `DISPONIBLE`), getters públicos y un setter público `setEstado(EstadoLibro)`. Se incluyeron `equals`, `hashCode` y `toString`.

* **Issue #3: Pruebas unitarias básicas para Libro**
    * Se creó el paquete `com.biblioteca.modelo` en `src/test/java`.
    * Se implementó la clase de prueba `LibroTest` utilizando JUnit 5.
    * Se añadieron tests (`@Test`) para verificar:
        * La correcta creación e inicialización de un `Libro` (`testCrearLibroValido`).
        * El funcionamiento del método `setEstado` (`testCambiarEstadoLibroAPrestado`, `testCambiarEstadoLibroADisponible`).
        * El lanzamiento de excepciones por el constructor con argumentos inválidos (`testCrearLibroIsbnNuloLanzaExcepcion`, `testCrearLibroTituloVacioLanzaExcepcion`).
    * Se verificó que todas las pruebas pasan con `mvn test`.

### Milestone 2: Implementación del Catálogo

* **Issue #4: Implementación de la clase Catalogo**
    * Se creó el paquete `com.biblioteca.servicio`.
    * Se implementó la clase `Catalogo` con un `Map<String, Libro>` interno (clave=ISBN).
    * Se implementó `agregarLibro(Libro)` lanzando `IllegalArgumentException` si el ISBN está duplicado.
    * Se implementó `buscarPorIsbn(String)` devolviendo `Libro` o `null`.
    * Se implementó `obtenerTodosLosLibrosDisponibles()` utilizando Streams para filtrar por `EstadoLibro.DISPONIBLE`.
    * Se añadió método auxiliar `obtenerTodosLosLibros()`.

* **Issue #5: Pruebas unitarias para Catalogo**
    * Se creó la clase de prueba `CatalogoTest` en `src/test/java/com/biblioteca/servicio`.
    * Se utilizó `@BeforeEach` para configurar un `Catalogo` con libros de prueba (disponibles y prestados).
    * Se implementaron tests (`@Test`) para `agregarLibro` (éxito y duplicado con `assertThrows`).
    * Se implementaron tests (`@Test`) para `buscarPorIsbn` (encontrado con `assertNotNull`/`assertEquals`, y no encontrado con `assertNull`).
    * Se implementaron tests (`@Test`) para `obtenerTodosLosLibrosDisponibles`, verificando el tamaño y contenido de la lista resultante en diferentes escenarios (con disponibles, sin disponibles, catálogo vacío).
    * Se verificó que todas las pruebas pasan.

### Milestone 3: Sistema de Préstamos

* **Issue #6: Implementación de la clase Prestamo**
    * Se implementó la clase `Prestamo` en `com.biblioteca.modelo` con atributos `libro` (Libro) y `fechaPrestamo` (LocalDate, asignada automáticamente). Se incluyó constructor, getters, `equals`, `hashCode` y `toString`.

* **Issue #7: Implementación de SistemaPrestamos**
    * Se creó el paquete `com.biblioteca.excepciones`.
    * Se definieron las excepciones `LibroNoEncontradoException` y `LibroNoDisponibleException` (extendiendo `RuntimeException`).
    * Se implementó la clase `SistemaPrestamos` en `com.biblioteca.servicio` con dependencia `Catalogo` inyectada vía constructor.
    * Se implementó la lógica del método `prestarLibro(String isbn)`: busca en `Catalogo`, valida disponibilidad (lanza excepciones personalizadas si no se encuentra o no está disponible), cambia el estado del `Libro` a `PRESTADO`, crea y devuelve un objeto `Prestamo`.

* **Issue #8: Pruebas con Mocks para SistemaPrestamos**
    * Se creó la clase de prueba `SistemaPrestamosTest` configurada con Mockito (`@ExtendWith`, `@Mock`, `@InjectMocks`) para simular `Catalogo`.
    * Se implementó test para `prestarLibro` exitoso: usando `when/thenReturn` para el mock, verificando el `Prestamo` devuelto, el cambio de estado del libro y la interacción con el mock (`verify`).
    * Se implementaron tests para `prestarLibro` con libro no encontrado: usando `when/thenReturn(null)` en el mock y `assertThrows(LibroNoEncontradoException.class)`.
    * Se implementaron tests para `prestarLibro` con libro no disponible: usando `when/thenReturn` con un libro en estado `PRESTADO` y `assertThrows(LibroNoDisponibleException.class)`.
    * Se verificó que todas las pruebas pasan.

### Milestone 4: Sistema de Usuarios

* **Issue #9: Implementación de la clase Usuario**
    * Se implementó la clase `Usuario` en `com.biblioteca.modelo` con atributos `nombre` y `historialPrestamos` (`List<Prestamo>`).
    * Se implementó constructor, getter para nombre, getter para historial (con copia defensiva) y método `agregarPrestamoAlHistorial`.

* **Issue #10: Implementación de GestionUsuarios**
    * Se definió la excepción `UsuarioNoEncontradoException`.
    * Se implementó la clase `GestionUsuarios` en `com.biblioteca.servicio` con dependencia `SistemaPrestamos` inyectada y mapa interno para usuarios (clave=nombre).
    * Se implementó `registrarUsuario(String nombre)` con manejo de nombres duplicados (lanzando `IllegalArgumentException`).
    * Se implementó `registrarPrestamo(String nombreUsuario, String isbn)`: busca usuario (lanza `UsuarioNoEncontradoException`), llama a `sistemaPrestamos.prestarLibro`, relanza excepciones (`LibroNoEncontrado`, `LibroNoDisponible`), y si tiene éxito, añade `Prestamo` al historial del `Usuario`. Se añadió método auxiliar `buscarUsuarioPorNombre`.

* **Issue #11: Pruebas con múltiples mocks para GestionUsuarios**
    * Se creó la clase de prueba `GestionUsuariosTest` configurada con Mockito para simular `SistemaPrestamos`.
    * Se implementó test para `registrarPrestamo` exitoso, verificando la llamada al mock (`verify`) y la actualización del historial del usuario.
    * Se implementó test para `registrarPrestamo` con usuario no encontrado (`assertThrows`).
    * Se implementaron tests para `registrarPrestamo` verificando el correcto manejo (relanzamiento) de `LibroNoEncontradoException` y `LibroNoDisponibleException` cuando son lanzadas por el mock de `SistemaPrestamos` (usando `when/thenThrow` y `assertThrows`).
    * Se verificó que todas las pruebas pasan.

## ✅ Evidencia de Pruebas Superadas

Todas las pruebas unitarias implementadas para las clases `Libro`, `Catalogo` y `GestionUsuarios` pasan correctamente en la versión final del proyecto en la rama `main`.

La ejecución de `mvn test` produce la siguiente salida resumen:
```
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running com.biblioteca.modelo.LibroTest
[INFO] Tests run: 5, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.064 s -- in com.biblioteca.modelo.LibroTest
[INFO] Running com.biblioteca.servicio.SistemaPrestamosTest
OpenJDK 64-Bit Server VM warning: Sharing is only supported for boot loader classes because bootstrap classpath has been appended
WARNING: A Java agent has been loaded dynamically (/home/maag_perez/.m2/repository/net/bytebuddy/byte-buddy-agent/1.14.4/byte-buddy-agent-1.14.4.jar)
WARNING: If a serviceability tool is in use, please run with -XX:+EnableDynamicAgentLoading to hide this warning
WARNING: If a serviceability tool is not in use, please run with -Djdk.instrument.traceUsage for more information
WARNING: Dynamic loading of agents will be disallowed by default in a future release
INFO: Libro 'Libro Disponible para Mock' cambiado a estado PRESTADO.
INFO: Préstamo creado para 'Libro Disponible para Mock'.
[INFO] Tests run: 3, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 1.029 s -- in com.biblioteca.servicio.SistemaPrestamosTest
[INFO] Running com.biblioteca.servicio.GestionUsuariosTest
INFO: Usuario 'UsuarioRegistrado' registrado exitosamente.
INFO: Préstamo (Libro: 111-VALIDO) registrado en el historial de Usuario: UsuarioRegistrado
INFO: Usuario 'UsuarioParaTestExcepcion2' registrado exitosamente.
Error al intentar prestar libro para el usuario 'UsuarioParaTestExcepcion2': Mock: Libro no disponible ISBN: ISBN-NO-DISPONIBLE
INFO: Usuario 'UsuarioParaTestExcepcion' registrado exitosamente.
Error al intentar prestar libro para el usuario 'UsuarioParaTestExcepcion': Mock: Libro no encontrado ISBN: ISBN-QUE-FALLA
[INFO] Tests run: 4, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.109 s -- in com.biblioteca.servicio.GestionUsuariosTest
[INFO] Running com.biblioteca.servicio.CatalogoTest
[INFO] Tests run: 7, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.030 s -- in com.biblioteca.servicio.CatalogoTest
[INFO]
[INFO] Results:
[INFO]
[INFO] Tests run: 19, Failures: 0, Errors: 0, Skipped: 0
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  2.994 s
[INFO] Finished at: 2025-04-30T11:10:08-03:00
[INFO] ------------------------------------------------------------------------
```

## Consideraciones Éticas sobre el Uso de IA
El uso de Inteligencia Artificial (IA) como herramienta de asistencia en el desarrollo de software es una práctica cada vez más común. Sin embargo, es importante considerar los siguientes aspectos éticos:

## Uso de Asistencia de IA (Google Gemini)

Siguiendo las pautas de integridad académica y transparencia establecidas para este trabajo práctico, se declara el uso de la herramienta de inteligencia artificial Google Gemini como asistente durante el desarrollo.

La asistencia de IA se utilizó específicamente en las siguientes áreas:

* **Resolución de Errores:** Ayuda en la identificación y corrección de errores de compilación y runtime encontrados durante la codificación.
* **Estructuración de Tareas:** Sugerencias para organizar y describir los Issues de GitHub correspondientes a cada etapa del desarrollo.
* **Estructura del Proyecto:** Recomendaciones sobre la adopción de la estructura estándar de paquetes y carpetas para proyectos Java (`src/main/java`, etc.).
* **Guía y Planificación:** Asistencia en la interpretación inicial de los requisitos y en la planificación del desarrollo de las funcionalidades solicitadas.

**Autoría del Código:**

Es importante destacar que **el codigo presentado y logica utilizada son de autoria propia**. La IA funcionó como una herramienta de apoyo para superar bloqueos (errores), organizar el trabajo y obtener guía sobre convenciones estándar.

1. **Transparencia y Honestidad**
   - Declarar el uso de IA en el desarrollo del trabajo
   - Documentar cómo se utilizó la IA como herramienta de asistencia
   - No presentar código generado por IA como propio sin revisión y comprensión

2. **Aprendizaje y Comprensión**
   - La IA debe ser utilizada como una herramienta de aprendizaje, no como un reemplazo del pensamiento crítico
   - Es fundamental entender el código generado y las pruebas implementadas
   - El estudiante debe ser capaz de explicar y justificar las decisiones tomadas

3. **Responsabilidad**
   - El estudiante es responsable final de la calidad y corrección del código
   - Las pruebas deben ser verificadas y validadas personalmente
   - El código debe ser revisado y comprendido antes de su entrega

4. **Uso Apropiado**
   - La IA debe ser utilizada para asistir en el aprendizaje, no para evadir el proceso de desarrollo
   - Las consultas a la IA deben ser específicas y enfocadas en el aprendizaje
   - No se debe depender exclusivamente de la IA para resolver problemas

5. **Integridad Académica**
   - El trabajo final debe reflejar el aprendizaje y comprensión del estudiante
   - La IA es una herramienta de asistencia, no un sustituto del aprendizaje
   - Se espera que el estudiante demuestre su comprensión de los conceptos a través de su implementación

## Recursos Adicionales
- [Documentación de JUnit5](https://junit.org/junit5/docs/current/user-guide/)
- [Documentación de Mockito](https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html)
- [Guía de Maven](https://maven.apache.org/guides/)