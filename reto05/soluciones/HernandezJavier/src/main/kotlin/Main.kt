package org.example

import org.example.models.Hero
import org.example.services.Backup.BackUpImpl
import org.example.services.HeroService
import org.example.storage.HeroStorage
import org.example.storage.InformeStorageJson

fun main() {
    println("Batalla de héores Marvel")
    val heroStorage = HeroStorage()
    val listaHeroes = heroStorage.readFromFile()
    println(listaHeroes)
    println("Ingrese el ID del personaje: ")
    val input = readln().trim().toIntOrNull() ?: 0
    listaHeroes.filter { it.id == input }.map { it.habilidad}.forEach { println(it) }
    println()
    println("Listado de personajes con id par")
    listaHeroes.filter { it.id % 2 == 0 }.forEach { println(it) }
    println()
    println("Personaje más viejo")
    listaHeroes.maxBy { it.edad }.also { println("${it.nombre}, ${it.edad}") }
    println()
    println("Personaje más joven")
    listaHeroes.minBy { it.edad }.also { println(it) }
    println()
    println("Personajes que hayan fallecido")
    listaHeroes.filter { !it.vivo }.forEach { println(it) }
    println()
    println("Personajes que son villanos")
    listaHeroes.filter { it.villano }.forEach { println(it) }
    println()
    println("Personajes que están vivos")
    listaHeroes.filter { it.vivo }.forEach { println(it) }
    println()
    println("Personajes con edad mayor a 40 y puntos de combate mayor a 70")
    listaHeroes.filter { it.edad > 40 && it.pc > 70}.forEach { println(it) }
    println()
    println("Personajes que no son heroes")
    listaHeroes.filter { it.villano }.forEach { println(it) }
    println()
    println("Agrupación de personajes por habilidad")
    listaHeroes.groupBy { it.habilidad }.forEach { (habilidad, lista) ->
        val numero = lista.count()
        println("$habilidad : $numero")
    }
    println()
    println("Enfrentamiento")
    val informe = InformeStorageJson()
    val backup = BackUpImpl()
    val servicio = HeroService(listaHeroes, informe, backup )
    servicio.enfrentamiento()

}

