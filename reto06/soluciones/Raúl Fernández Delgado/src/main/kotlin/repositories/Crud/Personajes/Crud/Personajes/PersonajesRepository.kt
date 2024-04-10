package repositories.Crud.Personajes.Crud.Personajes

interface PersonajesRepository : CrudRepository<Personaje, Long> {
    fun findByTipo(tipo: String): List<Personaje>
}