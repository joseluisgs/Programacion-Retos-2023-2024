import kotlinx.serialization.Serializable

@Serializable
data class PersonajeDto(
    val id: Long,
    val tipo: String,
    val nombre: String,
    val habilidad: String,
    val ataque: Int,
    val edad: Int,
    val arma: String
)