package org.example.services.storage.heroe

import org.example.models.Heroe
import org.example.dto.HeroeDto
import org.example.services.storage.base.FileStorage
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File
import org.example.exceptions.storage.StorageExceptions
import org.example.mappers.toHeroe
import org.example.mappers.toHeroeDto
import org.lighthousegames.logging.logging

private val log = logging()

/**
 * Clase que gestiona el almacenamiento de personajes en fichero formato JSON
 */
class HeroeFileStorageJson: FileStorage<Heroe> {

    /**
     * Leemos de un fichero JSON todos los elementos y los devolvemos en una lista de héroes
     * @param file Fichero donde están los datos
     * @return List Lista con los personajes
     */
    override fun readFromFile(file: File): List<Heroe> {
        log.debug { "Leyendo de fichero JSON: $file" }
        if (!file.exists()) {
            log.error { "El fichero no existe: $file" }
            throw StorageExceptions.FileNotFound("El fichero no existe: $file")
        }

        return Json { ignoreUnknownKeys = true }
            .decodeFromString<List<HeroeDto>>(file.readText())
            .map {
                it.toHeroe()
            }
    }

    /**
     * Guardamos en el fichero JSON todos los elementos pasados en la lista de héroes
     * @param list Lista de heroes
     * @param file Fichero donde se guardarán los elementos de la lista
     */
    override fun writeToFile(list: List<Heroe>, file: File) {
        log.debug { "Escribiendo en fichero JSON: $file" }
        file.writeText(
            Json {
                ignoreUnknownKeys = true
                prettyPrint = true
            }.encodeToString<List<HeroeDto>>(
                list.map { it.toHeroeDto() }
            )
        )
    }
}