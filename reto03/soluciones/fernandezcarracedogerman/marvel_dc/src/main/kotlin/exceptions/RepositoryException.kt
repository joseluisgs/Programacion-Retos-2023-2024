package org.example.exceptions

sealed class RepositoryException (message:String): Exception(message){
    class EmptyRepository: RepositoryException("La base de datos está vacía")
    class FullRepository: RepositoryException("La base de datos está llena")
    class ResizeRepository: RepositoryException("La base de datos no puede tener un tamaño menor que 0")
}