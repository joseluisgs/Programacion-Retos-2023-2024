package repositories.personajes

import models.Personaje
import repositories.crud.CrudRepository

interface PersonajeRepository : CrudRepository<Personaje, String> {

    fun findByNombre(nombre : String): Personaje?
    fun findByHabilidad(habilidad : String): Personaje?

}