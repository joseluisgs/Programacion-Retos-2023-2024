# Explicación del programa

***

### Creación de clases

Comenzamos creando las diferentes clases.
En este caso utilizaremos el método de la Herencia, por lo que contaremos con una clase padre y varias clases hijas con sus respectivos descendientes.

La clase padre será _Personaje()_, la cual estará vacía. De ella descienden las clases _Enemigo()_, _Aliado()_ y _Potter()_.

* De la clase _Enemigo()_ heredan las clases _Bellatrix()_, _Dementor()_ y _Voldemort()_. Las cuales heredan el daño que causarán a Harry y el método atacar().

* De la clase _Aliado()_ heredan las clases _Granger()_, _McGonagall()_ y _Ron()_. Las cuales heredan la cura que sumarán a la vida de Harry y el método curar().
A su vez, la clase _Ron()_ tiene un método propio que es fallar().

* La clase _Potter()_ tiene la variable vida y dos métodos propios: atacar() y defenderse().

Por último, tenemos tres clases independientes: _Mazmorra()_ donde se albergará toda la dinámica del juego,
_Horrocrux()_ que solo tiene un contador con el total de horrocruxes que se pueden destruir
y _Direccion()_ en donde se encuentran registradas las cuatro direcciones que puede tomar nuestro personaje.

### Dinámica del juego

Hay 4 constantes con el tamaño de la mazmorra, la cantidad de unidades de cada personaje, la cantidad de dementores y la cantidad máxima de horrocruxes.

La clase mazmorra no tiene constructor, por lo que creamos cuatro variables privadas que llaman a sus respectivas clases: potter, horrocrux, dementor y direccion.

Los métodos de la clase son los siguientes:

* _inicializarMazmorra()_: colocamos la cantidad requerida de los diferentes items del juego en el tablero de la mazmorra mediante el método "repeat" en una posición aleatoria.
En cada repeat asignamos a cada item su correspondiente clase.
* _posicionAleatoria()_: coloca los items en una posición aleatoria dentro de la matriz.
* _posicionHarry()_: situamos a Harry en su posición inicial (0,0). Este mismo método lo guardamos en la variable **posicion**.
* _imprimirMazmorra()_: se imprime la matriz con todos los personajes colocados dentro en ella.
* _movernos()_: a través de la respuesta del usuario, se mueve a Harry por la matriz siempre y cuando la posición sea válida.
* _mostrarCelda()_: cuando pasamos una posición, muestra la celda.
* _posicionValida()_: verifica que la posición a la cual se quiere mover no se salga de los límites de la matriz.
* _accionPersonajes()_: mediante un "when" asignamos los diferentes métodos que queremos que realice el juego al posicionarse sobre una casilla que esté ocupada por la clase casteada.
* _moverPersonajeAleatorio()_: mueve a Bellatrix y a Voldemort a una posición aleatoria dentro de la matriz cuando Potter se posiciona en su casilla.
* _informe()_: recoge la información en cada movimiento sobre la vida restante de Harry y los Horrocruxes destruidos.
* _accionJuego()_: recoge y simula todos los métodos necesarios para llevar a cabo el juego.