package exceptions.personajes

sealed class PersonajesExceptions(message:String):Exception(message) {
    class TipoNoEncontrado():PersonajesExceptions("El Tipo No Se A encontrado")
    class PersonajeNoEncontrado():PersonajesExceptions("El Personaje No  se Ha encontrado")
}