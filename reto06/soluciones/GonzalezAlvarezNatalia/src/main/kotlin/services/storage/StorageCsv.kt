package org.example.services.storage

import org.example.config.Config
import org.example.exceptions.storage.StorageException
import org.example.models.Personaje
import org.lighthousegames.logging.logging
import kotlin.io.path.Path

private val logger = logging()

/**
 * Implementación de la interfaz [Storage] para almacenar y cargar datos de tipo [Personaje] en formato CSV.
 *
 * @since 1.0
 * @author Natalia Gonzalez
 */
class StorageCsv : Storage<Personaje> {
    /**
     * Almacena los datos de personajes en un archivo CSV.
     *
     * @param data Lista de personajes a almacenar.
     * @return true si el almacenamiento fue exitoso, false de lo contrario.
     * @throws StorageException.StoreException si ocurre un error durante el almacenamiento.
     * @since 1.0
     * @author Natalia Gonzalez
     */
    override fun store(data: List<Personaje>): Boolean {
        logger.debug { "Guardando personajes en fichero CSV" }
        try {
            val file = Path(Config.storageData, "personajes-back.csv").toFile()
            file.writeText("tipo,nombre,habilidad,ataque,edad,arma\n")
            data.forEach {
                file.appendText("${it.tipo},${it.nombre},${it.habilidad},${it.ataque},${it.edad},${it.arma}\n")
            }
            logger.debug { "Guardado correctamente" }
            return true
        } catch (e: Exception) {
            logger.error { "Error al guardar el fichero CSV de personajes: ${e.message}" }
            throw StorageException.StoreException("Error al guardar el fichero CSV de personajes: ${e.message}")
        }
    }

    /**
     * Carga los datos de personajes desde un archivo CSV.
     *
     * @param fileName Nombre del archivo CSV desde el cual cargar los datos.
     * @return Lista de personajes cargados.
     * @throws StorageException.LoadException si ocurre un error durante la carga de datos.
     * @since 1.0
     * @author Natalia Gonzalez
     */
    override fun load(fileName: String): List<Personaje> {
        try {
            logger.debug { "Cargando personajes desde fichero CSV" }
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
                        edad = data[4].toInt(),
                        arma = data[5]
                    )
                }
        } catch (e: Exception) {
            logger.error { "Error al cargar el fichero CSV de personajes: ${e.message}" }
            throw StorageException.LoadException("Error al cargar el fichero CSV de personajes: ${e.message}")
        }
    }
}
