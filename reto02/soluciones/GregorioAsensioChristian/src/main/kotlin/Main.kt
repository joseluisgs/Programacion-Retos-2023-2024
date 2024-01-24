fun main() {

    val circuito = Circuito(8, 10)
    println("¡Bienvenido al Gran Premio de Formula 1 DAW 🏆!")
    println("Parrilla de salida:")

    for (piloto in pilotos) {
    circuito.agregarPiloto(piloto)
    }

circuito.imprimirCircuito()
}
