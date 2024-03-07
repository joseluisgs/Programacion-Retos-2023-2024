package org.example.services.Backup

import java.nio.file.Files
import java.nio.file.StandardCopyOption
import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream
import kotlin.io.path.Path
import kotlin.io.path.createTempDirectory

class BackUpImpl: BackUp {
    override fun backUp() {
        val tempDir = createTempDirectory("Informe_BackUp")
        val filePath = Path("informe", "informe.json")
        Files.copy(filePath, tempDir.resolve("informe.json"))
        val zipFile = tempDir.resolve("informe.zip")
        ZipOutputStream(Files.newOutputStream(zipFile)).use { zip ->
            zip.putNextEntry(ZipEntry("informe.json"))
            Files.copy(tempDir.resolve("informe.json"), zip)
            zip.closeEntry()
        }
        if(!Files.exists(Path("backup"))){
            Files.createDirectories(Path("backup"))
        }
        val backupDir = Path("backup", "informe.zip")
        Files.copy(zipFile, backupDir, StandardCopyOption.REPLACE_EXISTING)
        tempDir.toFile().deleteRecursively()
        tempDir.toFile().deleteOnExit()
    }

}