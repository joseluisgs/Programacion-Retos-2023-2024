package services

import config.Config
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import models.Personaje
import org.lighthousegames.logging.logging
import java.nio.file.Files
import java.nio.file.StandardCopyOption
import java.util.zip.ZipEntry
import java.util.zip.ZipFile
import java.util.zip.ZipOutputStream
import kotlin.io.path.*

private val logger = logging()

class BackupImpl: Backup {

    override fun backup() {
        csvToJson() //Creamos el json a partir del csv existente

        logger.debug { "Backup: Realizando backup" }

        val tempDir = createTempDirectory(Config.temporaryStorageBackupFile)
        logger.debug { "Backup: Directorio temporal: $tempDir" }

        val file = Path(Config.storageDir, Config.storageFileForBackup)
        Files.copy(file, tempDir.resolve(Config.storageFileForBackup), StandardCopyOption.REPLACE_EXISTING)
        logger.debug { "Backup: Fichero copiado: $file" }

        val zipFile = tempDir.resolve(Config.storageBackupFile)
        ZipOutputStream(Files.newOutputStream(zipFile)).use { zip ->
            zip.putNextEntry(ZipEntry(Config.storageFileForBackup))
            Files.copy(tempDir.resolve(Config.storageFileForBackup), zip)
            zip.closeEntry()
        }
        logger.debug { "Backup: Fichero comprimido: $zipFile" }

        if (!Files.exists(Path(Config.storageBackupDir))) {
            Files.createDirectories(Path(Config.storageBackupDir))
        }
        val backupDir = Path(Config.storageBackupDir, Config.storageBackupFile)
        Files.copy(zipFile, backupDir, StandardCopyOption.REPLACE_EXISTING)
        logger.debug { "Backup: Fichero copiado a directorio de backup: $backupDir" }

        tempDir.toFile().deleteRecursively()
        tempDir.toFile().deleteOnExit()
        logger.debug { "Backup: Directorio temporal borrado: $tempDir" }
    }

    override fun restore() {
        logger.debug { "Backup: Realizando restore" }
        val backupDir = Path(Config.storageBackupDir, Config.storageBackupFile)
        val tempDir = createTempDirectory(Config.temporaryStorageRestoreFile)
        logger.debug { "Backup: Directorio temporal: $tempDir" }

        Files.copy(backupDir, tempDir.resolve(Config.storageBackupFile), StandardCopyOption.REPLACE_EXISTING)
        logger.debug { "Backup: Fichero copiado: $backupDir" }

        val zipFile = tempDir.resolve(Config.storageBackupFile)
        if (!Files.exists(zipFile)) {
            logger.error { "Backup: Fichero de backup no encontrado" }
            return
        }
        if (Files.size(zipFile) == 0L) {
            logger.error { "Backup: Fichero de backup vacío" }
            return
        }

        ZipFile(zipFile.toFile()).use { zip ->
            if (zip.entries().asSequence().none()) {
                logger.error { "Backup: Fichero de backup vacío" }
                return
            }
            zip.entries().asSequence().forEach { archivos ->
                zip.getInputStream(archivos).use { input ->
                    Files.copy(input, tempDir.resolve(archivos.name), StandardCopyOption.REPLACE_EXISTING)
                    logger.debug { "Backup: Fichero descomprimido: $archivos" }
                }
            }
        }

        val file = Path(Config.storageDir, Config.storageFileForBackup)
        Files.copy(tempDir.resolve(Config.storageFileForBackup), file, StandardCopyOption.REPLACE_EXISTING)
        logger.debug { "Backup: Fichero restaurado: $file" }

        tempDir.toFile().deleteRecursively()
        tempDir.toFile().deleteOnExit()
        logger.debug { "Backup: Directorio temporal borrado: $tempDir" }
        jsonToCsv() //Pasamos el json a csv generando "personajes-back.csv"
    }

    private fun csvToJson() {
        val csv = Path(Config.storageDir, Config.storageFile).readLines().drop(1)
        val listadoPersonajes: MutableList<Personaje> = mutableListOf()

        for (linea in csv) {
            val datos = linea.split(",")
            val personaje = Personaje(datos[0].toInt(), datos[1], datos[2], datos[3].toInt(), datos[4].toBoolean(), datos[5].toBoolean(), datos[6], datos[7].toInt())
            listadoPersonajes.add(personaje)
        }

        val jsonProperties = Json { ignoreUnknownKeys = true; prettyPrint = true }
        val json = jsonProperties.encodeToString(listadoPersonajes)
        Path(Config.storageDir, Config.storageFileForBackup).writeText(json)
    }

    private fun jsonToCsv() {
        val json = Path(Config.storageDir, Config.storageFileForBackup).readText()

        val listadoPersonajes = Json.decodeFromString<List<Personaje>>(json)

        val csv = Path(Config.storageDir, Config.storageFile)

        csv.writeText("ID,NickName,Nombre,Edad,Vivo,Villano,Habilidad,PC\n")
        listadoPersonajes.forEach {
            csv.appendText("${it.id},${it.nickName},${it.nombre},${it.edad},${it.vivo},${it.villano},${it.habilidad},${it.puntosCombate}\n")
        }
    }
}