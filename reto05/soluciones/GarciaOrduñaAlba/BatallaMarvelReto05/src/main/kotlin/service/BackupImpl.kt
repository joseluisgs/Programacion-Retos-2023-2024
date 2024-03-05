package org.example.service
import org.example.config.Config
import java.nio.file.Files
import java.nio.file.StandardCopyOption

import java.nio.file.Paths
import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream
import java.util.zip.ZipFile

class BackupImpl : Backup {
    override fun backup() {
        try {
            val tempDir = Files.createTempDirectory("personaje_backup")
            println("Directorio temporal creado: $tempDir")

            val sourceFilePath = Paths.get(Config.storageDir, Config.storageFile)
            val targetFilePath = tempDir.resolve(Config.storageFile)

            Files.copy(sourceFilePath, targetFilePath, StandardCopyOption.REPLACE_EXISTING)
            println("Archivo copiado a directorio temporal: $targetFilePath")

            val zipFilePath = Paths.get(Config.storageBackupDir, Config.storageBackupFile)
            ZipOutputStream(Files.newOutputStream(zipFilePath)).use { zip ->
                zip.putNextEntry(ZipEntry(Config.storageFile))
                Files.copy(targetFilePath, zip)
                zip.closeEntry()
            }
            println("Archivo comprimido creado: $zipFilePath")

            Files.deleteIfExists(targetFilePath)
            println("Archivo temporal eliminado")

        } catch (e: Exception) {
            println("Error durante el respaldo: ${e.message}")
        }
    }

    override fun restore() {
        try {
            val tempDir = Files.createTempDirectory("barcos_restore")
            println("Directorio temporal creado: $tempDir")

            val zipFilePath = Paths.get(Config.storageBackupDir, Config.storageBackupFile)
            val targetFilePath = tempDir.resolve(Config.storageBackupFile)

            Files.copy(zipFilePath, targetFilePath, StandardCopyOption.REPLACE_EXISTING)
            println("Archivo de respaldo copiado a directorio temporal: $targetFilePath")

            ZipFile(targetFilePath.toFile()).use { zip ->
                zip.entries().asSequence().forEach { entry ->
                    zip.getInputStream(entry).use { input ->
                        Files.copy(input, tempDir.resolve(entry.name), StandardCopyOption.REPLACE_EXISTING)
                        println("Archivo restaurado: ${entry.name}")
                    }
                }
            }

            val sourceFilePath = Paths.get(Config.storageDir, Config.storageFile)
            Files.copy(tempDir.resolve(Config.storageFile), sourceFilePath, StandardCopyOption.REPLACE_EXISTING)
            println("Archivo restaurado a la ubicación original: $sourceFilePath")

            Files.deleteIfExists(targetFilePath)
            println("Archivo temporal eliminado")

        } catch (e: Exception) {
            println("Error durante la restauración: ${e.message}")
        }
    }
}