package org.example

import com.github.michaelbull.result.*
import org.example.config.Config
import org.example.exceptions.storage.StorageError
import org.example.models.Enemigo
import org.example.models.Guerrero
import org.example.repositories.Personajes.PersonajesRepository
import org.example.services.cache.personajes.PersonajesCache
import org.example.services.personajes.PersonajeServiceImpl
import org.example.services.storage.StoragePersonajesCsv
import org.example.services.storage.StoragePersonajesJson
import org.example.validators.PersonajeValidator

fun main() {
    val personajesService = PersonajeServiceImpl(
        storageCsv = StoragePersonajesCsv(),
        storageJson = StoragePersonajesJson(),
        personajesRepository = PersonajesRepository(),
        personajesCache = PersonajesCache(Config.cacheSize),
        personajeValidator = PersonajeValidator()
    )

    val personajes = personajesService.loadFromCsv().mapBoth(
        success = {
            Ok(it)
        },
        failure = {
            Err(StorageError.LoadError("No se ha podido cargar el csv"))
        }
    )

    personajesService.findAll()
        .onSuccess { personajes ->
            personajes.forEach { println(it) }
        }.onFailure { println("Error: ${it.message}") }

    println("Tres personajes más mayores")
    personajes.onSuccess {it.sortedByDescending { it.edad }.take(3).forEach { println(it) }}
    println()

    println("Agrupar personajes por tipo de arma")
    personajes.onSuccess { it.groupBy { it.arma }.forEach { println(it) } }
    println()

    println("Personajes menores de edad")
    personajes.onSuccess {it.filter { it.edad < 18 }.forEach { println(it) }}
    println()

    println("Personajes ordenados según su ataque")
    personajes.onSuccess { it.sortedByDescending { it.ataque }.forEach { println(it) } }

    println("Mostrar a todos los enemigos")
    personajes.onSuccess { it.filterIsInstance<Enemigo>().forEach { println(it) } }
    println()

    println("Dos personajes con el ataque más fuerte")
    personajes.onSuccess { it.sortedByDescending { it.ataque }.take(2).forEach { println(it) } }

}