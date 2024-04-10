package org.example

import org.example.models.Enemigo
import org.example.repositories.personajes.PersonajesRepositoryImpl
import org.example.services.cache.personajes.PersonajesCache
import org.example.services.personajes.PersonajesServiceImpl
import org.example.services.storage.StoragePersonajesCsv
import org.example.services.storage.StoragePersonajesJson
import org.lighthousegames.logging.logging

private val logger = logging()

fun main() {
    val personajesServicio = PersonajesServiceImpl(
        storageCsv = StoragePersonajesCsv(),
        storageJson = StoragePersonajesJson(),
        personasRepository = PersonajesRepositoryImpl(),
        personasCache = PersonajesCache()
    )

    // Sacamos listado de personajes desde el csv
    val listadoPersonajesCSV = personajesServicio.loadFromCsv()

    // Insertamos en la BBDD los personajes del listado
    listadoPersonajesCSV.forEach { personajesServicio.save(it) }

    // Mostramos los personajes cargados
    println("\nMOSTRANDO TODOS LOS PERSONAJES")
    personajesServicio.findAll().forEach { println(it) }

    // Buscando personaje por nombre
    println("\nMOSTRANDO PERSONAJE LLAMADO ZELDA")
    val p1 = personajesServicio.findByName("Zelda")
    println(p1)

    // Buscando personajes por tipo
    println("\nMOSTRADNO TODOS LOS PERSONAJES DEL TIPO ENEMIGO")
    personajesServicio.findByTipo("Enemigo").forEach { println(it) }

    // Guardamos personajes en CSV de almacenamiento
    personajesServicio.storeToCsv(listadoPersonajesCSV)

    // Generamos personajes en JSON de almacenamiento
    personajesServicio.storeToJson(listadoPersonajesCSV)

    println("\nTres personajes más mayores:")
    listadoPersonajesCSV.sortedByDescending { it.edad }.take(3).forEach { println(it) }

    println("\nAgrupar personajes por tipo de arma:")
    listadoPersonajesCSV.groupBy { it.arma }.forEach { println(it) }

    println("\nPersonajes menores de edad:")
    listadoPersonajesCSV.filter { it.edad < 18 }.forEach { println(it) }

    println("\nPersonajes con más ataque: (primeros)")
    listadoPersonajesCSV.sortedByDescending { it.ataque }.take(3).forEach { println(it) }

    println("\nMostrar a todos los enemigos:")
    listadoPersonajesCSV.filterIsInstance<Enemigo>().forEach { println(it) }

    println("\nDos personajes con el ataque más fuerte:")
    listadoPersonajesCSV.sortedByDescending{ it.ataque }.take(2).forEach { println(it) }

}