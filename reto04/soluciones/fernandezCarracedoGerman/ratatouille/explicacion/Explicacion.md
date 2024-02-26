# Restaurante La Ratatouille
### Solución de: Germán Fernández Carracedo
### GitHub: germangfc

---


<div style="text-align: justify">

### Planteamiento general

Se simulará por una parte el movimiento dentro del restaurante y por otra, el desarrollo de una jornada de trabajo en 
el restaurante, tal como pide el enunciado.

El modulo controlador hará la simulación, introduciendo los clientes, moviendo a los camareros y actualizando las 
estructuras de datos de apoyo.

### Diseño

Se ha diseñado la aplicación con el siguiente esquema de clases: una clase abstracta Empleado, de la cuál heredan 2 
clases (Cocinero y Camarero). Por otra parte tenemos las clases Cliente, Comanda, Menú y Mesa. No se ha definido una clase
rata puesto que no es necesario guardar ningún estado o comportamiento de la misma al estar gestionado como un "evento" 
en el controlador.

Para generar los menús se ha creado un factory que los crea combinando aleatoriamente varios primeros platos, segundos y postres.

Nos hemos apoyado en la clase ArrayDeque para gestionar un repositorio implementado con una cola para las comandas en 
espera de ser cocinadas, y la hemos combinado con algunas operaciones, que indicamos en el interfaz Cola.

Se ha creado un sistema de excepciones orientado a dominio.

Los parámetros de configuración de nuestro programa se almacenan en un fichero, y se cargan en memoria en un Object llamado Config.

Por último, tenemos un controlador, del que tendremos una sola instancia, utilizando el patrón singleton. Se podría 
haber implementado directamente como Object, al no tener parámetros, pero se ha optado por el singleton para practicar 
y por si en alguna ampliación posterior, se le añade algún parámetro. 

Como estructuras de datos tendremos: una lista de menús para el día, generados aleatoriamente,
la cola de comandas, una cola de clientes esperando entrar, una lista de comandas terminadas y una matriz que representará
el restaurante.

#### Restaurante

Se representará mediante una matriz de objetos de la clase Any (o null) con tantas filas y columnas como nos indica el enunciado 
(3 columnas de los pasillos de los camareros x 7 filas, una para el salón, cinco para los camareros
y una para la cocina). Estos parámetros se pueden modificar en el fichero de configuración.

El contenido de la matriz será: 
- Fila 0 (Salón): objetos Cliente u objetos Mesa (cuando no hay cliente).
- Filas 1 hasta la fila de la cocina: objetos camarero o null.
- Fila 7 (última, Cocina): objeto cocinero, situado siempre en la columna 1 de la matriz.

#### La simulación
La sucesión de pasos generales de la simulación es un bucle que se repite hasta que no queden
clientes en espera para entrar al restaurante, no haya ningún cliente comiendo aún, o se haya
agotado un tiempo de simulación establecido.
Cuando esto ocurra, acabará la simulación y se mostrará el informe final.

#### Secuencia:

1. Entrada de clientes, con una probabilidad y cada cierto tiempo.
2. Comprobar la aparición aleatoria de una rata en el restaurante.
3. Acciones de los clientes. Según su estado actual, esperarán al camarero, comerán, se enfadarán si no son atendidos, y se marcharán sin pagar si no les atienden a tiempo o si ven una rata. 
4. Acciones de los camareros. Según su estado actual, esperarán nuevos clientes, irán a preguntarles el menú, irán a la cocina a encargarlo y lo llevarán de vuelta al cliente cuando esté listo. Pueden tropezar con una rata si sale en su pasillo.
5. Acciones del cocinero. Permanecerá a la espera de que le pidan una comanda, la cocinará y volverá a la espera.
6. Incrementar el tiempo de simulación y los tiempos de simulación de los clientes.
7. Mostrar el restaurante.
8. Se vuelve al paso 1.

#### Notas de interés

Acciones como cocinar una comanda, comer,... tienen una duración determinada en la configuración y pueden modificarse en 
el fichero de configuración.

Los clientes, según el enunciado, si pasan 15 segundos sin ser atendidos, protestarán. En esta solución se ha añadido que 
lo hagan con una probabilidad, por ello, algunos no protestarán. A los 20 segundos, igualmente según una probabilidad 
protestarán y se marcharán sin pagar. Estos tiempos y probabilidades son configurables dentro de la clase Cliente. En
nuestro caso, la probabilidad de marcharse sin pagar es del 100%.

Cuando un camarero sirve el menú al cliente o cuando está dirigiéndose hacia el cliente y es interrumpido, se colocarán 
de vuelta automáticamente en su "posición de inicio" junto a la cocina.
Por falta de tiempo no se ha implementado la simulación de esta vuelta desde la mesa del cliente.

#### Posibles mejoras

Implementación de la "vuelta a la posición de inicio" de los camareros.


</div>

