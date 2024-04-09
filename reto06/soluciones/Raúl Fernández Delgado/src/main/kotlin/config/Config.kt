package config

import java.nio.file.Files
import java.util.*
import kotlin.io.path.Path

object Config {
    var databaseUrl="jdbc:sqlite:personajes.db"
        private set
    var cacheSize=5
        private set
    var storageData="data"
        private set
    var databaseInitTables: Boolean = false
        private set
    var databaseInitData: Boolean = false
        private set
    init {
        val properties= Properties()
        properties.load(ClassLoader.getSystemResourceAsStream("config.properties"))
        databaseUrl =properties.getProperty("database.url", databaseUrl)
        cacheSize =properties.getProperty("cache.size", cacheSize.toString()).toInt()
        storageData =properties.getProperty("storage.data", storageData)
        databaseInitTables =properties.getProperty("database.init.tables", this.databaseInitTables.toString()).toBoolean()
        databaseInitData =properties.getProperty("database.init.data", this.databaseInitData.toString()).toBoolean()
        Files.createDirectories(Path(storageData))
    }

}