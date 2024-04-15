package org.example

import org.example.models.Personaje
import org.example.repositories.personajes.PersonajesRepositoryImpl
import org.example.services.cache.personajes.PersonajesCache
import org.example.services.personajes.PersonajeServiceImpl
import org.example.services.storage.StorageCsv
import org.example.services.storage.StorageJson
import kotlin.io.path.*

fun main() {
    val personajeService = PersonajeServiceImpl(
        storageCsv = StorageCsv(),
        storageJson = StorageJson(),
        personajesRepository = PersonajesRepositoryImpl(),
        personajesCache = PersonajesCache()
    )

    val personas = personajeService.loadFromCsv()
    personas.forEach {
        personajeService.save(it)
    }

    personajeService.findAll().forEach {
        println(it)
    }

    personajeService.storeToJson(personajeService.findAll())

    val directorioActual = System.getProperty("user.dir")
    println("El directorio actual es: $directorioActual")

    val databaseDir = Path(directorioActual, "data")

    if (databaseDir.exists() && databaseDir.isDirectory()) {
        println("El directorio existe")
    } else {
        println("El directorio no existe")
    }

    val dataFile = Path(databaseDir.toString(), "personajes-back.csv")

    if (dataFile.exists() && !dataFile.isDirectory() && dataFile.extension == "csv" && dataFile.isReadable()) {
        println("El archivo existe")
    } else {
        println("El archivo no existe")
    }

    val listaPersonajes = dataFile.readLines()
        .drop(1)
        .map { line -> line.split(",") }
        .map { parts ->
            Personaje(
                tipo = parts[0],
                nombre = parts[1],
                habilidad = parts[2],
                ataque = parts[3],
                edad = parts[4].toInt(),
                arma = parts[5]
            )
        }

    // Tres personajes más mayores.
    println("🔴 Tres personajes más mayores:")
    println(listaPersonajes.sortedByDescending { it.edad }.take(3))

    // Agrupar personajes por tipo de arma.
    println("🟠 Agrupar personajes por tipo de arma:")
    println(listaPersonajes.groupBy { it.arma })

    // Personajes menores de edad.
    println("🟡 Personajes menores de edad:")
    println(listaPersonajes.filter { it.edad < 18 })

    // Personajes con más ataque.
    println("🟢 Personajes con más ataque:")
    println(listaPersonajes.sortedByDescending { it.ataque }.take(3))

    // Mostrar a todos los enemigos.
    println("🔵 Mostrar a todos los enemigos:")
    println(listaPersonajes.filter { it.tipo == "Enemigo" })

    // Dos personajes con el ataque más fuerte.
    println("🟣 Dos personajes con el ataque más fuerte:")
    println(listaPersonajes.sortedByDescending { it.ataque }.take(2))
}