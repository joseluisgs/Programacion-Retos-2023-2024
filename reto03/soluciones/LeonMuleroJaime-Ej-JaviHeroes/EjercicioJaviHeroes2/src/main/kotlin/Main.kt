package org.example

import org.example.models.Heroe
import org.example.repositories.heroescompany.*

const val REPOMARVEL = "\nRepositorio Marvel"
const val REPODC = "\nRepositorio DC"

val heroesDCRepository = HeroesDCRepository()
val heroesMarvelRepository = HeroesMarvelRepository()

fun main() {
    println("Accediendo a la base de datos de La Liga de la Justicia... ")
    println("Pulsa cualquier tecla para comenzar")
    readln()

    do {
        println("\n¿Qué operación deseas realizar? { 1 - 6 }")
        println("1. Obtener listado completo de héroes")
        println("2. Buscar héroe por ID")
        println("3. Añadir héroe")
        println("4. Actualizar héroe")
        println("5. Borrar héroe")
        println("6. Salir")

        val opcion = readln().toIntOrNull()

        if (opcion != null && opcion != 6) {
            println("\n¿A qué repositorio deseas acceder? { 1 - 2 }")
            println("1. Marvel")
            println("2. DC")

            val repo = readln().toIntOrNull()

            if (repo in 1..2 && repo != null) {
                acciones(opcion, repo)
            }

        }
    } while (opcion == null || opcion != 6)

    println("\nAbandonando la base de datos...")
}

fun acciones(opcion: Int, repo: Int) {
    when (opcion) {
        1 -> {
            when (repo) {
                1 -> {
                    println(REPOMARVEL)
                    heroesMarvelRepository.imprimirArray(heroesMarvelRepository.getAllHeroes())
                }
                2 -> {
                    println(REPODC)
                    heroesDCRepository.imprimirArray(heroesDCRepository.getAllHeroes())
                }
            }
        }

        2 -> {
            println("\nIntroduce la ID del héroe que buscas:")
            val id = readln().toIntOrNull() ?: -1
            when (repo) {
                1 -> {
                    println(REPOMARVEL)
                    heroesMarvelRepository.imprimirHeroe(heroesMarvelRepository.getHeroeById(id))
                }

                2 -> {
                    println(REPODC)
                    heroesDCRepository.imprimirHeroe(heroesDCRepository.getHeroeById(id))
                }
            }
        }

        3 -> {
            println("\nIntroduce los datos de tu héroe:")
            val heroe: Heroe = datosNuevoHeroe()
            when (repo) {
                1 -> {
                    println(REPOMARVEL)
                    val res = heroesMarvelRepository.saveHeroe(heroe)
                    heroesMarvelRepository.imprimirHeroe(res)
                }
                2 -> {
                    println(REPODC)
                    val res = heroesDCRepository.saveHeroe(heroe)
                    heroesDCRepository.imprimirHeroe(res)
                }
            }
        }

        4 -> {
            println("\nIntroduce la ID del héroe para actualizar:")
            val id = readln().toIntOrNull() ?: -1
            println("\nIntroduce los datos de tu heroe")
            val heroe: Heroe = datosNuevoHeroe()
            when (repo) {
                1 -> {
                    println(REPOMARVEL)
                    val res = heroesMarvelRepository.updateHeroe(id, heroe)
                    heroesMarvelRepository.imprimirHeroe(res)
                }

                2 -> {
                    println(REPODC)
                    val res = heroesDCRepository.updateHeroe(id, heroe)
                    heroesDCRepository.imprimirHeroe(res)
                }
            }
        }

        5 -> {
            println("\nIntroduce la ID del héroe para borrar:")
            val id = readln().toIntOrNull() ?: -1
            when (repo) {
                1 -> {
                    println(REPOMARVEL)
                    heroesMarvelRepository.deleteHeroe(id)
                }
                2 -> {
                    println(REPODC)
                    heroesDCRepository.deleteHeroe(id)
                }
            }
        }
    }
}

private fun datosNuevoHeroe(): Heroe {
    val regexNombreAlias = "^([a-zA-Z0-9]){3,25}$".toRegex()
    var nombre: String
    var alias: String
    var altura: Int
    var edad: Int

    do {
        println("\nNombre:")
        nombre = readln()
        if (!nombre.matches(regexNombreAlias)) println("El nombre introducido no es válido ❌")
    } while (!nombre.matches(regexNombreAlias))

    do {
        println("\nAlias:")
        alias = readln()
        if (!alias.matches(regexNombreAlias)) println("El alias introducido no es válido ❌")
    } while (!alias.matches(regexNombreAlias))

    do {
        println("\nAltura (cm):")
        altura = readln().toIntOrNull() ?: -1
        if (altura < 0) println("La altura introducida no es válida ❌")
    } while (altura < 0)

    do {
        println("\nEdad:")
        edad = readln().toIntOrNull() ?: -1
        if (edad < 0) println("La edad introducida no es válida ❌")
    } while (edad < 0)

    println("\nNotas:")
    val notas: String = readln()

    val heroe = Heroe(nombre, alias, altura, edad, notas)

    return heroe
}