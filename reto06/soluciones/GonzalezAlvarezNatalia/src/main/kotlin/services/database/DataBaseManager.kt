package org.example.services.database

import org.apache.ibatis.jdbc.ScriptRunner
import org.example.config.Config
import org.lighthousegames.logging.logging
import java.io.PrintWriter
import java.io.Reader
import java.sql.Connection
import java.sql.DriverManager

private val logger = logging()

/**
 * Objeto singleton que gestiona la conexión a la base de datos y ejecuta scripts SQL.
 *
 * @since 1.0
 * @author Natalia Gonzalez
 */
object DataBaseInitializer {
    /**
     * Inicializa la conexión a la base de datos y ejecuta scripts de inicialización, si está configurado.
     */
    fun initialize() {
        initConexion()
        if (Config.databaseInitTables) {
            initTablas()
        }
        if (Config.databaseInitData) {
            initData()
        }
    }

    /**
     * Inicializa los datos de la base de datos ejecutando el script "data.sql".
     * @since 1.0
     * @author Natalia Gonzalez
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
     * Inicializa las tablas de la base de datos ejecutando el script "tables.sql".
     * @since 1.0
     * @author Natalia Gonzalez
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
     * Inicializa la conexión a la base de datos.
     * @since 1.0
     * @author Natalia Gonzalez
     */
    private fun initConexion() {
        logger.debug { "Iniciando conexión con la base de datos" }
        try {
            DriverManager.getConnection(Config.databaseUrl)
        } catch (e: Exception) {
            logger.error { "Error al conectar con la base de datos: ${e.message}" }
        }
        logger.debug { "Conexión con la base de datos inicializada" }
    }

    /**
     * Ejecuta un script SQL utilizando un [ScriptRunner].
     *
     * @param reader Lector que proporciona el script SQL.
     * @param logWriter Indica si se debe escribir el log de la ejecución del script.
     * @since 1.0
     * @author Natalia Gonzalez
     */
    private fun scriptRunner(reader: Reader, logWriter: Boolean = false) {
        logger.debug { "Ejecutando script SQL con log: $logWriter" }
        val connection = DriverManager.getConnection(Config.databaseUrl)
        val sr = ScriptRunner(connection)
        sr.setLogWriter(if (logWriter) PrintWriter(System.out) else null)
        sr.runScript(reader)
    }
}

object DataBaseManager : AutoCloseable {
    var connection: Connection? = null
        private set

    init {
        DataBaseInitializer.initialize()
    }

    /**
     * Cierra la conexión a la base de datos.
     * @since 1.0
     * @author Natalia Gonzalez
     */
    override fun close() {
        logger.debug { "Cerrando conexión con la base de datos" }
        connection?.close()
        logger.debug { "Conexión con la base de datos cerrada" }
    }

    /**
     * Ejecuta una operación en la base de datos dentro de un bloque [use], asegurándose de cerrar la conexión luego de su ejecución.
     *
     * @param block Bloque de código que realiza operaciones en la base de datos.
     * @since 1.0
     * @author Natalia Gonzalez
     */
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
