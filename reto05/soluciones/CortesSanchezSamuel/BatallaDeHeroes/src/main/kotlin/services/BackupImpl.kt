package services

import models.Personaje
import java.io.File
import java.nio.file.Files
import java.nio.file.StandardCopyOption
import kotlin.io.path.*

class BackupImpl : Backup {
    override fun backup() {
        val tempDir = createTempDirectory("personajes_backup")
        val dataFile = Path(tempDir.toString(), "personajes.csv")

        Files.copy(dataFile, tempDir, StandardCopyOption.REPLACE_EXISTING)

    }

    override fun restore() {
        TODO("Not yet implemented")
    }
}