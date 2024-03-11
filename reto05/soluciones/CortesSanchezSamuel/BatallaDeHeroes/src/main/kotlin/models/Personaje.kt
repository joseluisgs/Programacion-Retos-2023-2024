package models

data class Personaje(
    val id: Int,
    val nickname: String,
    val nombre : String,
    val edad : Int,
    val vivo : Boolean,
    val villano : Boolean,
    val habilidad : String,
    val pc : Int

) {
    override fun toString(): String {
        return "Id:$id, Nickname:$nickname, Nombre:$nombre, Edad:$edad, Vivo:$vivo, Villano:$villano, Habilidad:$habilidad, Pc:$pc"
    }

}
