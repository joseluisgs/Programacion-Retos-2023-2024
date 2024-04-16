package org.example.services.database

import org.apache.ibatis.jdbc.ScriptRunner
import org.example.config.Config
import org.lighthousegames.logging.logging
import java.io.PrintWriter
import java.io.Reader
import java.sql.Connection
import java.sql.DriverManager


private val logger = logging()

object DataBaseManager : AutoCloseable {
    var connection: Connection? = null
        private set

    init {
        DataBaseManager.initialize()
    }

    fun initialize() {
        initConexion()
        if (Config.databaseInitTables) initTablas()
        if (Config.databaseInitData) initData()
    }

    private fun initData() {
        logger.debug { "Cargando datos..." }
        try {
            val data = ClassLoader.getSystemResourceAsStream("data.sql")?.bufferedReader()!!
            scriptRunner(data, true)
            logger.debug { "Datos cargados" }
        } catch (e: Exception) {
            logger.error { "Error al cargar los datos: ${e.message}" }
        }
    }

    private fun initTablas() {
        logger.debug { "Creando tablas..." }
        try {
            val tablas = ClassLoader.getSystemResourceAsStream("tables.sql")?.bufferedReader()!!
            scriptRunner(tablas, true)
            logger.debug { "Tabla 'personajes' creada con éxito" }
        } catch (e: Exception) {
            logger.error { "Ha ocurrido un error al crear las tablas: ${e.message}" }
        }
    }

    private fun initConexion() {
        logger.debug { "Iniciando conexión con la base de datos" }
        if (connection == null || connection!!.isClosed) {
            connection = DriverManager.getConnection(Config.databaseUrl)
        }
        logger.debug { "Conexión con la base de datos iniciada" }
    }

    override fun close() {
        if (!connection!!.isClosed) connection!!.close()
        logger.debug { "Conexión con la base de datos cerrada" }
    }

    fun <T> use(block: (DataBaseManager) -> T) {
        try {
            initConexion()
            block(this)
        } catch (e: Exception) {
            logger.error { "Error en la base de datos: ${e.message}" }
        } finally {
            close()
        }
    }

    private fun scriptRunner(reader: Reader, logWriter: Boolean = false) {
        logger.debug { "Ejecutando script SQL con log: $logWriter" }
        val sr = ScriptRunner(connection)
        sr.setLogWriter(if (logWriter) PrintWriter(System.out) else null)
        sr.runScript(reader)
    }
}