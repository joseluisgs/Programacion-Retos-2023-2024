package repositories.personaje

import models.Personaje

class PersonajesRepositoryImpl : PersonajesRepository {
    override fun getByHabilidad(habilidad: String): List<Personaje> {
        TODO("Not yet implemented")
    }

    override fun getByName(nombre: String): List<Personaje> {
        TODO("Not yet implemented")
    }

    override fun getByEdad(edad: Int): List<Personaje> {
        TODO("Not yet implemented")
    }

    override fun getByPc(pc: Int): List<Personaje> {
        TODO("Not yet implemented")
    }

    override fun findAll(): List<Personaje> {
        TODO("Not yet implemented")
    }

    override fun findById(id: String): Personaje? {
        TODO("Not yet implemented")
    }

    override fun save(value: Personaje): Personaje {
        TODO("Not yet implemented")
    }

    override fun update(id: String, value: Personaje): Personaje? {
        TODO("Not yet implemented")
    }

    override fun deleteById(id: String): Personaje? {
        TODO("Not yet implemented")
    }
}