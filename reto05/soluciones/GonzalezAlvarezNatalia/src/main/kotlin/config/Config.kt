package org.example.config

import org.lighthousegames.logging.logging
import java.util.*

private val logger = logging()

/**
 * Objeto que almacena la configuración de la aplicación.
 *
 * @property storageDir Directorio de almacenamiento.
 * @property storageFile Nombre del archivo de almacenamiento.
 * @property cacheSize Tamaño de la caché.
 * @property storageBackupDir Directorio de respaldo.
 * @property storageBackupFile Nombre del archivo de respaldo.
 * @author Natalia González Álvarez
 * @since 1.0
 */
object Config {
    var storageDir: String = "data"
        private set
    var storageFile: String = "heroes.json"
        private set
    var cacheSize: Int = 5
        private set
    var storageBackupDir: String = "backup"
        private set
    var storageBackupFile: String = "heroes.zip"
        private set

    init {
        val properties = Properties()
        properties.load(ClassLoader.getSystemResourceAsStream("config.properties"))
        logger.debug { "Cargando configuración" }
        storageDir = properties.getOrDefault("storage.dir", storageDir).toString()
        storageFile = properties.getProperty("storage.file", storageFile)
        cacheSize = properties.getProperty("cache.size", cacheSize.toString()).toInt()
        storageBackupDir = properties.getProperty("storage.backup.dir", storageBackupDir)
        storageBackupFile = properties.getProperty("storage.backup.file", storageBackupFile)
    }
}
