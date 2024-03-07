package org.example.config
import org.lighthousegames.logging.logging
import java.util.*

private val logger = logging()

/**
 * Clase object que almacena los parámetros de configuración del programa, cuando se instancia, carga los valores
 * desde un fichero de configuración incluido en resources.
 * Los parámetros son los nombres y rutas de los distintos archivos utilizados.
 */
object Config {
    var storageFileTemp: String = "personajesTemp.json"
        private set
    var storageBackupDir: String = "backup"
        private set
    var storageBackupFile: String = "personajes.zip"
        private set
    var storageHeroesFile: String = "personajes.csv"
        private set
    var storageHeroesJsonFile: String = "personajes.json"
        private set
    var storageBitacoraFile: String = "bitacora.txt"
        private set
    var storageHeroesDir: String = "data"
        private set

    init {
        val properties = Properties()
        properties.load(ClassLoader.getSystemResourceAsStream("config.properties"))
        logger.debug { "Cargando configuración" }
        storageFileTemp = properties.getProperty("storage.fileTemp", storageFileTemp)
        storageBackupDir = properties.getProperty("storage.backup.dir", storageBackupDir)
        storageBackupFile = properties.getProperty("storage.backup.file", storageBackupFile)
        storageHeroesDir = properties.getProperty("storage.heroes.dir", storageHeroesDir)
        storageHeroesFile = properties.getProperty("storage.heroes.file", storageHeroesFile)
        storageHeroesJsonFile = properties.getProperty("storage.heroes.jsonfile", storageHeroesJsonFile)
        storageBitacoraFile = properties.getProperty("storage.bitacora.file", storageBitacoraFile)
    }
}