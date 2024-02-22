package models

abstract class RedBull: Equipos() {
    var isRapida=false
     fun vueltaRapida():Boolean{
        if ((0..100).random()<2)return true
        return false
    }

    /**
     * mientras que el piloto haya terminado la carrera y que la vuelta no sea rapida
     * hacemos la probabilidad de que sea rapida que es una funcion que es de herencia
     * @param piloto
     *
     */
    fun accionesRedBull(){
        if (!((this as Piloto).acabada()) && !this.isRapida  ) {
            this.isRapida=this.vueltaRapida()
        }
    }
}