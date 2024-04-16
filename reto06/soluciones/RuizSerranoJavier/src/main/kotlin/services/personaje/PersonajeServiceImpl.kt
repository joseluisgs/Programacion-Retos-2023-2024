package org.example.services.personaje

import org.example.models.Personaje
import org.example.repositories.personaje.PersonajeRepository
import org.example.services.storage.Storage
import org.lighthousegames.logging.logging


private val log = logging()
class PersonajeServiceImpl
    (val storageCsv: Storage<Personaje>,
     val storageJson: Storage<Personaje>,
     val personajesRepository: PersonajeRepository
) : PersonajeService {
    override fun loadFromCsv(): List<Personaje> {
        log.debug { "Cargando personas desde CSV" }
        return storageCsv.load("personajesGpt.csv")
    }

    override fun storeToCsv(personas: List<Personaje>) {
        log.debug { "Guardando personas en CSV" }
        storageCsv.store(personas)
    }

    override fun loadFromJson(): List<Personaje> {
        log.debug { "Cargando personas desde JSON" }
        return storageJson.load("personas-back.json")
    }

    override fun storeToJson(personas: List<Personaje>) {
        log.debug { "Guardando personas en JSON" }
        storageJson.store(personas)
    }

    override fun findAll(): List<Personaje> {
        log.debug { "Buscando todas las personas" }
        return personajesRepository.findAll()
    }

    override fun findByTipo(tipo: String): List<Personaje> {
        log.debug { "Buscando personas por tipo $tipo" }
        return personajesRepository.findByTipo(tipo)
    }
}