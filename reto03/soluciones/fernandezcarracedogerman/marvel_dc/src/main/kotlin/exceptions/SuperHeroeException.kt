package org.example.exceptions

sealed class SuperHeroeException (message:String): Exception(message){
    class NotFound(id: Int) : SuperHeroeException("No se ha encontrado la persona con id: $id")
    class NameNotValid : SuperHeroeException("El nombre no es válido")
    class AliasNotValid : SuperHeroeException("El alias no es válido")
    class AlturaNotValid : SuperHeroeException("La altura no es válida")
    class NotasNotValid : SuperHeroeException("Las notas no son válidas")

}
