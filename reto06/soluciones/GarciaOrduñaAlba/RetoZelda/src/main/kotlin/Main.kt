package org.example

import StoragePersonajeCsv
import org.example.models.Enemigo
import org.example.repositories.personaje.PersonajeRepositoryImpl
import org.example.service.cache.personaje.PersonajeCache
import org.example.service.personaje.PersonajeServiceImpl
import org.example.service.storage.StoragePersonajeJson

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
/**
 * Función principal que demuestra el uso del servicio de personajes para cargar, almacenar y consultar datos de personajes.
 * @param args Argumentos de línea de comandos
 * @author Alba Garcia Orduña
 * @version 1.1
 */
fun main() {
    val personajesServicio = PersonajeServiceImpl(
        storageCsv = StoragePersonajeCsv(),
        storageJson = StoragePersonajeJson(),
        personasRepository = PersonajeRepositoryImpl(),
        personasCache = PersonajeCache()
    )

    // Sacar listado personajes
    val listadoPersonajesCSV = personajesServicio.loadFromCsv()

    // Insterar BBDD
    listadoPersonajesCSV.forEach { personajesServicio.save(it) }

    // Mostramos todos los personajes
    println("\nMostrando personajes...:")
    personajesServicio.findAll().forEach { println(it) }

    personajesServicio.storeToCsv(listadoPersonajesCSV)
    personajesServicio.storeToJson(listadoPersonajesCSV)

    println("🟠🟠Consultas🟠🟠")

    println("\nTres personajes más mayores:")
    listadoPersonajesCSV.sortedByDescending { it.edad }.take(3).forEach { println(it) }

    println("\nAgrupar personajes por tipo de arma:")
    listadoPersonajesCSV.groupBy { it.arma }.forEach { println(it) }

    println("\nPersonajes menores de edad:")
    listadoPersonajesCSV.filter { it.edad < 18 }.forEach { println(it) }

    println("\nPersonajes con más ataque:")
    listadoPersonajesCSV.sortedByDescending { it.ataque }.take(3).forEach { println(it) }

    println("\nMostrar a todos los enemigos:")
    listadoPersonajesCSV.filterIsInstance<Enemigo>().forEach { println(it) }

    println("\nDos personajes con el ataque más fuerte:")
    listadoPersonajesCSV.sortedByDescending{ it.ataque }.take(2).forEach { println(it) }

}
