package org.example.models.Pilotos

import org.example.models.Patrocinadores.Mercedes

/**
 * @author Mario de Domingo
 * @version 1.0-SNAPSHOT
 * @param i Coordenadas de la fila
 * @param j Coordenadas de la columna
 */
class Hamilton(i: Int, j: Int) :Pilotos(" 🟡 ","Hamilton",10, i, j),Mercedes{
    override fun accion() {
        if(!accidente){
            if (moverse == 0){
                movimiento()
                accidente()
                error()
            } else {moverse -= 1}
        }
    }

    /**
     * si da error el piloto retrocede una casilla
     */
    override fun error(){
        if (j!=0&&j!=1){
            val prov = (1..100).random()
            if(prov<=15){
                j-=2
                println("Error: $nombre retrocede 1 casilla")
            }

        }
    }
}