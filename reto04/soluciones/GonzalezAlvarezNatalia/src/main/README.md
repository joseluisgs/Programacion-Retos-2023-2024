## Componentes del programa

### Clases de modelado

- `Cliente`: representa a un cliente del restaurante, con propiedades como su identificador, tiempo de espera y total a pagar.
- `Plato`: representa a un plato del menú del restaurante, con propiedades como su nombre, primero, segundo, postre, precio y valoración.
- `Menu`: representa un menú del restaurante, el cual es una lista de platos.
- `Camarero`: representa a un camarero del restaurante, con propiedades como su fila, columna y tiempo de espera antes de pedir un plato.
- `Rata`: representa a una rata que puede aparecer en el restaurante y afectar el funcionamiento del mismo.

### Clases de colas

- `Queue`: interfaz genérica que representa una cola con métodos como `enqueue`, `dequeue`, `peek`, `isEmpty` y `size`.
- `Cocina`: clase que representa la cola de la cocina, donde se almacenan los menús pedidos por los camareros.
- `Mesa`: clase abstracta que representa una mesa del restaurante, con métodos como `enqueue`, `dequeue`, `peek`, `isEmpty` y `size`.

### Clases de mesas

- `Mesa1`, `Mesa2`, `Mesa3`: clases que representan las mesas del restaurante, con su propia lógica de interacción con los clientes y camareros.

### Otras clases

- `Restaurante`: clase que representa el restaurante en su conjunto, con métodos como `posicionarPersonajes`, `moverPersonajes` e `imprimirRestaurante`.

## Cómo usar el programa

El programa se puede ejecutar desde la clase `Restaurante` y observar la simulación del restaurante a través del método `simular`. La simulación durará 20 iteraciones y mostrará el estado del restaurante en cada iteración, incluyendo la posición de los personajes y las interacciones entre ellos.

Además, se pueden observar los informes finales de la simulación, como el total de clientes que visitaron el restaurante, el total de clientes descontentos y la recaudación total.