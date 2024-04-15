import org.example.config.Config
import org.example.dto.PersonajeDto
import org.example.exceptions.storage.StorageExceptions
import org.example.mappers.toPersonaje
import org.example.mappers.toPersonajeDto
import org.example.models.Personaje
import org.example.service.storage.Storage
import org.lighthousegames.logging.logging
import java.time.LocalDate
import kotlin.io.path.Path


private val logger = logging()
/**
 * Implementación de [Storage] que almacena y carga personajes desde un archivo CSV.
 */
class StoragePersonajeCsv: Storage<Personaje> {
    /**
     * Almacena una lista de personajes en un archivo CSV.
     * @param data La lista de personajes a almacenar.
     * @return true si la operación de almacenamiento fue exitosa, false de lo contrario.
     * @throws StorageExceptions.StoreExceptions Si hay un error al guardar el archivo CSV.
     */
    override fun store(data: List<Personaje>): Boolean {
        logger.debug { "Guardando personajes en fichero csv" }
        try {
            val file = Path(Config.storageData, "personajes-back.csv").toFile()
            file.writeText("Id,Tipo,Nombre,Habilidad,Ataque,Edad,Arma,CreatedAt,UpdatedAt,IsDeleted\n")
            data.map { it.toPersonajeDto() }
                .forEach {
                    file.appendText("${it.id},${it.tipo},${it.nombre},${it.habilidad},${it.ataque},${it.edad},${it.arma},${it.createdAt},${it.updatedAt},${it.isDeleted}\n")
                }
            logger.debug { "Guardado correctamente" }
            return true
        } catch (e: Exception) {
            logger.error { "Error al guardar el fichero csv de personajes: ${e.message}" }
            throw StorageExceptions.StoreExceptions("Error al guardar el fichero csv de personas: ${e.message}")
        }
    }
    /**
     * Carga personajes desde un archivo CSV.
     * @param fileName El nombre del archivo desde donde cargar los personajes.
     * @return Una lista de personajes cargados desde el archivo CSV.
     * @throws StorageExceptions.LoadExceptions Si hay un error al cargar el archivo CSV.
     */

    override fun load(fileName: String): List<Personaje> {
        try {
            logger.debug { "Cargando personajes desde fichero csv" }
            val file = Path(Config.storageData, fileName).toFile()
            if (!file.exists()) throw StorageExceptions.LoadExceptions("El fichero $fileName no existe")
            return file.readLines().drop(1)
                .map {
                    val data = it.split(",")
                    PersonajeDto(
                        id = data[0].toInt(),
                        tipo = data[1],
                        nombre = data[2],
                        habilidad = data[3],
                        ataque = data[4].toInt(),
                        edad = data[5].toInt(),
                        arma = data[6],
                        createdAt = LocalDate.now().toString(),
                        updatedAt = LocalDate.now().toString(),
                        isDeleted = false
                    ).toPersonaje()
                }
        } catch (e: Exception) {
            logger.error { "Error al cargar el fichero csv de personajes: ${e.message}" }
            e.printStackTrace()
            throw StorageExceptions.LoadExceptions("Error al cargar el fichero csv de personajes: ${e.message}")
        }
    }
}