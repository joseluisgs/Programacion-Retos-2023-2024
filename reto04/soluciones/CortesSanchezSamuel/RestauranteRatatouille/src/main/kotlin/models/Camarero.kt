package models

const val ESPERA=1000L

class Camarero(
    val id: Int,
    var estado: EstadoCamarero
) {
    fun atenderCliente(cliente: Cliente, mesa: Int) {
        println("Camarero $id está atendiendo al cliente ${cliente.id} en la mesa $mesa.")
        estado = EstadoCamarero.ATENDIENDO
        Thread.sleep(ESPERA)
        println("Camarero $id ha terminado de atender al cliente ${cliente.id}.")
        estado = EstadoCamarero.ESPERANDO
    }

    enum class EstadoCamarero {
        ESPERANDO, ATENDIENDO, EN_CAMINO, RECOGIENDO

    }
}
