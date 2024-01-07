import models.*
/**
 * Función principal que inicia y controla el juego.
 */
fun main(){
    println("------🪄-------BIENVENIDO A LA MISTERIOSA MAZMORRA ENCANTADA DE AZCABAN------🪄-------")
    println("El juego acabará cuando Harry destruya los 7 horrocruxes")
    println("Ten en cuenta que el mapa se irá mostrando cuanto mas avances (W/A/S/D)")
    println("Ayuda a Harry a poder regresar a Hogwarts sano y salvo. Confiamos en ti, joven mago")

    val prision=Azkaban()
    prision.printMap()
    prision.moverHarry()
}