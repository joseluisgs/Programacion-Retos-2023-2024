package org.example.config

import org.lighthousegames.logging.logging
import java.nio.file.Files
import java.util.Properties
import kotlin.io.path.Path

private val logger = logging()
object Config {
    var databaseUrl: String = "jdbc:sqlite:personajes.db"
        private set
    var databaseInitTables: Boolean = false
        private set
    var databaseInitData: Boolean = false
        private set
    var storageData: String = "data"
        private set
    var cacheSize: Int = 5
        private set

    init {
        try {
            logger.debug { "Cargando configuración" }
            val properties = Properties()
            properties.load(ClassLoader.getSystemResourceAsStream("config.properties"))
            databaseUrl = properties.getProperty("database.url", this.databaseUrl)
            databaseInitTables = properties.getProperty("database.init.tables", this.databaseInitTables.toString()).toBoolean()
            databaseInitData = properties.getProperty("database.init.data", this.databaseInitData.toString()).toBoolean()
            storageData = properties.getProperty("storage.data", this.storageData)
            cacheSize = properties.getProperty("cahcheSize", this.cacheSize.toString()).toInt()
            logger.debug { "Configuración cargada correctamente" }
            Files.createDirectories(Path(storageData))
        }catch (e: Exception){
            logger.error { "Error cargando configuración: ${e.message}" }
            logger.error { "Usando valores por defecto" }
        }
    }
}

