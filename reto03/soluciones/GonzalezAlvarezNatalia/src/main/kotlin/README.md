# Explicación del programa

***

### Creación de clases y repositorios

Creamos dos clases en el paquete **models**: _Registro()_ y _Superheroe()_.

* _Registro()_: representa la base de datos al completo. Recoge todos los métodos para llevar a cabo la aplicación y llama 
a los repositorios necesarios.
* _Superheroe()_: representa los datos que contendrán los superhéroes que se añadan a la base de datos.

Añadimos al paquete **validators** la clase _ContraseñaValidator()_.

* _ContraseñaValidator()_: validamos que la contraseña que se introduzca sea válida ("marvel" o "dc").

El paquete **repositories** contiene dos apartados:

* Paquete **base**: nos encontramos con la interfaz CrudRepository.
  * _CrudRepository_: almacena todos los métodos necesarios para llevar a cabo el método CRUD.
* Paquete **superheroes**: se compone de los dos repositorios para cada base de datos.
  * _DcRepository_: base de datos de DC implementada con programación funcional.
  * _MarvelRepository_: base de datos de Marvel implementada con programación estructurada, modular y orientada a objetos.