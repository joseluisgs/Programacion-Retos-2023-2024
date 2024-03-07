package org.example.config
import java.util.*

object Config {
    var storageDir: String = "data"
        private set
    var storageFile: String = "personajes.json"
        private set
    var storageBackupDir: String = "backup"
        private set
    var storageBackupFile: String = "personajes.zip"
        private set

    init {
        val properties = Properties()
        properties.load(ClassLoader.getSystemResourceAsStream("config.properties"))

        storageDir = properties.getOrDefault("storage.dir", storageDir).toString()
        storageFile = properties.getProperty("storage.file", storageFile)
        storageBackupDir = properties.getProperty("storage.backup.dir", storageBackupDir)
        storageBackupFile = properties.getProperty("storage.backup.file", storageBackupFile)
    }
}