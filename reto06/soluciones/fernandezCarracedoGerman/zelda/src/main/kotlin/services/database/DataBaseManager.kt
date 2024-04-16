package org.example.services.database

import org.apache.ibatis.jdbc.ScriptRunner
import org.lighthousegames.logging.logging
import java.io.PrintWriter
import java.io.Reader
import java.sql.Connection
import java.sql.DriverManager
import org.example.config.Config

/**
 * Clase para manejar las conexiones a la base de datos
 * Contiene dos clases Object: DataBaseInitializer y DataBaseManager
 * @see DataBaseManager
 * @see DataBaseInitializer
 */
private val log = logging()

/**
 * Clase para inicializar la conexión, las tablas y datos iniciales de la base de datos
 */
object DataBaseInitializer {
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
        log.debug { "Iniciando carga de datos" }
        try {
            val data = ClassLoader.getSystemResourceAsStream("data.sql")?.bufferedReader()!!
            scriptRunner(data, true)
            log.debug { "Datos cargados" }
        } catch (e: Exception) {
            log.error { "Error al cargar los datos: ${e.message}" }
        }
    }

    /**
     * Inicializamos las tablas de la base de datos en caso de que se haya configurado
     */

    private fun initTablas() {
        log.debug { "Creando tablas" }
        try {
            val tablas = ClassLoader.getSystemResourceAsStream("tables.sql")?.bufferedReader()!!
            scriptRunner(tablas, true)
            log.debug { "Tabla personajes creada" }
        } catch (e: Exception) {
            log.error { "Error al crear las tablas: ${e.message}" }
        }
    }

    /**
     * Inicializamos la conexión con la base de datos
     */

    private fun initConexion() {
        // Inicializamos la base de datos
        log.debug { "Iniciando conexión con la base de datos" }
        try {
            DriverManager.getConnection(Config.databaseUrl)
        } catch (e:Exception) {
            log.error { "Error al conectar con la base de datos: ${e.message}" }
        }

        log.debug { "Conexión con la base de datos inicializada" }
    }

    /**
     * Función para ejecutar un script SQL en la base de datos
     */
    private fun scriptRunner(reader: Reader, logWriter: Boolean = false) {
        log.debug { "Ejecutando script SQL con log: $logWriter" }
        val connection = DriverManager.getConnection(Config.databaseUrl)
        val sr = ScriptRunner(connection)
        sr.setLogWriter(if (logWriter) PrintWriter(System.out) else null)
        sr.runScript(reader)
    }

}

/**
 * Clase autocloseable, para realizar operaciones sobre la base de datos
 */
object DataBaseManager : AutoCloseable {
    var connection: Connection? = null
        private set

    /**
     * Inicializamos la base de datos
     */
    init {
        DataBaseInitializer.initialize()
    }

    /**
     * Cerramos la conexión con la base de datos
     */
    override fun close() {
        log.debug { "Cerrando conexión con la base de datos" }
        connection?.close()
        log.debug { "Conexión con la base de datos cerrada" }
    }

    /**
     * Función para usar la base de datos y cerrarla al finalizar la operación
     */

    fun <T> use(block: (Connection) -> T) {
        try {
            connection = DriverManager.getConnection(Config.databaseUrl)
            block(connection!!)
        } catch (e: Exception) {
            log.error { "Error en la base de datos: ${e.message}" }
            throw e
        } finally {
            close()
        }
    }
}