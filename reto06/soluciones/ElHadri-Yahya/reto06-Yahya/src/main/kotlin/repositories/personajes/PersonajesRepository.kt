package repositories.personajes

import models.Personaje
import repositories.crud.CrudRepository

interface PersonajesRepository:CrudRepository<Personaje,String> {
}