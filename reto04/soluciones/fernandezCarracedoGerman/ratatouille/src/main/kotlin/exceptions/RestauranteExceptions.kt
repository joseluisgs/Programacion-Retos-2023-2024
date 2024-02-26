package org.example.exceptions

sealed class RestauranteExceptions(message:String): Exception(message) {
    class ClienteColocacionException(message: String):RestauranteExceptions(message)
    class ClienteNoExistenteException(message: String):RestauranteExceptions(message)
    class ComandaException(message: String):RestauranteExceptions(message)
    class MenuNoExistenteException(message: String):RestauranteExceptions(message)
    class CamareroNoExistenteException(message: String):RestauranteExceptions(message)
}