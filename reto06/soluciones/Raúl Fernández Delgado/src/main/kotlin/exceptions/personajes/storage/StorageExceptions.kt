package exceptions.personajes.storage

sealed class StorageExceptions(message:String):Exception(message){
    class FicheroNoEncontrado():StorageExceptions("El Fichero No Existe")
}