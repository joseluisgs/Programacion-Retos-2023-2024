
interface PersonajesService {
    fun loadFromCsv(): List<PersonajeDto>
    fun storeToCsv(personas: List<PersonajeDto>)
    fun loadFromJson(): List<PersonajeDto>
    fun storeToJson(personas: List<PersonajeDto>)
    fun findAll(): List<PersonajeDto>
    fun findByTipo(tipo: String): List<PersonajeDto>
    fun save(persona: PersonajeDto): PersonajeDto
}