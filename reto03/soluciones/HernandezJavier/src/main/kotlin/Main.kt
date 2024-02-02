package org.example

import org.example.models.DcHero
import org.example.models.MarvelHero
import org.example.repositories.heroes.DcRepository
import org.example.repositories.héroes.MarvelRepository
import org.example.services.DcHeroServiceImpl
import org.example.services.MarvelServiceImpl
import org.example.validators.HeroValidator
import java.time.LocalDateTime

fun main() {
    val dcRepository = DcRepository()
    val marvelRepository = MarvelRepository()
    val heroValidator = HeroValidator()
    val dcHeroServiceImpl = DcHeroServiceImpl(dcRepository, heroValidator)
    val marvelServiceImpl = MarvelServiceImpl(marvelRepository, heroValidator)

    println("Bienvenido a la Data Base de los Super Héroes")
    do{
        println("A que base de datos quiere acceder?")
        println("Marvel, Pulse 1")
        println("Dc, Pulse 2")
        println("Para salir, Pulse 3")
        println("Introduzca su opción")
        val input = readln().trim().toIntOrNull() ?: -1
        when(input){
            1 -> marvelMenu(marvelServiceImpl)
            2 -> dcMenu(dcHeroServiceImpl)
            3 -> println("Hasta luego")
            else -> println("Opción no válida")
        }
        println()
    }while (input != 3 )
}

/**
 * Menú para gestionar los datos de DC
 * @param dcHeroServiceImpl el servicio que controla el repositorio
 */
fun dcMenu(dcHeroServiceImpl: DcHeroServiceImpl) {
    do{
        println("Para comprobar los datos guardados, pulse 1")
        println("Para buscar por el id, pulse 2")
        println("Para guardar un registro, pulse 3")
        println("Para eliminar un registro, pulse 4")
        println("Para actualizar un registro, pulse 5")
        println("Para salir al menú, pulse 6")
        println("Introduzca su opción")
        val input = readln().trim().toIntOrNull() ?: -1
        when(input){
            1 -> dcHeroServiceImpl.getAll().forEach { hero -> println(hero)  }
            2 -> searchIdDc(dcHeroServiceImpl)
            3 -> saveDc(dcHeroServiceImpl)
            4 -> deleteDc(dcHeroServiceImpl)
            5 ->UpdateDc(dcHeroServiceImpl)
            6 -> println("Saliendo al menú")
            else -> println("Opción no válida")
        }
    }while (input != 6)
}

/**
 * Interfaz para introducir los datos del update en el campo deseado
 * @param dcHeroServiceImpl el servicio que controla el repositorio
 */
private fun UpdateDc(dcHeroServiceImpl: DcHeroServiceImpl) {
    println("Introduzca id:")
    val id = readln().trim().toIntOrNull() ?: -1
    println()
    println("Introduzca nombre")
    val nombre = readln()
    println()
    println("Intrduzca Alias")
    val alias = readln()
    println()
    println("Introduzca edad")
    val edad = readln().toIntOrNull() ?: -1
    println()
    println("Introduzca estatura")
    val altura = readln().toIntOrNull() ?: -1
    println()
    println("Introduzca Notas")
    val notas = readln()
    val hero = DcHero(
        nombre = nombre,
        alias = alias,
        altura = altura,
        edad = edad,
        notas = notas,
        createdAt = LocalDateTime.now(),
        updatedAt = LocalDateTime.now()
    )
    dcHeroServiceImpl.update(id, hero)?.let { hero -> println(hero) } ?: println("No existe el héroe")
}

/**
 * Interfaz para llevar a cabo el delete con una id
 * @param dcHeroServiceImpl el servicio que controla el repositorio
 */
private fun deleteDc(dcHeroServiceImpl: DcHeroServiceImpl) {
    println("Introduzca id:")
    val id = readln().trim().toIntOrNull() ?: -1
    dcHeroServiceImpl.delete(id)
}

/**
 * Interfaz para llevar a cabo el save introduciendo los parametros deseados
 * @param dcHeroServiceImpl el servicio que controla el reopsitorio
 */
private fun saveDc(dcHeroServiceImpl: DcHeroServiceImpl) {
    println("Introduzca nombre")
    val nombre = readln()
    println()
    println("Intrduzca Alias")
    val alias = readln()
    println()
    println("Introduzca edad")
    val edad = readln().toIntOrNull() ?: -1
    println()
    println("Introduzca estatura")
    val altura = readln().toIntOrNull() ?: -1
    println()
    println("Introduzca Notas")
    val notas = readln()
    val hero = DcHero(
        nombre = nombre,
        alias = alias,
        altura = altura,
        edad = edad,
        notas = notas,
        createdAt = LocalDateTime.now(),
        updatedAt = LocalDateTime.now()
    )
    dcHeroServiceImpl.save(hero)
}
/**
 * Interfaz para buscar mediante la introducción de un ID
 * @param dcHeroServiceImpl el servicio que controla el repositorio
 */
private fun searchIdDc(dcHeroServiceImpl: DcHeroServiceImpl) {
    println("Introducza id:")
    val id = readln().trim().toIntOrNull() ?: -1
    dcHeroServiceImpl.getById(id)?.let { hero -> println(hero) } ?: println("No existe héroe")
}
/**
 * Interfaz para buscar mediante la introducción de un ID
 * @param marvelServiceImpl el servicio que controla el repositorio
 */
private fun searchIdMarvel(marvelServiceImpl: MarvelServiceImpl) {
    println("Introducza id:")
    val id = readln().trim().toIntOrNull() ?: -1
    marvelServiceImpl.getById(id)?.let { hero -> println(hero) } ?: println("No existe héroe")
}
/**
 * Interfaz para llevar a cabo el save introduciendo los parametros deseados
 * @param marvelServiceImpl el servicio que controla el reopsitorio
 */
private fun saveMarvel(marvelServiceImpl: MarvelServiceImpl) {
    println("Introduzca nombre")
    val nombre = readln()
    println()
    println("Intrduzca Alias")
    val alias = readln()
    println()
    println("Introduzca edad")
    val edad = readln().toIntOrNull() ?: -1
    println()
    println("Introduzca estatura")
    val altura = readln().toIntOrNull() ?: -1
    println()
    println("Introduzca Notas")
    val notas = readln()
    val hero = MarvelHero(
        nombre = nombre,
        alias = alias,
        altura = altura,
        edad = edad,
        notas = notas,
        createdAt = LocalDateTime.now(),
        updatedAt = LocalDateTime.now()
    )
    marvelServiceImpl.save(hero)
}
/**
 * Interfaz para llevar a cabo el delete con una id
 * @param marvelServiceImpl el servicio que controla el repositorio
 */
private fun deleteMarvel(marvelServiceImpl: MarvelServiceImpl) {
    println("Introduzca id:")
    val id = readln().trim().toIntOrNull() ?: -1
    marvelServiceImpl.delete(id)
}
/**
 * Interfaz para introducir los datos del update en el campo deseado
 * @param marvelServiceImpl el servicio que controla el repositorio
 */
private fun UpdateMarvel(marvelServiceImpl: MarvelServiceImpl) {
    println("Introduzca id:")
    val id = readln().trim().toIntOrNull() ?: -1
    println()
    println("Introduzca nombre")
    val nombre = readln()
    println()
    println("Intrduzca Alias")
    val alias = readln()
    println()
    println("Introduzca edad")
    val edad = readln().toIntOrNull() ?: -1
    println()
    println("Introduzca estatura")
    val altura = readln().toIntOrNull() ?: -1
    println()
    println("Introduzca Notas")
    val notas = readln()
    val hero = MarvelHero(
        nombre = nombre,
        alias = alias,
        altura = altura,
        edad = edad,
        notas = notas,
        createdAt = LocalDateTime.now(),
        updatedAt = LocalDateTime.now()
    )
    marvelServiceImpl.update(id, hero)?.let { hero -> println(hero) } ?: println("No existe el héroe")
}
/**
 * Menú para gestionar los datos de Marvel
 * @param marvelServiceImpl el servicio que controla el repositorio
 */
fun marvelMenu(marvelServiceImpl: MarvelServiceImpl) {
    do{
        println("Para comprobar los datos guardados, pulse 1")
        println("Para buscar por el id, pulse 2")
        println("Para guardar un registro, pulse 3")
        println("Para eliminar un registro, pulse 4")
        println("Para actualizar un registro, pulse 5")
        println("Para salir al menú, pulse 6")
        println("Introduzca su opción")
        val input = readln().trim().toIntOrNull() ?: -1
        when(input){
            1 -> marvelServiceImpl.getAll().forEach { hero -> println(hero) }
            2 -> searchIdMarvel(marvelServiceImpl)
            3 -> saveMarvel(marvelServiceImpl)
            4 -> deleteMarvel(marvelServiceImpl)
            5 -> UpdateMarvel(marvelServiceImpl)
            6 -> println("Saliendo al menú")
            else -> println("Opción no válida")
        }
    }while (input != 6)
}
