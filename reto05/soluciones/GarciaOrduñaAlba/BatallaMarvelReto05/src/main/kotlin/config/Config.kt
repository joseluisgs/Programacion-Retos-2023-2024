package org.example.config
import java.util.*

object Config {
    var storageDir: String = "data"
        private set
    var storageFile: String = "personaje.json"
        private set
    var storageBackupDir: String = "backup"
        private set
    var storageBackupFile: String = "personaje.zip"
        private set

    init {
        val properties = Properties()
        properties.load(ClassLoader.getSystemResourceAsStream("config.properties"))

        storageDir = properties.getProperty("storage.dir", storageDir)
        storageFile = properties.getProperty("storage.file", storageFile)
        storageBackupDir = properties.getProperty("storage.backup.dir", storageBackupDir)
        storageBackupFile = properties.getProperty("storage.backup.file", storageBackupFile)
    }
}