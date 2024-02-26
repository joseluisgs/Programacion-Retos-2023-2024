package repositories.matriz

interface Matriz<T> {
    fun get(row: Int, col: Int): T?

    fun set(row: Int, col: Int, value: T?)

    val rows: Int

    val cols: Int

    fun clear()

    override fun toString(): String

}