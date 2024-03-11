package repositories.personaje

import models.Personaje
import repositories.crud.CrudRepository

interface PersonajesRepository: CrudRepository<Personaje, String> {
    fun getByHabilidad(habilidad: String):List<Personaje>
    fun getByName(nombre: String): List<Personaje>
    fun getByEdad(edad:Int): List<Personaje>
    fun getByPc(pc: Int): List<Personaje>
}