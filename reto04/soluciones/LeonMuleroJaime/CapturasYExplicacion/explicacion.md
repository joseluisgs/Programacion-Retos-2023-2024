Para realizar el ejercicio he hecho una matriz, en la cual posicionamos las 3 mesas donde entrarán los clientes, los 3 camareros que se moverán por la matriz y los 3 cocineros para cada camarero.

Una vez comienza la simulación se imprime la matriz para ver la posición de los objetos en el restaurante antes de moverse. Justo debajo irán los comentarios con los distintos eventos que sucedan.

En primer lugar, generaremos los clientes, siempre y cuando no lleguen a 10. Estos se generan de forma aleatoria con un random del 0 al 3 que en caso de ser 3 generará un nuevo cliente.

Una vez se genere un cliente el camarero respectivo a la mesa en la cual se encuentre el cliente comenzará a moverse. Una vez llegue a la mesa se generará un plato aleatorio de una lista de platos la cual simulará la comanda del cliente.

Este camarero una vez recoja el plato volverá a moverse hasta el cocinero, al cual "entregará" el plato y esperará a que lo cocine durante 3 segundos.

Cuando tenga el plato "cocinado" volverá a llevárselo al cliente. Una vez se lo entregue, el cliente tardará 5 segundos en comérselo y el camarero volverá a su posición inicial.

Esto se realizará con los 3 camareros a la vez con sus respectivos clientes.

La simulación acaba una vez el número de clientes llegue a 10 atendidos.

Al terminar la simulación, sacará el número de clientes atendidos y la recaudación total.