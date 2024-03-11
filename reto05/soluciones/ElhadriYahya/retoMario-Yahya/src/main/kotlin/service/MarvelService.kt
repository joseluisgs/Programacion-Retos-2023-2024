package service

import models.Personaje
import storage.Storage
import java.io.File
import java.nio.file.Files
import java.time.LocalDate
import kotlin.io.path.Path


class MarvelService(
    val storage: Storage<Personaje>
) {
    private var heroesPersona:MutableList<Personaje> = mutableListOf()
    private var heroesMaquina:MutableList<Personaje> = mutableListOf()

    val lista=storage.load(File("src/main/resources/personajes.csv"))
    fun simulacion(){
        var contador=0
        do{
            val num=(0..29).random()
            if (!heroesMaquina.contains(lista[num])){
                heroesMaquina.add(lista[num])
                contador++
            }
        }while (contador<5)
        contador=0
        do{
            val num=(0..29).random()
            if (!heroesPersona.contains(lista[num])){
                heroesPersona.add(lista[num])
                contador++
            }
        }while (contador<5)

        val puntosMaquina=heroesMaquina.sumOf { it.puntosCombate }
        val puntosPersona=heroesPersona.sumOf { it.puntosCombate }
        var ganador:String=""
        if (puntosPersona > puntosMaquina) ganador="Yahya"
        if (puntosMaquina > puntosPersona) ganador= "Maquina"
        if (puntosMaquina == puntosPersona) ganador= "empate"

        Files.createDirectories(Path("informe"))
        val file= Path("informe","bitacora.txt").toFile()
        if (!file.exists()) file.createNewFile()
        file.appendText("INFORME DE BATALLA\n")
        file.appendText("FECHA: ${LocalDate.now()}\n")
        file.appendText("LISTA DE YAHYA: ${heroesPersona.map { it.nombre }}\n")
        file.appendText("LISTA DE LA MAQUINA: ${heroesMaquina.map { it.nombre }}\n")
        file.appendText("PUNTOS DE YAHYA: $puntosPersona\n")
        file.appendText("PUNTOS DE LA MAQUINA: $puntosMaquina\n")
        file.appendText("GANADOR: $ganador\n")
    }
    fun leer():List<Personaje>{
        return storage.load(File("src/main/resources/personajes.csv"))
    }


}