EXPLIXACION YAHYA
Tengo un arbol de herencia que comienza con equipos que no tiene nada y es una abstract clas,
luego cada equipo hereda de equipo ya ya cada uno implementa sus funcion, ya sea mala estartegia, vuelta rapida, etc.
Luego esta la clase de cada piloto que hereda de su equipo y tienen la interfaz piloto en la cual estan todas las variables
que tienen los pilotos las funciones de accidentes pitstop y moverse, ya que es algo que tienen todos.

La simulacion tiene un do while que acaba cuando todos haya terminado la carrera que se comprueba en cada movimiento si tos han acabado.
la simulacion lo primero que hace es mover al jugador ya nular su posicion anterior, una vez a modificado su columna con la funcion de mover
empezamos a hacer las acciones, la primera es pitstop en caso de que esa columna sea la 4
luego reliazamos las probabilidades de los accidentes y de las malas estrategia
comprobamos la metreologia y y ya por ultimo posicionamos a los pilotos por si en alguna de las columnas hemos cambiado su columna
y acabaria cuando tods esten accidentados o tengan en su contador de vueltas un 3