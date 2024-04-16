# Leyendas de Hyrule: El Repositorio de Personajes
### Solución de: Germán Fernández Carracedo
### GitHub: germangfc

<div style="text-align: justify">

### Planteamiento general

### Diseño

Se ha diseñado la aplicación con el siguiente esquema de clases:

- Una clase POJO Personaje con los datos especificados en el enunciado.

- Una clase DTO para las transferencias a y desde la base de datos, así como al importar y exportar a los ficheros de personajes.

Usamos un mapeador para transformar los datos de la clase DTO al modelo y viceversa.

Los parámetros de configuración de nuestro programa se almacenan en un fichero, y se cargan en memoria en un Object llamado Config.

Se ha creado un sistema de excepciones orientado a dominio.

Se utiliza un interfaz con las operaciones de almacenamiento en fichero (lectura y escritura), que implementan dos clases distintas, encargadas del almacenamiento en ficheros de formato CSV y JSON respectivamente.

Una caché para optimizar los accesos a la base de datos. El servicio de personajes es el que intenta localizar los datos en la caché antes de hacerlo en la base de datos, evitando accesos innecesarios. 

Tenemos una clase que implementa un repositorio que accede a una base de datos SQLite, con las operaciones CRUD habituales y una opción añadida para localizar personajes por tipo.

Se ha implementado un validador para comprobar que los datos de los personajes que se almacenen en la base de datos sean correctos.

Por último, tenemos un servicio que que hará de coordinador de las peticiones y entregas de información a los distintos componentes de la aplicación. 


### Diseño Interfaz del programa

Se ha diseñado un menú para ejecutar las operaciones y mostrar los resultados que nos solicitan en la especificación.

### Tests

Se han implementado tests sobre:
- Validador
- Repositorio
- Servicio

Quedarían por implementar tests:
- Caché
- Almacenamiento CSV/JSON

Se adjunta informe de cobertura de tests, en la carpeta **_htmlTestingReport_**.

### Notas de Interés

Se ha implementado aunque no se pedía en el ejercicio, la exportación a CSV y la importación desde JSON, y estas operaciones son funcionales y accesibles desde el menú de opciones.

</div>

