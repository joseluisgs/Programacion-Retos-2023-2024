package org.example.services.heroe

import org.example.config.Config
import org.example.exceptions.storage.StorageExceptions
import org.example.models.Heroe
import org.example.service
import org.example.services.backup.Backup
import org.example.services.storage.base.FileStorage
import org.example.services.storage.bitacora.BitacoraFileStorageTxt
import org.lighthousegames.logging.logging
import java.io.File
import kotlin.io.path.Path

private val log = logging()

/**
 *  Maneja la lógica de las importaciones y exportaciones de datos a nuestra lista, la escritura en el fichero de
 *  bitácora, y de las copias de seguridad.
 *  @property storageCsv Implementa el almacenamiento en formato CSV
 *  @property bitacora Implementa el almacenamiento de la bitácora de batallas en fichero de texto plano
 *  @property storageJson Implementa el almacenamiento en formato JSON
 *  @property backup Implementa la gestión de la copia de seguridad
 */
class HeroeServiceImpl(
    private val storageCsv: FileStorage<Heroe>,
    private val bitacora:BitacoraFileStorageTxt,
    private val storageJson: FileStorage<Heroe>,
    private val backup: Backup
): HeroesService {

    private var listaHeroes:List<Heroe> = listOf()
    val filePersonajes = File(Path(System.getProperty("user.dir"), Config.storageHeroesDir, Config.storageHeroesFile).toString())
    val fileBitacora = File(Path(System.getProperty("user.dir"), Config.storageHeroesDir, Config.storageBitacoraFile).toString())
    val fileJson =  File(Path(System.getProperty("user.dir"), Config.storageHeroesDir, Config.storageHeroesJsonFile).toString())

    init{

        if (!filePersonajes.exists()) {  throw StorageExceptions.FileNotFound("No se encontró el fichero de Personajes.") }
        if (!filePersonajes.canRead()) { throw StorageExceptions.FileNotFound("El fichero de Personajes no se puede leer.") }

        if (!fileBitacora.exists()) {
            fileBitacora.writeText("")
            log.debug { "El fichero de bitácora no existía, se crea" }
        }
        if (!fileBitacora.canRead()) { throw StorageExceptions.FileNotFound("El fichero de bitácora no se puede leer.") }

        // importamos el CSV de personajes a la lista
        importFromFile(filePersonajes)
    }

    /**
     * Recuperamos la lista completa de héroes
     * @return List Lista de héroes con todos sus elementos
     */
    override fun getAll(): List<Heroe> {
        return listaHeroes
    }

    /**
     * Exportamos los elementos de la lista de héroes a un fichero CSV
     * @param file Fichero donde se guardará la lista
     */
    override fun exportToFile(file: File) {
        log.debug { "Exportando lista Personajes a CSV: $file" }
        storageCsv.writeToFile(listaHeroes, file)
    }

    /**
     * Importamos los elementos de un fichero CSV a nuestra lista de héroes
     * @param file Fichero donde se encuentran los datos
     */
    override fun importFromFile(file: File) {
        log.debug { "Importando Personajes desde CSV: $file" }
        listaHeroes = storageCsv.readFromFile(file)
    }


    /**
     * Añade una línea de texto dado al final del fichero de bitácora
     * @param text Texto a añadir
     */
    override fun anadirTextoBitacora(text: String) {
        log.debug { "Añadiendo línea de bitácora" }
        bitacora.anadirTexto(text, fileBitacora)
    }

    /**
     * Exportamos la lista actual de personajes al fichero de formato JSON
     * @param file Fichero donde se guardará la lista
     */
    override fun exportToJson(file: File) {
        log.debug { "Exportando lista Personajes a JSON: $file" }
        storageJson.writeToFile(listaHeroes, file)
    }

    /**
     * Importamos la lista actual de personajes desde el fichero de formato JSON
     * @param file Fichero donde se encuentran los datos
     */
    override fun importFromJson(file: File) {
        log.debug { "Importando Personajes desde JSON: $file" }
        listaHeroes = storageJson.readFromFile(file)
    }

    /**
     * Se realiza la copia de seguridad de los datos, previa exportación a un fichero en formato JSON,
     * que es el que se incluirá en el backup
     */
    override fun backup() {
        log.debug { "Realizando backup" }
        exportToJson(fileJson)
        backup.backup()
    }

    /**
     * Restaura la copia de seguridad. Como el fichero guardado tiene formato JSON, una vez recuperado,
     * restauramos nuestra lista y el fichero de personajes en CSV
     */
    override fun restore() {
        log.debug { "Realizando restore" }
        backup.restore()

        log.debug { "Personajes restaurados a JSON" }
        importFromJson (fileJson)
        log.debug { "Lista de Personajes restaurada" }

        exportToFile(filePersonajes)
        log.debug { "Fichero de Personajes CSV restaurado" }
    }
}