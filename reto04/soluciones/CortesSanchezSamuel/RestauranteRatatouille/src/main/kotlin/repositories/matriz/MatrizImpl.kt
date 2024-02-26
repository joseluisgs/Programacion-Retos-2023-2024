package repositories.matriz

class MatrizImpl<T>(

    override var rows: Int,
    override var cols: Int
) : Matriz<T> {
    val matriz: MutableList<MutableList<T?>> = mutableListOf()


    init {
        for (i in 0..<rows) {
            val fila = MutableList<T?>(cols) { null }
            for (j in 0..<cols) {
                fila.add(null)
            }
            matriz.add(fila)
        }
    }

    override operator fun get(row: Int, col: Int): T? {
        return matriz[row][col]
    }


    override fun set(row: Int, col: Int, value: T?) {
        matriz[row][col] = value
    }

    override fun clear() {
        for (i in 0..<rows) {
            for (j in 0..<cols) {
                this.set(i, j, null)
            }
        }
    }

    override fun toString(): String {
        val sb = StringBuilder()
        for (i in 0..<rows) {
            for (j in 0..<cols) {
                if (this.get(i, j) != null) {
                    sb.append("[ ").append(this.get(i, j)).append(" ]")
                } else {
                    sb.append("[    ]")
                }
            }
            sb.append("\n")
        }
        return sb.toString()
    }

}