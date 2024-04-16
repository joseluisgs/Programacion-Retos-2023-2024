package services.database

import config.Config
import java.io.PrintWriter
import java.sql.Connection
import java.sql.DriverManager
import java.io.Reader
import org.apache.ibatis.jdbc.ScriptRunner


object DataBaseManager : AutoCloseable {
    var connection: Connection? = null
        private set

    init {
        initConexion()
        if (Config.databaseInitTables) {
            initTablas()
        }
        if (Config.databaseInitData) {
            initData()
        }

    }

    private fun initData() {
        try {
            val data = ClassLoader.getSystemResourceAsStream("data.sql")?.bufferedReader()!!
            scriptRunner(data, true)
        } catch (e: Exception) {
            println("Error al cargar los datos: ${e.message}")
        }
    }

    private fun initTablas() {
        try {
            val tablas = ClassLoader.getSystemResourceAsStream("tables.sql")?.bufferedReader()!!
            scriptRunner(tablas, true)
        } catch (e: Exception) {
            println("Error al crear las tablas: ${e.message}")
        }
    }

    private fun initConexion() {
        println("Iniciando conexión con la base de datos")
        if (connection == null || connection!!.isClosed) {
            connection = DriverManager.getConnection(Config.databaseUrl)
        }
        println("Conexión con la base de datos inicializada")
    }


    override fun close() {
        println("Cerrando conexión con la base de datos")
        if (!connection!!.isClosed) {
            connection!!.close()
        }
        println("Conexión con la base de datos cerrada")
    }


    fun <T> use(block: (DataBaseManager) -> T) {
        try {
            initConexion()
            block(this)
        } catch (e: Exception) {
            println("Error en la base de datos: ${e.message}")
        } finally {
            close()
        }
    }

    private fun scriptRunner(reader: Reader, logWriter: Boolean = false) {
        println("Ejecutando script SQL con log: $logWriter")
        val sr = ScriptRunner(connection)
        sr.setLogWriter(if (logWriter) PrintWriter(System.out) else null)
        sr.runScript(reader)
    }
}