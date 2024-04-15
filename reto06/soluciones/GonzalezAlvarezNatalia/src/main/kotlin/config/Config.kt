package org.example.config

import org.lighthousegames.logging.logging
import java.nio.file.Files
import java.util.*
import kotlin.io.path.Path

/**
 * Clase que gestiona la configuración de la aplicación.
 *
 * @author Natalia Gonzalez
 * @since 1.0
 */
private val logger = logging()

/**
 * Objeto singleton que contiene los parámetros de configuración de la aplicación.
 */
object Config {
    /**
     * URL de la base de datos.
     */
    var databaseUrl: String = "jdbc:sqlite:personajes.db"
        private set
    /**
     * Indicador para inicializar las tablas de la base de datos.
     */
    var databaseInitTables: Boolean = false
        private set
    /**
     * Indicador para inicializar los datos en la base de datos.
     */
    var databaseInitData: Boolean = false
        private set
    /**
     * Ruta de almacenamiento de datos.
     */
    var storageData: String = "data"
        private set
    /**
     * Tamaño de la caché.
     */
    var cacheSize: Int = 7
        private set

    init {
        try {
            logger.debug { "Cargando configuración" }
            val properties = Properties()
            properties.load(ClassLoader.getSystemResourceAsStream("config.properties"))
            databaseUrl = properties.getProperty("database.url", this.databaseUrl)
            databaseInitTables =
                properties.getProperty("database.init.tables", this.databaseInitTables.toString()).toBoolean()
            databaseInitData =
                properties.getProperty("database.init.data", this.databaseInitData.toString()).toBoolean()
            storageData = properties.getProperty("storage.data", this.storageData)
            cacheSize = properties.getProperty("cache.size", this.cacheSize.toString()).toInt()
            logger.debug { "Configuración cargada correctamente" }

            Files.createDirectories(Path(storageData))

        } catch (e: Exception) {
            logger.error { "Error cargando configuración: ${e.message}" }
            logger.error { "Usando valores por defecto" }
        }
    }
}
