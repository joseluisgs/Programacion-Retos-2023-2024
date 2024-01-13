import models.Mazmorra

fun main() {
    println("🎩 COMIENZA LA EXPLORACIÓN 🪄")
    println("Usa (WASD) para moverte por la mazmorra 🏰")
    println("Objetivo: Destruir todos los Horrocruxes (💎)")
    println("Ten cuidado con Voldemort, Bellatrix y los Dementores que intentarán pararte ⚠️")
    println("Acuerdate de buscar a tus amigos Hermione, Ron, y a la profesora McGonagall para que te ayuden 🆘\n")

    val exploracion = Mazmorra()
    exploracion.exploracion()
}