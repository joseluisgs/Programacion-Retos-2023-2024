package org.example.services

import org.example.models.Hero
import org.example.services.Backup.BackUpImpl
import org.example.storage.InformeStorageJson
import java.io.File
import java.nio.file.Files
import java.time.LocalDate
import kotlin.io.path.Path

class HeroService(val lista: List<Hero>,  val informeStorageJson:  InformeStorageJson, val backup: BackUpImpl ) {
    fun enfrentamiento(){
        val miLista = lista.shuffled().take(5)
        val listaSistema = (lista - miLista).shuffled().take(5)
        val misPuntos = miLista.sumOf { it.pc }
        val sistemaPuntos = listaSistema.sumOf { it.pc }
        var ganador = ""
        if(misPuntos > sistemaPuntos)  ganador = "Jugador"
        if (misPuntos < sistemaPuntos) ganador ="Sistema"
        if(misPuntos == sistemaPuntos) ganador = "Empate"
        val informe = listOf("Fecha: ${LocalDate.now()}",
            "Lista jugador: ${miLista.map { it.nombre }}",
            "Lista Sistema: ${listaSistema.map { it.nombre }}",
            "Puntos Jugador: $misPuntos",
            "Puntos Sistema: $sistemaPuntos",
            "Ganador: $ganador"
        )
        informeStorageJson.writeFromFile(informe)
        backup.backUp()
    }
}