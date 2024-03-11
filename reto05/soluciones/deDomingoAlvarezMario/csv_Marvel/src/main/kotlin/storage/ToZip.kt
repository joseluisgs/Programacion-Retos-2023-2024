import java.io.BufferedInputStream
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream

class ToZip {
    fun toZip(archivos: List<Path>, rutaArchivoZip: String) {
        try {
            val zip = Paths.get(rutaArchivoZip)
            Files.createDirectories(zip.parent)

            Files.newOutputStream(zip).use { zipStream ->
                ZipOutputStream(zipStream).use { zipOutputStream ->
                    archivos.forEach { archivo ->
                        val entry = ZipEntry(archivo.fileName.toString())
                        zipOutputStream.putNextEntry(entry)

                        Files.newInputStream(archivo).use { fileStream ->
                            BufferedInputStream(fileStream).use { bufferedStream ->
                                bufferedStream.copyTo(zipOutputStream)
                            }
                        }

                        zipOutputStream.closeEntry()
                    }
                }
            }
            println("Archivos comprimidos con éxito en: $rutaArchivoZip")
        } catch (e: Exception) {
            println("Error al comprimir archivos: ${e.message}")
        }
    }
}
