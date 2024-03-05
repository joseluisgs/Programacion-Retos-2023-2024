
## Creación de clases

El proyecto está estructurado en varios paquetes:

* **_controllers_**: Contiene las clases que manejan la lógica del juego.
* **_dto_**: Contiene las clases DTO utilizadas para transferir datos entre capas.
* **_mappers_**: Contiene funciones de mapeo para convertir entre diferentes tipos de objetos.
* **_models_**: Contiene las clases que definen el modelo de datos del juego.

## Clase Main

En la clase Main se encuentran las diferentes consultas:

* **_Habilidad de un Personaje Específico_**: Solicita al usuario el ID de un personaje y muestra sus habilidades. 
* **_Listado de los Personajes con ID Par_**: Muestra los personajes cuyo ID es par. 
* **_Personaje Más Viejo_**: Muestra el nombre y la edad del personaje más viejo. 
* **_Personaje Más Joven_**: Muestra el nombre y la edad del personaje más joven. 
* **_Personajes que han Fallecido_**: Muestra la lista de personajes que han fallecido. 
* **_Villanos en la Base de Datos_**: Muestra la lista de todos los personajes que son villanos. 
* **_Héroes Vivos_**: Muestra la lista de todos los héroes que están actualmente vivos. 
* **_Personajes con Edad Menor a 40 y Puntos de Combate Mayor a 70_**: Muestra la lista de personajes con edad menor a 40 y puntos de combate mayor a 70. 
* **_Personajes que no son Héroes_**: Muestra la lista de personajes que son villanos. 
* **_Agrupar Personajes por Habilidad_**: Cuenta cuántos personajes tienen cada habilidad y muestra los resultados.

La selección de listas aleatorias para el jugador y para la máquina.

La determinación del ganador de los diferentes combates entre los héroes.

## Archivo bitacora.txt

Los resultados de los combates entre los héroes se almacenan en el archivo en formato txt: bitacora.txt.

Para ello en la clase _HeroeController()_ tenemos la función guardarResultadoBatalla(), donde mediante la función
appendText() podemos ir guardando los diferentes datos sin sobreescribir los anteriores.