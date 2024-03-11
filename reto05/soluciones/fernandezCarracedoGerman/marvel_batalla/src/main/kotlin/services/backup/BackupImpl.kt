package org.example.services.backup

import org.example.config.Config
import org.lighthousegames.logging.logging
import java.nio.file.Files
import java.nio.file.StandardCopyOption
import java.util.zip.ZipEntry
import java.util.zip.ZipFile
import java.util.zip.ZipOutputStream
import kotlin.io.path.Path

private val log = logging()

/**
 * Clase que implementa las operaciones de backup (backup y restore)
 * @see Backup
 *
 */
class BackupImpl:Backup {

    /**
     * Realiza el backup del fichero JSON en la carpeta de backup
     */
    override fun backup() {
        log.debug { "BackupImpl: Realizando backup" }

        val zipFile = Path(Config.storageHeroesDir).resolve(Config.storageBackupFile)
        ZipOutputStream(Files.newOutputStream(zipFile)).use { zip ->
            zip.putNextEntry(ZipEntry(Config.storageHeroesJsonFile))
            Files.copy(Path(Config.storageHeroesDir).resolve(Config.storageHeroesJsonFile), zip)
            zip.closeEntry()
        }
        log.debug { "BackupImpl: Fichero comprimido: $zipFile" }



        // Copiamos el zip a un directorio de backup
        if (!Files.exists(Path(Config.storageBackupDir))) {
            Files.createDirectories(Path(Config.storageBackupDir))
        }
        val backupDir = Path(Config.storageBackupDir, Config.storageBackupFile)
        Files.copy(zipFile, backupDir, StandardCopyOption.REPLACE_EXISTING)
        log.debug { "BackupImpl: Fichero copiado a directorio de backup: $backupDir" }


    }

    /**
     * Restaura en formato JSON el fichero guardado en nuestro backup
     * Primero comprueba que el fichero exista y no esté vacío
     */
    override fun restore() {
        log.debug { "BackupImpl: Realizando restore" }
        val backupDir = Path(Config.storageBackupDir)
        val zipFile = backupDir.resolve(Config.storageBackupFile)
        if (!Files.exists(zipFile)) {
            log.error { "BackupImpl: Fichero de backup no encontrado" }
            return
        }
        if (Files.size(zipFile) == 0L) {
            log.error { "BackupImpl: Fichero de backup vacío" }
            return
        }

        ZipFile(zipFile.toFile()).use { zip ->
            if (zip.entries().asSequence().none()) {
                log.error { "BackupImpl: Fichero de backup vacío" }
                return
            }
            zip.entries().asSequence().forEach { archivos ->
                zip.getInputStream(archivos).use { input ->
                    Files.copy(input, backupDir.resolve(archivos.name), StandardCopyOption.REPLACE_EXISTING)
                    log.debug { "BackupImpl: Fichero descomprimido: $archivos" }
                }
            }
        }

        // Movemos el fichero a su ubicación original
        val filePath = Path(Config.storageHeroesDir, Config.storageHeroesJsonFile)
        Files.move(backupDir.resolve(Config.storageHeroesJsonFile), filePath, StandardCopyOption.REPLACE_EXISTING)
        log.debug { "BackupImpl: Fichero restaurado: $filePath" }
    }

}