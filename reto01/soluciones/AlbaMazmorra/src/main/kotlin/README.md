
# Clase Mazmorra

La clase `Mazmorra` representa el entorno y la lógica central de la aventura mágica en Howgarts. Aquí se detallan sus componentes y funcionalidades.

## Propiedades

- **MAPSIZE:** Tamaño del mapa, constante con valor 6.
- **PROB_RECIBIR_ATAQUE:** Probabilidad de recibir un ataque, constante con valor 60.
- **mazmorra:** Matriz que representa la mazmorra, inicializada como una matriz de tamaño `MAPSIZE x MAPSIZE`.
- **harry:** Instancia de la clase `Harry`, el personaje principal.
- **columna:** Posición actual de la columna en la mazmorra.
- **fila:** Posición actual de la fila en la mazmorra.

## Métodos

1. **comenzar():**
    - **Descripción:** Inicializa la posición inicial de Harry en la mazmorra.
    - **Implementación:** Establece la posición de Harry en la esquina superior izquierda de la mazmorra.

2. **mover(usuarioDireccion: String):**
    - **Descripción:** Mueve a Harry en la dirección especificada por el usuario.
    - **Parámetro:**
        - `usuarioDireccion:` Dirección proporcionada por el usuario ("ARRIBA", "ABAJO", "DERECHA" o "IZQUIERDA").
    - **Implementación:** Calcula la nueva posición de Harry según la dirección y actualiza la mazmorra en consecuencia.

3. **imprimirMazmorra(mazmorra: Array<Array<Any?>>):**
    - **Descripción:** Imprime visualmente el estado actual de la mazmorra.
    - **Parámetro:**
        - `mazmorra:` Matriz que representa la mazmorra con los personajes y objetos.
    - **Implementación:** Itera sobre la matriz de la mazmorra y muestra el símbolo correspondiente para cada personaje u objeto.

4. **colocarPersonajesAleatorios(mazmorra: Array<Array<Any?>>):**
    - **Descripción:** Coloca aleatoriamente varios personajes y objetos en la mazmorra.
    - **Parámetro:**
        - `mazmorra:` Matriz que representa la mazmorra.
    - **Implementación:** Crea una lista de personajes y objetos, luego los coloca en celdas vacías de la mazmorra de manera aleatoria.

5. **simular():**
    - **Descripción:** Simula el desarrollo del juego.
    - **Implementación:** Inicia el juego colocando personajes aleatorios, luego ejecuta un bucle hasta que se cumplan ciertas condiciones (recoger todos los Horrocruxes o perder toda la salud de Harry).

6. **accionJuego():**
    - **Descripción:** Maneja la interacción del usuario, permitiéndole mover a Harry.
    - **Implementación:** Solicita al usuario que elija una dirección y mueve a Harry si es posible.

7. **comprobarLimites(fila: Int, columna: Int): Boolean:**
    - **Descripción:** Verifica si la posición especificada está dentro de los límites de la mazmorra.
    - **Parámetros:**
        - `fila:` Fila a verificar.
        - `columna:` Columna a verificar.
    - **Implementación:** Retorna `true` si la posición está dentro de los límites, de lo contrario, retorna `false`.

8. **accionPersonajes(fila: Int, columna: Int):**
    - **Descripción:** Maneja las acciones de los personajes en la mazmorra.
    - **Parámetros:**
        - `fila:` Fila actual de Harry.
        - `columna:` Columna actual de Harry.
    - **Implementación:** Realiza acciones según el tipo de personaje u objeto presente en la posición de Harry.

9. **moverEnemigo(fila: Int, columna: Int):**
    - **Descripción:** Mueve a un enemigo a una posición aleatoria de la mazmorra.
    - **Parámetros:**
        - `fila:` Fila actual de Harry.
        - `columna:` Columna actual de Harry.
    - **Implementación:** Encuentra una posición aleatoria vacía y coloca al enemigo allí, luego elimina al enemigo de su posición anterior.

10. **informe():**
    - **Descripción:** Muestra el estado actual de la salud y los Horrocruxes de Harry.
    - **Implementación:** Imprime la salud actual y la cantidad de Horrocruxes recogidos por Harry.

11. **simular():**
    - **Descripción:** Simula la aventura mágica hasta que se cumplan las condiciones de victoria o derrota.
    - **Implementación:** Inicializa la posición de Harry, coloca personajes aleatorios, muestra un informe inicial y entra en un bucle donde se solicitan acciones al usuario y se ejecutan las acciones de los personajes hasta que se cumplan las condiciones de fin de juego.

12. **main():**
    - **Descripción:** Función principal para ejecutar el juego.
    - **Implementación:** Muestra mensajes de bienvenida, genera una instancia de la clase `Mazmorra` y simula la aventura mágica llamando al método `simular()`.