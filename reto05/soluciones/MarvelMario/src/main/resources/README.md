<div align="center">
  <h1 style="font-size: 85px;">Batalla de Héroes Marvel</h1>
  <img src="https://i.pinimg.com/originals/36/bd/3d/36bd3d1ea1a751714c9bb42502ab74b1.png" alt="Héroes Marvel" style="width: 75%;">
</div>

## Descripción

¡Bienvenido al desafío de la "Batalla de Héroes Marvel"! En este desafío, te enfrentarás a la máquina en una batalla
de personajes Marvel. Tu objetivo es realizar consultas sobre un archivo CSV (El archivo CSV contiene información
sobre los personajes de Marvel, incluyendo su NickName, Nombre, Edad, si el personaje sigue Vivo, Villano, Habilidad y
Puntos de Combate), después, seleccionar aleatoriamente dos listas de personajes: una para ti y otra para la máquina. Al final,
compararás los puntos acumulados por cada índice de las listas para determinar quién ganó la batalla.
Los resultados de cada batalla deberán salvarse en un fichero de bitacora.txt, donde muestre por cada batalla realizada la fecha de realización y puntuación de cada participante y quien ha ganado.
Además. deberás poder hacer un backup de los datos de los personajes en formato zip para poder importarlos o exportarlos.
Recuerda que todos los directorios importartes para el uso de la app deben estar descritos en el fichero config.properties.

## Tareas

### 1. Consulta de Datos

- **Habilidad de un Personaje Específico:**
  Solicita al usuario que ingrese el ID de un personaje y muestra sus habilidades.

- **Listado de los personajes con ID par**

- **Personaje Más Viejo:**mostrando solo su nombre y edad.

- **Personaje Más Joven**

- **Personajes que hayan fallecido**

- **Villanos en la Base de Datos:**
  Filtra y muestra la lista de todos los personajes que son villanos.

- **Héroes Vivos:**
  Encuentra y muestra la lista de todos los héroes que están actualmente vivos.

- **Personajes con Edad Menor a 40 y Puntos de Combate Mayor a 70**

- **Personajes que no son no Héroes:**
  Identifica y muestra la lista de personajes que son villanos.

- **Agrupar Personajes por Habilidad:**
  Realiza un conteo de cuántos personajes tienen cada habilidad y muestra los resultados.

### 2. Selección Aleatoria

- Implementa una función para seleccionar aleatoriamente una lista de personajes para ti y otra para la máquina.
  - 5 personajes por lista.
  - El personaje tiene que estar vivo.
  - No se pueden repetir personajes entre listas.

### 3. Determinación del Ganador

- Compara las puntuaciones individuales de cada héroe en el mismo índice de ambas listas. Por ejemplo, compara quién
  ganaría en un enfrentamiento directo entre el héroe 1 de tu lista y el héroe 1 de la lista de la máquina.

- Anuncia al ganador de la "Batalla de Héroes Marvel" basándote en la suma total de puntuaciones de cada lista.
  Es decir, evalúa quién tiene un rendimiento global mejor considerando las victorias en los enfrentamientos individuales
  entre héroes de ambas listas.

### 4. Backup

- Debe srealizar un backup de los datos en formato .zip, pero teniendo en cuenta que dentro de este zip estarán los datos en formato json. Deberás ser capazy mostrar como salvar los datos a este backup o restaurarlos.
