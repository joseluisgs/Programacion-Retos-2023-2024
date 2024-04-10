package org.example.services.database

import org.apache.ibatis.jdbc.ScriptRunner
import org.example.config.Config
import org.lighthousegames.logging.logging
import java.io.PrintWriter
import java.io.Reader
import java.sql.Connection
import java.sql.DriverManager

private val logger = logging()
object DatabaseManager: AutoCloseable {
    var connection: Connection? = null
        private set

    init {
        initConexion()
        if(Config.databaseInitTables) initTablas()
        if(Config.databaseInitData) initData()
    }
    private fun initData(){
        logger.debug { "Iniciando carga de datos" }
        try {
            val data = ClassLoader.getSystemResourceAsStream("data.sql")?.bufferedReader()!!
            scriptRunner(data, true)
            logger.debug { "Datos cargados" }
        }catch (e:Exception){
            logger.error { "Error al cargar los datos ${e.message}" }
        }
    }

    private fun initTablas(){
        logger.debug { "Creando tablas" }
        try {
            val data = ClassLoader.getSystemResourceAsStream("tables.sql")?.bufferedReader()!!
            scriptRunner(data, true)
            logger.debug { "Tabla personajes creada" }
        }catch (e:Exception){
            logger.error { "Error al crear las tabalas: ${e.message}" }
        }
    }

    private fun initConexion(){
        logger.debug { "Iniciando la conexión con la base de datos" }
        if(connection == null || connection!!.isClosed){
            connection = DriverManager.getConnection(Config.databaseUrl)
        }
        logger.debug { "Conexión con la base de datos incializada" }
    }
    private fun scriptRunner(reader: Reader, logWriter: Boolean = false) {
        logger.debug { "Ejecutando script SQL con log: $logWriter" }
        val sr = ScriptRunner(connection)
        sr.setLogWriter(if (logWriter) PrintWriter(System.out) else null)
        sr.runScript(reader)
    }
    override fun close() {
        logger.debug { "Cerrando la conexión con la base de datos" }
        if(!connection!!.isClosed){
            connection!!.close()
        }
        logger.debug { "Conexión con la base de datos cerrada" }
    }

    fun <T> use(block: (DatabaseManager) -> T){
        try {
            initConexion()
            block(this)
        }catch (e: Exception){
            logger.error { "Error en la base de datos: ${e.message}" }
        }finally {
            close()
        }
    }
}