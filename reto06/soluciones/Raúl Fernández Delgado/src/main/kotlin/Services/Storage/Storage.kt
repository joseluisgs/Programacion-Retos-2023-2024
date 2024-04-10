package Services.Storage

interface Storage<T> {
    fun store(data: List<T>): Boolean
    fun load(file: String): List<T>
}