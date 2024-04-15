package org.example.service.database

import org.apache.ibatis.jdbc.ScriptRunner
import org.example.config.Config
import org.lighthousegames.logging.logging
import java.io.PrintWriter
import java.io.Reader
import java.sql.Connection
import java.sql.DriverManager


private val logger = logging()

/**
 * Inicializador de la base de datos
 */
object DataBaseInitializer {
    /**
     * Inicializamos la base de datos
     */


    fun initialize() {
        // Iniciamos la base de datos
        initConexion()
        if (Config.databaseInitTables) {
            initTablas()
        }
        if (Config.databaseInitData) {
            initData()
        }
    }

    /**
     * Inicializamos los datos de la base de datos en caso de que se haya configurado
     */
    private fun initData() {
        logger.debug { "Iniciando carga de datos" }
        try {
            val data = ClassLoader.getSystemResourceAsStream("data.sql")?.bufferedReader()!!
            scriptRunner(data, true)
            logger.debug { "Datos cargados" }
        } catch (e: Exception) {
            logger.error { "Error al cargar los datos: ${e.message}" }
        }
    }

    /**
     * Inicializamos las tablas de la base de datos en caso de que se haya configurado
     */
    private fun initTablas() {
        logger.debug { "Creando tablas" }
        try {
            val tablas = ClassLoader.getSystemResourceAsStream("tables.sql")?.bufferedReader()!!
            scriptRunner(tablas, true)
            logger.debug { "Tabla personajes creada" }
        } catch (e: Exception) {
            logger.error { "Error al crear las tablas: ${e.message}" }
        }
    }

    /**
     * Inicializamos la conexión con la base de datos
     */
    private fun initConexion() {
        // Inicializamos la base de datos
        logger.debug { "Iniciando conexión con la base de datos" }
        try {
            DriverManager.getConnection(Config.databaseUrl)
        } catch (e: Exception) {
            logger.error { "Error al conectar con la base de datos: ${e.message}" }
        }
        logger.debug { "Conexión con la base de datos inicializada" }
    }

    /**
     * Función para ejecutar un script SQL en la base de datos
     */
    private fun scriptRunner(reader: Reader, logWriter: Boolean = false) {
        logger.debug { "Ejecutando script SQL con log: $logWriter" }
        val connection = DriverManager.getConnection(Config.databaseUrl)
        val sr = ScriptRunner(connection)
        sr.setLogWriter(if (logWriter) PrintWriter(System.out) else null)
        sr.runScript(reader)
    }
}

/**
 * Clase para gestionar la conexión con la base de datos
 */
object DataBaseManager : AutoCloseable {
    var connection: Connection? = null
        private set

    init {
        DataBaseInitializer.initialize()
    }

    override fun close() {
        logger.debug { "Cerrando conexión con la base de datos" }
        connection?.close()
        logger.debug { "Conexión con la base de datos cerrada" }
    }

    fun <T> use(block: (Connection) -> T) {
        try {
            connection = DriverManager.getConnection(Config.databaseUrl)
            block(connection!!)
        } catch (e: Exception) {
            logger.error { "Error en la base de datos: ${e.message}" }
            throw e
        } finally {
            close()
        }
    }
}