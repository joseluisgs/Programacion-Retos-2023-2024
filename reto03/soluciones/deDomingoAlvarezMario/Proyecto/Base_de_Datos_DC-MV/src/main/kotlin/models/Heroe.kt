/**
 * @author Mario de Domingo
 * @version "1.0-SNAPSHOT"
 * @property nombre nombre real del personaje
 * @property apodo es el alias que usa cada héroe
 * @property edad en años
 * @property altura en metros
 */
class Heroe(
    var nombre: String = "",
    var apodo: String = "",
    var edad: Int = 0,
    var altura: Double = 0.0
) {

    /**
     * Llamamos a las funciones de cada valor del héroe
     */
    fun asignar() {
        asignarNombre()
        asignarApodo()
        asignarAltura()
        asignarEdad()
    }

    /**
     * Asignamos el nombre
     * @return nombre
     */
    private fun asignarNombre() {
        print(" --> Nombre: ")
        nombre = readln()
    }

    /**
     * Asignamos el apodo
     * @return apodo
     */
    private fun asignarApodo() {
        print(" --> Apodo: ")
        apodo = readln()
    }

    /**
     * Asignamos la altura como un Double
     * @return altura
     */
    private fun asignarAltura(): Double {
        do {
            print(" --> Altura: ")
            val valor = readln()
            val altura = valor.toDoubleOrNull()
            if (altura != null) {
                return altura
            } else {
                println("Ingrese un valor numérico.")
            }
        } while (true)
    }

    /**
     * Asignamos la Edad correspondiente en Años
     * @return edad
     */
    private fun asignarEdad(): Int {
        do {
            print(" --> Edad: ")
            val valor = readln()
            val edad = valor.toIntOrNull()
            if (edad != null) {
                return edad
            } else {
                println("Ingrese un valor numérico.")
            }
        } while (true)
    }
}
