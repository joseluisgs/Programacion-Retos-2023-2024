# Repositorio de Héroes

Este repositorio contiene la implementación de un sistema de gestión de héroes utilizando Kotlin. Proporciona funcionalidades para agregar, eliminar, actualizar, buscar y listar héroes.

## Estructura del proyecto

El proyecto está organizado de la siguiente manera:

- **`Heroe.kt`**: Define la clase `Heroe` que representa a un héroe de los Vengadores, con propiedades como nombre, alias, altura, edad, notas, y campos de metadatos como ID, fecha de creación y fecha de actualización.

- **`Vengadores.kt`**: Contiene la implementación del repositorio de héroes de los Vengadores utilizando programación orientada a objetos. Incluye funciones para agregar, eliminar, actualizar, buscar por ID y listar héroes.

- **`DC.kt`**: Contiene la implementación del repositorio de héroes de DC utilizando programación funcional. Incluye funciones para agregar, eliminar, actualizar, buscar por ID y listar héroes.

- **`VengadoresTest.kt`**: Archivo de pruebas unitarias utilizando JUnit para probar las operaciones del repositorio de héroes de los Vengadores.

- **`DCTest.kt`**: Archivo de pruebas unitarias utilizando JUnit para probar las operaciones del repositorio de héroes DC.

## Funcionalidades del Repositorio

En los repository podemos encontrar las siguientes funcionalidades:

- **Agregar Héroe**: Permite agregar un nuevo héroe a la base de datos.
- **Eliminar Héroe**: Permite eliminar un héroe existente de la base de datos.
- **Actualizar Héroe**: Permite actualizar la información de un héroe existente.
- **Buscar Héroe por ID**: Permite buscar un héroe por su ID único.
- **Listar Héroes**: Permite obtener una lista de todos los héroes almacenados en la base de datos.
