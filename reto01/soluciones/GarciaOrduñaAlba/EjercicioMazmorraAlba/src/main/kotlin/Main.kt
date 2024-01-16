
/**
 * Punto de entrada principal para el juego "Aventura mágica en Howgarts".
 */
fun main() {
    println("Bienvenido al juego: Aventura mágica en Howgarts ")
    println("Generando mazmorra...")

    // Crear una instancia de la clase Mazmorra
    val mazmorra = Mazmorra()

    // Iniciar la simulación del juego
    mazmorra.simular()
}
