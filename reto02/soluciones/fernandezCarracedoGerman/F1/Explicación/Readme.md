# F1: Gran Premio de DAW

## Solución de: Germán Fernández Carracedo
### GitHub: germangfc

![](F1-logo.png)

### Planteamiento general

Se ha diseñado la aplicación con una clase abstracta Equipo, de la cuál heredan 4 clases (una por cada equipo participante). Los equipos solo heredan de esta clase abstracta el nombre del equipo, y para sus estrategias, implementan interfaces (ErrorEquipo, MalaEstrategia, ProblemaFiabilidad y VueltaRapida), puesto que son comportamientos que comparten o podrían compartir en el futuro.

Para los pilotos, únicamente hemos creado una clase Piloto, con comportamiento que comparten todos los pilotos, por lo que no se ha considerado necesario crear una clase para cada piloto, sino crear un objeto de esa clase por cada piloto participante y modificar su estado según el curso de los eventos de la carrera. Por ello, la clase piloto tampoco implementará ningún interfaz en esta versión.

#### Circuito
Para simular el circuito se utilizará una matriz de 8 filas x 10 columnas. Las filas representarán "calles" o "carriles" por los que circulan los coches, y las columnas serán "tramos" de pista. Los pilotos se sitúan inicialmente en la columna 0 y cada coche debe recorrer los 10 tramos de su carril para completar una vuelta. 

La matriz contendrá objetos del tipo Piloto, y al imprimir las posiciones de cada uno, comprobaremos el objeto Posición que tiene cada piloto (composición). También tendremos en cuenta el estado de carrera de cada piloto para mostrarlo (si está en pit stop o si ha tenido un accidente, por ejemplo).

Se mantendrá en cada turno una lista de los pilotos ordenada por tiempo de carrera, para mostrar el orden de carrera y finalmente el podium.

#### El juego
La sucesión de pasos generales del juego es un bucle que se repite hasta que no queden pilotos activos, es decir, hasta que **todos**, o bien hayan llegado a la meta, o bien hayan sido descalificados.
Cuando esto ocurra se mostrará el podio final y acabará la simulación.

1. Calcula el movimiento de los pilotos 
2. Actualizar el orden de los pilotos en la carrera
3. Comprobar la climatología y si se debe hacer un pitstop por ello
4. Mostrar la pista actualizada
5. Calcular los pilotos que siguen activos
6. Incrementar el tiempo de carrera
7. Se vuelve al paso 1.

#### Notas de interés

Cuando se cumple la probabilidad de lluvia, se comprueba si hay un cambio en el clima (si antes ya estaba lloviendo, se ha decidido que los coches no entren a cambiar neumáticos, y si estaba lloviendo y deja de hacerlo, también entrarán). Se contemplan las combinaciones posibles de estado del clima antes y después de llover, con el cambio si se produce. 

Si se produce cambio en el clima y finalmente los coches deben hacer pitstop, también se tiene en cuenta su estado actual en la carrera, para que actúen de una forma u otra: 
 - Si ya estaban el el pitstop aprovecharán a cambiar al neumático adecuado al nuevo clima, y si estaban afectados por una estratega anterior (error del equipo o vuelta rápida, p.ej.), finalizará, ya sea buena o mala, y entrarán a boxes. 
 - Si el piloto ya había llegado a meta o había sido descalificado, no hace el pitstop.


#### Posibles mejoras
Tener en cuenta la posibilidad de finalización anticipada de la carrera por:
- Lluvia extrema y suspensión de la carrera (con probabilidad de 1% por ejemplo)
- Tiempo límite de carrera (se establecería en una constante)
