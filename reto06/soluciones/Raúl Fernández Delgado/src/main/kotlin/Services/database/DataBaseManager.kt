package Services.database

import config.Config
import java.io.PrintWriter
import java.io.Reader
import java.sql.DriverManager

object DataBaseManager:AutoCloseable {
    var connection:Connection?=null
        private set

    init {
        initConexion()
        if (Config.databaseInitTables) initTablas()
        if (Config.databaseInitData) initData()
    }

    private fun initData() {
        val data = ClassLoader.getSystemResourceAsStream("data.sql")?.bufferedReader()!!
        scriptRunner(data, true)
    }

    private fun initTablas() {
        val tablas=ClassLoader.getSystemResourceAsStream("tables.sql")?.bufferedReader()!!
        scriptRunner(tablas,true)
    }

    private fun scriptRunner(tablas: Reader, logWriter: Boolean=false) {
        val sr = ScriptRunner(connection)
        sr.setLogWriter(if (logWriter) PrintWriter(System.out) else null)
        sr.runScript(tablas)
    }

    private fun initConexion() {
        if (connection ==null|| connection!!.isClosed){
            connection = DriverManager.getConnection(Config.databaseUrl)
        }
    }

    override fun close() {
        if (!connection!!.isClosed) connection!!.close()
    }

    fun <T> use(block:(DataBaseManager)->T){
        initConexion()
        block(this)
        close()
    }

}