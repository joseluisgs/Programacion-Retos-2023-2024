package org.example.exceptions

sealed class HeroException(message: String) : Exception(message) {
    class NameNotValid : HeroException("El nombre no es válido")
    class AliasNotValid : HeroException("El alias no es válido")
    class NotaNotValid: HeroException("La nota no es válida")
    class AlturaNotValid: HeroException("La altura no es válida")
    class EdadNotValid: HeroException("La edad no es válida")
    class NotFound(id: Int): HeroException("No se ha encontrado al héroe con el id: $id")

}