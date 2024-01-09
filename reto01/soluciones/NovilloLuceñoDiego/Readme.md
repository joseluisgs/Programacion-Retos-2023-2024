## Este es mi solución del problema de Harry Potter usando objetos con polimorfismo y herencias.

### Mi proyecto consta de las siguientes clases:

- **Controller**: se encarga del funcionamiento del juego y de coordinar las partes que forman parte de él.
- **Board**: se encarga de controlar todo lo que tiene que ver con la matriz, como por ejemplo imprimir el mapa.
- **Direction**: es una enum class que tiene las cuatro posiciones en las que se puede mover el personaje.
- **Position**: es la clase encargada de almacenar las coordenadas de los objetos que se encuentran en la matriz.
- **Entity**: es la clase más básica que engloba a todos los objetos que forman parte de la matriz. Solo guarda la posición de cada objeto y es abstracta.
  - **Player**: es el objeto que se mueve por el tablero según el input del usuario, se encarga de moverse a casillas válidas y de asegurarse que sus hechizos funcionan.
  - **Horrocrux**: son los objetos que Harry debe coleccionar para ganar.
  - **Enemies**: es la clase más básica que engloba a todos los enemigos, esta clase tiene una variable que contiene el ataque que tiene cada tipo.
    - **Dementor**: aparece, si Harry falla, ataca, si no, no y desaparece.
    - **StrongEnemies**: engloba a Voldemort y Bellatrix, si Harry acierta se mueven.
      - **Voldemort**: tiene 70 de ataque.
      - **Bellatrix**: tiene 30 de ataque.
  - **Allies**: es la clase más básica que engloba a todos los amigos de Harry, esta clase tiene una variable que dicta cuánto pueden curar a Harry.
    - **McGonagal**: puede curar 70 HP.
    - **Hermione**: puede curar 30 HP.
    - **Hermione**: puede curar 20 HP o hacerle daño a Harry (10 HP).


