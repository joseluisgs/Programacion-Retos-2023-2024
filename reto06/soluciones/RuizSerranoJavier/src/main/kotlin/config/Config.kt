package org.example.config


import org.lighthousegames.logging.logging
import java.nio.file.Files
import java.util.*
import kotlin.io.path.Path


private val log = logging()
object Config {

    var databaseUrl: String = "jdbc:sqlite:personas.db"
        private set
    var databaseInitTables: Boolean = false
        private set
    var databaseInitData: Boolean = false
        private set
    var storageData: String = "data"
        private set
    init{
        try {
            log.debug { "Cargando configuración" }

            val properties = Properties()

            properties.load(ClassLoader.getSystemResourceAsStream("config.properties"))

            databaseUrl = properties.getProperty("database.url", this.databaseUrl)
            databaseInitTables =
                properties.getProperty("database.init.tables", this.databaseInitTables.toString()).toBoolean()
            databaseInitData =
                properties.getProperty("database.init.data", this.databaseInitData.toString()).toBoolean()

            storageData = properties.getProperty("storage.data", this.storageData)

            log.debug { "Configuración cargada correctamente" }

            // crear el directorio si no existe
            Files.createDirectories(Path(storageData))

            log.debug { "Directorio Data creado correctamente" }

        } catch (e: Exception) {
            log.error { "Error cargando configuración: ${e.message}" }
            log.error { "Usando valores por defecto" }
        }
    }
}