# Aventura mágica en la Mazmorra Encantada de Azkaban

## Solución de: Germán Fernández Carracedo
### GitHub: germangfc

![](https://upload.wikimedia.org/wikipedia/commons/6/6e/Harry_Potter_wordmark.svg)

### Planteamiento general

#### Mazmorra
Para simular la Mazmorra Encantada de Azkaban, se utilizará una matriz de 6 filas x 6 columnas. El contenido de cada posición de la matriz será un objeto de tipo Celda, que a su vez estará compuesto por un objeto de tipo Any?, y por un booleano "visitada" que indicará si la casilla se ha visitado o no.

Celda no podrá ser nulo, siempre habrá algo en las posiciones de la matriz. En cambio su contenido sí, por ello se ha definido de tipo Any?. 

Para ir simulando que solo se vean las casillas visitadas de la mazmorra, se irán descubriendo a cada paso de Harry las casillas adyacentes a su posición, en función de la variable booleana visitada. Estas casillas quedarán visibles para el resto del juego.

La mazmorra se inicializa colocando aleatoriamente los elementos del juego, que pueden ser objetos de la clase Horrocrux o de la clase Personaje, y dentro de estos serán de las clases Harry, Amigo o Enemigo. A Harry se le coloca en la posición de la **esquina superior izquierda**.

Se utiliza doble buffer para la mazmorra, para poder actualizar la mazmorra manteniendo una copia de su estado original para su consulta.


#### El juego
La sucesión de pasos generales del juego es la siguiente:
1. Mostrar la mazmorra en su estado actual, y las casillas adyacentes a Harry.
2. Preguntar al usuario por teclado la dirección a la que se moverá el protagonista. Aquí, el usuario puede elegir abortar y finalizar la partida.
3. Si es válida (no nos lleva fuera del tablero), realizaremos la acción que corresponda según el contenido de la casilla de destino, siguiendo indicaciones del enunciado del problema.
4. Se resuelven los ataques/defensas/curaciones/destrucciones de horrocruxes y en cada caso se marca en una variable si debemos movernos a la casilla definitivamente o debemos quedarnos en la actual.
    - Nos quedaremos en la posición actual si: 
      - Atacamos y fallamos.
      - Nos defendemos. Se ha decidido que el personaje se esconde y puede elegir moverse a otra casilla en el próximo turno.
    - En caso contrario, ocuparemos la casilla destino y el personaje que había desaparecerá (Horrocrux, Amigo que nos cura o Enemigo que muere o huye).
5. Se coloca a Harry en la casilla que aplique según el resultado del paso anterior.
6. Se muestran las estadísticas del juego en ese momento.
7. Se vuelve al paso 1.


Esta sucesión de pasos se realizará en bucle mientras que:
   - Harry siga vivo (puntos de vida mayor que 0)
   - Queden horrocruxes por destruir en la mazmorra
   - El usuario no haya elegido finalizar la partida  

Cuando esto ocurra finalizará la partida.

#### Finalización

Cuando finaliza la partida, se muestra de nuevo el resumen de estadísticas del juego y finaliza la aplicación.

#### Notas de interés

Cuando Lord Voldemort o Bellatrix huyen y saltan a una posición aleatoria, se ha decidido que no se modifica la visibilidad de la celda donde caen. Si huyen a una posición visitada, esta permanecerá visitada y si estaba oculta, permanecerá igualmente oculta.


