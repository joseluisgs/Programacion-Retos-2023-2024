package org.example.config

import org.lighthousegames.logging.logging
import java.nio.file.Files
import java.util.*
import kotlin.io.path.Path

/**
 * Clase de Configuración con los parámetros de la aplicación
 * que se cargan del fichero config.properties en resources
 * Se define como Object para que solo se pueda tener una instancia única
 */

private val log = logging()

object Config {
    var storageDir: String = "data"
        private set
    var storageFileExportJson: String = "personajes-back.json"
        private set
    var storageFileExportCsv: String = "personajes-back.csv"
        private set
    var storageFileImportCsv: String = "personajes.csv"
        private set
    var storageFileImportJson: String = "personajes.json"
        private set
    var cacheSize: Int = 7
        private set
    var databaseUrl: String = "jdbc:sqlite:personajes.db"
        private set
    var databaseName: String = "personajes.db"
        private set
    var databaseInitTables: Boolean = false
        private set
    var databaseInitData: Boolean = false
        private set

    init {
        try {

            log.debug { "Cargando configuración" }

            val properties = Properties()
            properties.load(ClassLoader.getSystemResourceAsStream("config.properties"))

            storageDir = properties.getOrDefault("storage.dir", storageDir).toString()
            storageFileExportJson = properties.getProperty("storage.file.exportJson", storageFileExportJson)
            storageFileExportCsv = properties.getProperty("storage.file.exportCsv", storageFileExportCsv)
            storageFileImportCsv = properties.getProperty("storage.file.importCsv", storageFileImportCsv)
            storageFileImportJson = properties.getProperty("storage.file.importJson", storageFileImportJson)
            cacheSize = properties.getProperty("cache.size", cacheSize.toString()).toInt()
            databaseUrl = properties.getProperty("database.url", this.databaseUrl)
            databaseName = properties.getProperty("database.name", this.databaseName)
            databaseInitTables =
                properties.getProperty("database.init.tables", this.databaseInitTables.toString()).toBoolean()
            databaseInitData =
                properties.getProperty("database.init.data", this.databaseInitData.toString()).toBoolean()

            log.debug { "Configuración cargada correctamente" }

            // crear el directorio si no existe
            Files.createDirectories(Path(storageDir))

        } catch (e: Exception) {
            log.error { "Error cargando configuración: ${e.message}" }
            log.error { "Usando valores por defecto" }
        }
    }
}