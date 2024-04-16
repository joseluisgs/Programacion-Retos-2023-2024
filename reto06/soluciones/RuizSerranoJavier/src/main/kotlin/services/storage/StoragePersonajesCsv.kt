package org.example.services.storage

import org.example.config.Config
import org.example.exceptions.storage.StorageException
import org.example.models.Personaje
import org.lighthousegames.logging.logging
import kotlin.io.path.Path

private val log = logging()
class StoragePersonajesCsv: Storage<Personaje> {
    override fun store(data: List<Personaje>): Boolean {
        log.debug { "Guardando personas en fichero csv" }
        try {
            val file = Path(Config.storageData, "personajes-back.csv").toFile()
            file.writeText("tipo,nombre,habilidad,ataque,edad,arma\n")
            data.forEach {
                    file.appendText("${it.tipo},${it.nombre},${it.habilidad},${it.ataque},${it.edad},${it.arma}\n")
                }
            log.debug { "Guardado correctamente" }
            return true
        } catch (e: Exception) {
            log.error { "Error al guardar el fichero csv de personas: ${e.message}" }
            throw StorageException.StoreException("Error al guardar el fichero csv de personas: ${e.message}")
        }
    }

    override fun load(fileName: String): List<Personaje> {
        try {
            log.debug { "Cargando personas desde fichero csv" }
            val file = Path(Config.storageData, fileName).toFile()
            if (!file.exists()) throw StorageException.LoadException("El fichero $fileName no existe")
            return file.readLines().drop(1)
                .map {
                    val data = it.split(",")
                    Personaje(
                        tipo = data[0],
                        nombre = data[1],
                        habilidad = data[2],
                        ataque = data[3],
                        edad = data[4],
                        arma = data[5]
                    )
                }
        } catch (e: Exception) {
            log.error { "Error al cargar el fichero csv de personas: ${e.message}" }
            throw StorageException.LoadException("Error al cargar el fichero csv de personas: ${e.message}")
        }
    }
}