package org.example

import org.example.repositories.personaje.PersonajeRepositoryImpl
import org.example.services.personaje.PersonajeServiceImpl
import org.example.services.storage.StoragePersonajesCsv
import org.example.services.storage.StoragePersonajesJson

fun main() {
    println("Hello World!")

    val storageCsv = StoragePersonajesCsv()

    val service = PersonajeServiceImpl(
        StoragePersonajesCsv(),
        StoragePersonajesJson(),
        PersonajeRepositoryImpl(),
    )

    val personajes = storageCsv.load("personajesGpt.csv")

    storageCsv.store(personajes)

    val storageJson = StoragePersonajesJson()

    storageJson.store(personajes)

    storageJson.load("personas-back.json")


    service.findAll().forEach { println(it) }

    val list = service.loadFromCsv()
    println(list)
    service.storeToJson(list)
    service.storeToCsv(list)

    service.findAll().forEach { println(it) }

    service.loadFromCsv().forEach { println(it) }
    service.loadFromJson().forEach { println(it) }
}