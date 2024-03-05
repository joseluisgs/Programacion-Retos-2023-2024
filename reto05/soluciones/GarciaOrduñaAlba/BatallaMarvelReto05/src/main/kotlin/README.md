# Proyecto Batalla de Héroes Marvel

Este proyecto simula una batalla entre héroes y villanos del universo Marvel. Los personajes se cargan desde un archivo CSV y se utilizan para realizar enfrentamientos aleatorios entre equipos de héroes y villanos.

## Estructura del Proyecto

El proyecto consta de los siguientes componentes principales:

1. **Clase Personaje:**
    - Representa a un personaje del universo Marvel.
    - Contiene los siguientes atributos:
        - ID: Identificador único del personaje.
        - NickName: Apodo o alias del personaje.
        - Nombre: Nombre completo del personaje.
        - Edad: Edad del personaje.
        - Vivo: Booleano que indica si el personaje está vivo (true) o fallecido (false).
        - Villano: Booleano que indica si el personaje es un villano (true) o no (false).
        - Habilidad: Habilidad especial o destreza del personaje.
        - PuntosCombate: Puntos de combate que indican la fuerza del personaje.

2. **Clase PersonajeService:**
    - Gestiona la carga de los personajes desde un archivo CSV y proporciona métodos para obtener listas de personajes y determinar el ganador de la Batalla de Héroes Marvel.
    - Métodos principales:
        - `cargarPersonajesDesdeCSV()`: Carga los personajes desde un archivo CSV.
        - `obtenerPersonajes()`: Retorna la lista de todos los personajes cargados.
        - `obtenerPersonajesAleatorios()`: Retorna una lista aleatoria de 5 personajes.
        - `determinarGanador()`: Determina el ganador de la Batalla de Héroes Marvel.

