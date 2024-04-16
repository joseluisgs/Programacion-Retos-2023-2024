package exceptions.personaje

sealed class PersonajeException(message: String) : Exception(message) {
    class PersonajeNotFetchedException() : PersonajeException("Personaje no encontrado")
    class PersonajeNotSavedException() : PersonajeException("Personaje no guardado")
    class PersonajeNotUpdatedException() : PersonajeException("Personaje no actualizado")

}