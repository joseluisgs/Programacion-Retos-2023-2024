package org.example.service
import org.example.models.Personaje
import java.io.BufferedReader
import java.io.InputStreamReader
/**

Servicio encargado de gestionar los personajes del sistema.
 */
class PersonajeService {
    // Lista mutable que almacena los personajes cargados desde un archivo CSV
    private val personajes = mutableListOf<Personaje>()
    /**

    Inicializa el servicio cargando los personajes desde un archivo CSV al momento de la creación.
     */
    init {
        cargarPersonajesDesdeCSV()
    }
    /**

    Carga los personajes desde un archivo CSV ubicado en resources/personajes.csv
     */
    private fun cargarPersonajesDesdeCSV() {
        val fileName = "personajes.csv"
        val inputStream = javaClass.getResourceAsStream("/$fileName")
        inputStream?.let { stream ->
            val reader = InputStreamReader(stream)
            val bufferedReader = BufferedReader(reader)
            val lines = bufferedReader.readLines()

            // Omitir la primera línea (encabezados)
            for (i in 1 until lines.size) {
                val line = lines[i]
                val parts = line.split(",")
                if (parts.size == 8) {
                    val personaje = Personaje(
                        id = parts[0].trim().toInt(),
                        nickName = parts[1].trim(),
                        nombre = parts[2].trim(),
                        edad = parts[3].trim().toInt(),
                        vivo = parts[4].trim().toBoolean(),
                        villano = parts[5].trim().toBoolean(),
                        habilidad = parts[6].trim(),
                        puntosCombate = parts[7].trim().toInt()
                    )
                    personajes.add(personaje)
                } else {
                    println("Error al procesar la línea: $line")
                }
            }

            bufferedReader.close()
        } ?: println("No se pudo encontrar el archivo: $fileName")
    }
    /**

    Obtiene la lista de todos los personajes disponibles en el sistema.
    @return Lista de personajes.
     */
    fun obtenerPersonajes(): List<Personaje> {
        return personajes.toList()
    }
    /**

    Obtiene una lista aleatoria de 5 personajes del sistema.
    @return Lista de personajes aleatorios.
     */
    fun obtenerPersonajesAleatorios(): List<Personaje> {
        return personajes.shuffled().take(5) // Obtener una lista aleatoria de 5 héroes
    }
    /**

    Determina el ganador de la Batalla de Héroes Marvel.

    @return Mensaje indicando el resultado de la batalla.
     */
    fun determinarGanador(): String {
        val tusHeroes = obtenerPersonajesAleatorios()
        val heroesMaquina = obtenerPersonajesAleatorios()

        println("Tus héroes:")
        tusHeroes.forEach { println(it) }

        println("Héroes de la Máquina:")
        heroesMaquina.forEach { println(it) }

        var puntosTuyos = 0
        var puntosMaquina = 0

        for (i in tusHeroes.indices) {
            val heroeTuyo = tusHeroes[i]
            val heroeMaquina = heroesMaquina[i]

            // Comparar las puntuaciones de cada héroe
            if (heroeTuyo.puntosCombate > heroeMaquina.puntosCombate) {
                puntosTuyos++
            } else if (heroeTuyo.puntosCombate < heroeMaquina.puntosCombate) {
                puntosMaquina++
            }
        }

        // Anunciar al ganador de la Batalla de Héroes Marvel
        return when {
            puntosTuyos > puntosMaquina -> "¡Felicidades! ¡Has ganado la Batalla de Héroes Marvel!"
            puntosTuyos < puntosMaquina -> "¡La Máquina ha ganado la Batalla de Héroes Marvel!"
            else -> "La Batalla de Héroes Marvel ha terminado en empate."
        }
    }
}
