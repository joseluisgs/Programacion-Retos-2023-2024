import models.Mundo
val mundo = Mundo()
fun main() {
    println()
    println("""                   ___________________________ 
        |                   ¡¡¡BIENVENIDO A HOGWARTS!!! 
        |                   ___________________________ 
        |           
        |Tu mision sera entrar a las Mazmorras y destruir Los horrocruxes que aparecen en el mapa del merodeador,
        |¡Ten Cuidado! Voldemort y sus aliados estas escondidos, ten tu barita bien a mano.
        |Tus amigos estan repartidos por la mazmorra y dispuestos a ayudar,
        |¡Suerte! para poder acabar por fin con el Señor Tenebroso""".trimMargin())
    mundo.inicializarMundo()
}