package org.example.config

import org.lighthousegames.logging.logging
import java.nio.file.Files
import java.util.*
import kotlin.io.path.Path

private val logger = logging()

/**
 * Objeto singleton que maneja la configuración de la aplicación.
 */
object Config {
    /**
     * URL de la base de datos.
     */
    var databaseUrl: String = "jdbc:sqlite:personaje.db"
        private set

    /**
     * Indica si se deben inicializar las tablas de la base de datos.
     */
    var databaseInitTables: Boolean = false
        private set

    /**
     * Indica si se deben inicializar los datos de la base de datos.
     */
    var databaseInitData: Boolean = false
        private set

    /**
     * Ruta donde se almacenarán los datos.
     */
    var storageData: String = "data"
        private set

    /**
     * Tamaño del caché.
     */
    var cacheSize: Int = 7
        private set

    /**
     * Inicializa la configuración cargando valores desde un archivo de propiedades.
     */
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

            // crear el directorio si no existe
            Files.createDirectories(Path(storageData))

        } catch (e: Exception) {
            logger.error { "Error cargando configuración: ${e.message}" }
            logger.error { "Usando valores por defecto" }
        }
    }
}