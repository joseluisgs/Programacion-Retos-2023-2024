package models

interface Metereologia {

    var lluvia:Boolean
    var contador:Int

    fun lluvia():Boolean{
        if ((0..100).random()<35)return true
        return false
    }


}