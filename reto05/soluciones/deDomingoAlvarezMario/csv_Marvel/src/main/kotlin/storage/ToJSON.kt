import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.example.dto.PersonajesDto
import java.nio.file.Files
import java.nio.file.Paths

class ToJSON {

    fun convertirJson(personajes: List<PersonajesDto>, rutaArchivoJson: String, rutaArchivoZip: String) {

        val mapper = ObjectMapper()
        mapper.registerModule(KotlinModule())

        val json = mapper.writeValueAsString(personajes)
        val rutaArchivo = Paths.get(rutaArchivoJson)

        try {
            Files.write(rutaArchivo, json.toByteArray())
            println("Archivo JSON generado con éxito en: $rutaArchivoJson")

            // Comprimir el archivo JSON en el archivo ZIP
            val toZip = ToZip()
            toZip.toZip(listOf(rutaArchivo), rutaArchivoZip)
        } catch (e: Exception) {
            println("Error al procesar archivos: ${e.message}")
        }

    }

}