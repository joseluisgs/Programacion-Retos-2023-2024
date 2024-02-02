
Un par de horas después, cuando se terminan de asentar tus pensamientos, te pones a hacer un planeamiento de lo que
necesitas y te das cuenta de que ambas personas te han pedido lo mismo, y solo varía la discreción que Alfred te ha
pedido. Así que decide que el programa que diseñarás será lo más genérico posible para trabajar lo menos posible y que,
a la hora de consultarlo, deberás pedirte una contraseña para mayor seguridad. Dadas las necesidades, deberás crear dos
clases raíz para cada afiliación y, por lo tanto, dos bases de datos distintas, pero con una sola interfaz deberás ser
capaz de acceder a la información de una u otra.

La base de datos deberá almacenar el nombre (alfanumérico) y alias de cada héroe (alfanumérico), una altura (en cm),
edad (numérico) y un apartado para las notas (logitud variable), además de una clave autonumérica, un campo. createAt
que debe almacenar el momento en el que se creó y un updateAt, campo que almacena el momento donde se actualizó. Tenlo
en cuenta para el filtrado de la entrada a la hora de validad posibles campos en la base de datos.

Nuestra base de datos es una matriz inicialmente de 5 elementos que crecerá o disminuirá, según se necesite, sabiendo
que más de 5 elementos vacíos no es lo deseable.

Crea los dos repositorios para los grupos de héroes pedidos, repitiendo la menor cantidad de código posible.

Además, por exigencias de Odín, el repositorio de Marvel deberá implementarse con programación estructurada modular y
objetos, mientras que el de DC debe ser enfocado a programación funcional.

Crea el sistema de errores que consideres oportuno, adaptado al dominio, además de las pruebas pertinentes.