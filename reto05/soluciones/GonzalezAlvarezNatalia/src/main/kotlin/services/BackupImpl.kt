package org.example.services

import org.example.config.Config
import org.lighthousegames.logging.logging
import java.nio.file.Files
import java.nio.file.StandardCopyOption
import java.util.zip.ZipEntry
import java.util.zip.ZipFile
import java.util.zip.ZipOutputStream
import kotlin.io.path.Path
import kotlin.io.path.createTempDirectory

private val logger = logging()

/**
 * Clase que implementa las funciones de la interfaz Backup
 *
 * @since 1.0
 * @author Natalia González Álvarez
 */
class BackupImpl : Backup {
    override fun backup() {
        logger.debug { "BackupImpl: Realizando backup" }
        val tempDir = createTempDirectory("heroes_backup")

        logger.debug { "BackupImpl: Directorio temporal: $tempDir" }
        val filePath = Path(Config.storageDir, Config.storageFile)
        Files.copy(filePath, tempDir.resolve(Config.storageFile), StandardCopyOption.REPLACE_EXISTING)

        // Comprimir el archivo de datos en un archivo ZIP
        val zipFile = tempDir.resolve(Config.storageBackupFile)
        ZipOutputStream(Files.newOutputStream(zipFile)).use { zip ->
            zip.putNextEntry(ZipEntry(Config.storageFile))
            Files.copy(tempDir.resolve(Config.storageFile), zip)
            zip.closeEntry()
        }
        logger.debug { "BackupImpl: Fichero comprimido: $zipFile" }

        if (!Files.exists(Path(Config.storageBackupDir))) {
            Files.createDirectories(Path(Config.storageBackupDir))
        }
        val backupDir = Path(Config.storageBackupDir, Config.storageBackupFile)
        Files.copy(zipFile, backupDir, StandardCopyOption.REPLACE_EXISTING)
        logger.debug { "BackupImpl: Fichero copiado a directorio de backup: $backupDir" }

        tempDir.toFile().deleteRecursively()
        tempDir.toFile().deleteOnExit()
        logger.debug { "BackupImpl: Directorio temporal borrado: $tempDir" }
    }

    override fun restore() {
        logger.debug { "BackupImpl: Realizando restore" }
        val backupDir = Path(Config.storageBackupDir, Config.storageBackupFile)
        val tempDir = createTempDirectory("heroes_restore")

        logger.debug { "BackupImpl: Directorio temporal: $tempDir" }

        Files.copy(backupDir, tempDir.resolve(Config.storageBackupFile), StandardCopyOption.REPLACE_EXISTING)

        val zipFile = tempDir.resolve(Config.storageBackupFile)
        if (!Files.exists(zipFile)) {
            logger.error { "BackupImpl: Fichero de backup no encontrado" }
            return
        }
        if (Files.size(zipFile) == 0L) {
            logger.error { "BackupImpl: Fichero de backup vacío" }
            return
        }

        ZipFile(zipFile.toFile()).use { zip ->
            if (zip.entries().asSequence().none()) {
                logger.error { "BackupImpl: Fichero de backup vacío" }
                return
            }
            zip.entries().asSequence().forEach { entry ->
                zip.getInputStream(entry).use { input ->
                    Files.copy(input, tempDir.resolve(entry.name), StandardCopyOption.REPLACE_EXISTING)
                    logger.debug { "BackupImpl: Fichero descomprimido: ${entry.name}" }
                }
            }
        }

        val filePath = Path(Config.storageDir, Config.storageFile)
        Files.copy(tempDir.resolve(Config.storageFile), filePath, StandardCopyOption.REPLACE_EXISTING)
        logger.debug { "BackupImpl: Fichero restaurado: $filePath" }

        tempDir.toFile().deleteRecursively()
        tempDir.toFile().deleteOnExit()
        logger.debug { "BackupImpl: Directorio temporal borrado: $tempDir" }
    }
}
