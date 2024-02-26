package models

abstract class Mercedes: Equipos() {

    var isSaftyCar=false
    fun safteyCar():Boolean{
        if ((0..100).random()<3)return true
        return false
    }

    /**
     * mientras que el piloto haya terminado la carrera y que no tenga saftey car
     * hacemos la probabilidad de que sea sayftey car que es una funcion que es de herencia
     * @param piloto
     *
     */
    fun accionesMercedes(){
        if (!((this as Piloto).acabada())&& !this.isSaftyCar ){
            this.isSaftyCar=this.safteyCar()
            if (this.isSaftyCar){
                println("A ${this.nombre} LE SALE EL SAYFTEY CAR")
                if (this.columna==0){
                    this.columna=9
                    this.vuelta--
                }
                else this.columna--
                this.isSaftyCar=false
            }
        }
    }
}