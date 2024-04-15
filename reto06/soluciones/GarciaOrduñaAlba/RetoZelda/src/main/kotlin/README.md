# Reto Zelda

Este ejercicio consiste gestionar un conjunto de personajes, permitiendo cargarlos desde un archivo CSV, almacenarlos en una base de datos SQLite, realizar consultas sobre los personajes y guardarlos en formatos CSV y JSON.

## Estructura del Proyecto

El proyecto está estructurado de la siguiente manera:

- **src/main/kotlin**: Contiene el código fuente del proyecto.
    - **model**: Contiene la definición de la clase abstract`Personajes` junto con las clases `Guerrero` y `Enemigo`.
    - **repository**: Contiene la interfaz y la implementación del repositorio para acceder a los datos de los personajes.
    - **service**: Contiene la interfaz y la implementación del servicio para realizar operaciones sobre los personajes.
    - **storage**: Contiene la interfaz y las implementaciones de los componentes de almacenamiento para guardar y cargar los personajes desde archivos CSV y JSON.
- **src/main/resources**: Contiene los archivos CSV y JSON de ejemplo, así como los scripts SQL para inicializar la base de datos.
- **config.properties**: Archivo de configuración que contiene la configuración de la base de datos y del almacenamiento de datos.

## Funcionamiento del Programa

El programa funciona de la siguiente manera:

1. **Cargar Personajes desde CSV**: Lee los personajes desde un archivo CSV y los carga en una lista.
2. **Almacenar Personajes en la Base de Datos**: Guarda cada personaje en una base de datos SQLite.
3. **Consultar y Mostrar Personajes**: Realiza consultas sobre los personajes almacenados en la base de datos y los muestra en la consola.
4. **Guardar Personajes en CSV y JSON**: Guarda los personajes en archivos CSV y JSON.
5. **Realizar Consultas Adicionales**: Realiza consultas adicionales sobre los personajes, como encontrar los más mayores, agruparlos por tipo de arma, etc.
