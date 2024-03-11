package org.example.services.heroe

import org.example.models.Heroe
import java.io.File

/**
 *  Interfaz con las operaciones del servicio principal del programa
 */
interface HeroesService {
    fun getAll(): List<Heroe>
    fun importFromFile(file: File)
    fun exportToFile(file: File)
    fun anadirTextoBitacora(text:String)
    fun exportToJson(file: File)
    fun importFromJson(file: File)
    fun backup()
    fun restore()
}