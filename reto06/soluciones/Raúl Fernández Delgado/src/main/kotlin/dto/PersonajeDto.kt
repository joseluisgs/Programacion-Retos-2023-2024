package dto


data class PersonajeDto (
    val nombre:String,
    val tipo:String,
    val habilidad:String,
    val ataque:Int,
    val edad:Int,
    val arma:String,
    val isDeleted:Boolean?
)