package org.example

import org.example.controllers.HeroeController
import org.example.models.Heroe
import kotlin.io.path.*

fun main() {
    val heroeController = HeroeController()

    val directorioActual = System.getProperty("user.dir")
    println("El directorio actual es: $directorioActual")

    val databaseDir = Path(directorioActual, "data")

    if (databaseDir.exists() && databaseDir.isDirectory()) {
        println("El directorio existe")
    } else {
        println("El directorio no existe")
    }

    val dataFile = Path(databaseDir.toString(), "personajes.csv")

    if (dataFile.exists() && !dataFile.isDirectory() && dataFile.extension == "csv" && dataFile.isReadable()) {
        println("El archivo existe")
    } else {
        println("El archivo no existe")
    }

    val heroes = dataFile.readLines()
        .drop(1)
        .map { line -> line.split(",") }
        .map { parts ->
            Heroe(
                id = parts[0].toInt(),
                nickname = parts[1],
                nombre = parts[2],
                edad = parts[3].toInt(),
                vivo = parts[4].toBoolean(),
                villano = parts[5].toBoolean(),
                habilidad = parts[6],
                pc = parts[7].toInt()
            )
        }

    //Habilidad de un personaje específico.
    val idAleatorio = (1..30).random()
    println("⚪Habilidad del personaje con ID: $idAleatorio")
    val busquedaHeroe = heroes.find { it.id == idAleatorio }
    println(busquedaHeroe)
    //Listado de los personajes con ID par.
    println("🔴Personajes con ID par: " + heroes.filter { it.id % 2 == 0 })
    //Personaje Más Viejo.
    println("🟠Personaje más viejo: " + heroes.maxBy { it.edad })
    //Personaje Más Joven.
    println("🟡Personaje más joven: " + heroes.minBy { it.edad })
    //Personajes que hayan fallecido.
    println("🟢Personajes fallecidos: " + heroes.filter { !it.vivo })
    //Villanos en la Base de Datos.
    println("🔵Villanos: " + heroes.filter { it.villano })
    //Héroes Vivos.
    println("🟣Héroes vivos: " + heroes.filter { it.vivo })
    //Personajes con Edad Menor a 40 y Puntos de Combate Mayor a 70.
    println("🟤Personajes con edad menor a 40 y pc mayor a 70: " + heroes.filter { it.edad < 40 && it.pc > 70 })
    //Agrupar Personajes por Habilidad.
    println("⚫Personajes agrupados por habilidad: " + heroes.groupBy { it.habilidad })

    //Listas
    val (miLista, listaMaquina) = heroeController.elegirListasAleatorias(heroes)

    println("Tu lista:")
    println(miLista.toString())

    println("Lista de la Máquina:")
    println(listaMaquina.toString())

    //Combate
    val ganador = heroeController.determinarGanador(miLista, listaMaquina)
    println(ganador)

    heroeController.guardarResultadoBatalla(ganador)
}