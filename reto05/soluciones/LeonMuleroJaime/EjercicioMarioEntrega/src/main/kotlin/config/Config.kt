package config

object Config {
    var storageDir: String = "data"
    var storageFile: String = "personajes.csv"
    var storageBackupDir: String = "backup"
    var storageBackupFile: String = "backup-personajes.zip"
    var storageFileForBackup: String = "personajes.json"
    var temporaryStorageBackupFile: String = "personajes_backup"
    var temporaryStorageRestoreFile: String = "personajes_restore"
}