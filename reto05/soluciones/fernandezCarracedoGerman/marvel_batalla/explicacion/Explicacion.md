# Batalla de Héroes Marvel
### Solución de: Germán Fernández Carracedo
### GitHub: germangfc

<div style="text-align: justify">

### Planteamiento general

### Diseño

Se ha diseñado la aplicación con el siguiente esquema de clases:

- Una clase POJO Heroe con los datos especificados en el enunciado y adecuados para almacenar los datos del fichero CSV.

- Una clase DTO (HeroeDto) para la transferencia de datos al importar y exportar a los ficheros los datos de la lista de personajes.

Usamos un mapeador para transformar los datos de la clase DTO al modelo y viceversa.

Los parámetros de configuración de nuestro programa se almacenan en un fichero, y se cargan en memoria en un Object llamado Config.

Se ha creado un sistema de excepciones orientado a dominio.

Se utiliza un interfaz con las operaciones de almacenamiento en fichero (lectura y escritura), que implementan dos clases distintas, encargadas del almacenamiento en ficheros de formato CSV y JSON respectivamente.

Una clase Bitacora que simplemente implementa una operación de escritura de una línea de texto en un fichero plano, utilizada para guardar resultados de las batallas.

Por último, tenemos un servicio que maneja la lógica de las importaciones y exportaciones de datos a nuestra lista, la escritura en el fichero de bitácora, y de las copias de seguridad. 

Como estructuras de datos tendremos: una lista inmutable de personajes que cargaremos desde el fichero CSV. La utilizaremos para seleccionar las listas de juego aleatorias y para hacer las copias de seguridad.

Tendremos además, los tres ficheros mencionados previamente: CSV que nos dan, JSON auxiliar para crear y restaurar desde backup, y uno de texto plano para la bitácora.


### Diseño Interfaz del programa

Se ha diseñado un menú para ejecutar las operaciones y mostrar los resultados que nos solicitan en la especificación.

1. Consulta de datos.
- Leer Personajes del fichero CSV y cargarlos en una lista
- Mostrar Resultados de las consultas pedidas 

2. Selección Aleatoria de Personajes.  
Se generan y se muestran por pantalla las dos listas aleatorias con las que jugarán el usuario y la máquina.

3. Batalla y Determinación del Ganador.  
Se muestra el resultado del enfrentamiento de las dos listas de personajes y el ganador de la batalla. Para ello se muestran los enfrentamientos individuales entre héroes de ambas listas. 

4. Backup.
- Backup en zip del fichero CSV, pasado previamente a fichero en formato JSON.
- Restaurar copia de zip a CSV, exportando a CSV desde el fichero JSON almacenado en el zip de backup.


</div>

