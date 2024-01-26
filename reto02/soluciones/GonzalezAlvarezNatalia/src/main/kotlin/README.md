# Explicación del programa

***

### Creación de clases

Comenzamos creando las diferentes clases.
En este caso utilizaremos el método de la Herencia, por lo que contaremos con una clase padre y varias clases hijas.
Aparte de varias interfaces.

Las interfaces serán:

* _AstonMartin_: con el método malaEstrategia(), el cual posiciona al piloto en la posición actual durante 2 segundos.
* _Ferrari_: con el método problemaFiabilidad(), el cual hace que el piloto sea descalificado.
* _Mercedes_: con el método cometerError(), el cual hace que el piloto retroceda 1 casilla.
* _RedBull_: con el método vueltaRapida(), el cual hace que el piloto avance 2 casillas.

La clase padre será _Piloto()_, la cual tendrá las variables:

* _i_: número de fila del piloto.
* _j_: número de columna del piloto.
* _nombre_: nombre del piloto.
* _vueltas_: número de vueltas del piloto.
* _continuar_: indicador de si los pilotos se mueven o no

Y los métodos:

* _posicionar()_: colocamos al piloto en la primera columna de la matriz.
* _mover()_: movemos al piloto a lo largo de la matriz.
* _movernos()_: recoge las diferentes condiciones que se pueden dar a la hora de mover a los pilotos e indica como deben moverse.
* _cambiarNeumaticos()_: el piloto deja de moverse durante unos segundos para realizar un cambio de neumaticos.
* _pitStop()_: el piloto deja de moverse durante unos segundos en la columna 4.
* _descalificarPiloto()_: el estado de los pilotos pasa a ser descalificado.
* _terminado()_: el piloto ha terminado la carrera.

De ella descienden las clases _Max()_, _Checo()_, _Alonso()_, _Stroll()_, _Hamilton()_, _Russell()_, _Sainz()_ y _Leclerc()_.

* Las clases _Max()_ y _Checo()_ heredan, aparte, de la interfaz _RedBull_.

* Las clases _Alonso()_ y _Stroll()_ heredan, aparte, de la interfaz _AstonMartin_.

* Las clases _Hamilton()_ y _Russell()_ heredan, aparte, de la interfaz _Mercedes_.

* Las clases _Sainz()_ y _Leclerc()_ heredan, aparte, de la interfaz _Ferrari_.

Por último, tenemos una enum class: _Estado()_ donde se clasifica el estado del piloto.


### Dinámica del juego

Hay 2 constantes con el tamaño de las filas y columnas de la matriz.

La clase Pista no tiene constructor, por lo que creamos nueve variables privadas que llaman a sus respectivas clases:
max, checo, alonso, stroll, hamilton, russell, sainz y leclerc. Y una variable que recoja la matriz.

Los métodos de la clase son los siguientes:

* _posicionarPilotos()_: colocamos a los pilotos en la primera columna de la matriz.
* _moverPilotos()_: movemos a los pilotos a lo largo de la matriz.
* _posicionValida()_: verifica que la posición a la cual se va a mover el piloto no se salga de los límites de la matriz.
* _imprimirMazmorra()_: se imprime la matriz con todos los pilotos colocados dentro en ella.
* _acciones()_: se llevan a cabo los diferentes métodos que heredan los pilotos de las interfaces.
* _accidentes()_: el estado de los pilotos pasa a ser descalificado.
* _llover()_: los pilotos dejan de moverse durante unos segundos para realizar un cambio de neumaticos si se da el porcentaje.
* _simular()_: recoge y simula todos los métodos necesarios para llevar a cabo el juego.
* _terminada()_: el piloto ha terminado la carrera.