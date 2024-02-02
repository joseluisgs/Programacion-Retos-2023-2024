package org.example.models

import org.example.repositories.superheroes.DCRepository
import org.example.repositories.superheroes.MarvelRepository
import org.example.repositories.base.CrudRepository
import org.example.validators.ContraseñaValidator

/**
 * Clase que representa el registro completo de las bases de datos.
 */
class Registro {
    /**
     * condición que indica si el programa sigue o no.
     */
    private var continuar = true

    /**
     * variable que llama a la clase ContraseñaValidator().
     */
    private val contraseñaValidator = ContraseñaValidator()

    /**
     * Función para gestionar las bases de datos de los superhéroes de Marvel y DC.
     *
     * @author Natalia Gonzalez
     * @since 1.0
     */
    fun hacerRegistro() {

        println("¡Bienvenido a la base de datos!")

        println("Ingrese la contraseña:")
        println("Formato de la contraseña: marvel / dc")
        val contraseña = readln().trim()

        while (continuar) {
            val repository = when (contraseña) {
                "dc" -> DCRepository()
                "marvel" -> MarvelRepository()
                else -> {
                    println("Contraseña errónea")
                    break
                }
            }

            if (contraseñaValidator.validar(contraseña)){

                println("¿Qué desea hacer?")

                println("1. Añadir un superhéroe a la base de datos")
                println("2. Ver la base de datos completa")
                println("3. Buscar un superhéroe por su ID")
                println("4. Actualizar a un superhéroe")
                println("5. Eliminar a un superhéroe")
                println("6. Salir")

                val opcion = readln().trim()

                when (opcion) {
                    "1" -> {
                        println("Inserte el id del héroe: ")
                        val id = readln().toInt()

                        println("Inserte el nombre del héroe: ")
                        val nombre = readln()

                        println("Inserte el alias del héroe: ")
                        val alias = readln()

                        println("Inserte la altura del héroe: ")
                        val altura = readln().toInt()

                        println("Inserte la edad del héroe: ")
                        val edad = readln().toInt()

                        println("Inserte notas del héroe: ")
                        val notas = readln()

                        val nuevoSuperheroe = Superheroe(id, nombre, alias, altura, edad, notas)

                        println(nuevoSuperheroe)

                        añadirSuperheroe(nuevoSuperheroe)
                    }

                    "2" -> verSuperheroes(repository)

                    "3" -> verSuperheroe(repository)

                    "4" -> actualizarSuperheroe(repository)

                    "5" -> eliminarSuperheroe(repository)

                    "6" -> {
                        println("¡Adiós!")
                        continuar = false
                    }

                    else -> {
                        println("Opción no válida. Inténtelo de nuevo.")
                    }
                }

                seguirEnLaBaseDeDatos()
            }
        }
    }

    /**
     * Función que pregunta al usuario si quiere seguir en la base de datos.
     *
     * @author Natalia Gonzalez
     * @since 1.0
     */
    fun seguirEnLaBaseDeDatos(){
        if (continuar) {
            println("¿Quiere seguir en la base de datos? (si/no)")
            val afirmacion = readln().uppercase().trim()

            if (afirmacion != "SI") {
                println("¡Adiós!")
                continuar = false
            }
        }
    }

    /**
     * Función que elimina un superhéroe de la base de datos.
     *
     * @param repository Repositorio de superhéroes.
     * @author Natalia Gonzalez
     * @since 1.0
     */
    fun eliminarSuperheroe(repository: CrudRepository<Superheroe>) {
        println("Ingrese el ID del superhéroe a eliminar:")
        val idEliminar = readln().toIntOrNull() ?: 0
        repository.deleteSuperheroe(idEliminar)
    }

    /**
     * Función que actualiza la información de un superhéroe en la base de datos.
     *
     * @param repository Repositorio de superhéroes.
     * @author Natalia Gonzalez
     * @since 1.0
     */
    fun actualizarSuperheroe(repository: CrudRepository<Superheroe>){
        println("Ingrese el ID del superhéroe a actualizar:")
        val idActualizar = readln().toIntOrNull() ?: 0
        val superheroeActualizar = repository.getSuperheroeById(idActualizar)
        if (superheroeActualizar != null) {
            val heroeActualizado = repository.updateSuperheroe(idActualizar, superheroeActualizar)
            println("Superhéroe actualizado: $heroeActualizado")
        }
    }

    /**
     * Función que muestra la información de un superhéroe en la base de datos.
     *
     * @param repository Repositorio de superhéroes.
     * @author Natalia Gonzalez
     * @since 1.0
     */
    fun verSuperheroe(repository: CrudRepository<Superheroe>) {
        println("Ingrese el ID del superhéroe a buscar:")
        val idBuscar = readln().toIntOrNull() ?: 0
        val superheroeBuscado = repository.getSuperheroeById(idBuscar)
        println("Superhéroe encontrado:$superheroeBuscado")
    }

    /**
     * Función que muestra la información de todos los superhéroes en la base de datos.
     *
     * @param repository Repositorio de superhéroes.
     * @author Natalia Gonzalez
     * @since 1.0
     */
    fun verSuperheroes(repository: CrudRepository<Superheroe>) {
        val allSuperheroes = repository.getSuperheroes()
        allSuperheroes.forEach { println(it) }
    }

    /**
     * array de superhéroes.
     */
    private var superheroes : Array<Superheroe?> = arrayOfNulls<Superheroe>(5)
    /**
     * variable que asigna el próximo id al siguiente superhéroe.
     */
    private var nextId = 0

    /**
     * Función que añade un nuevo superhéroe a la base de datos.
     *
     * @param superheroe superhéroe que estemos añadiendo.
     * @author Natalia Gonzalez
     * @since 1.0
    */
    fun añadirSuperheroe(superheroe: Superheroe) {
        val newHeroe = superheroe.copy(id = nextId++)
        val newHeroes = Array(superheroes.size + 1) { i ->
            if (i < superheroes.size) {
                superheroes[i]
            } else {
                newHeroe
            }
        }
        superheroes = newHeroes
    }
}