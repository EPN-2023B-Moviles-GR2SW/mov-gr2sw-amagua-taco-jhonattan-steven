package com.example.b2023gr2sw

class BBaseDatosMemoria {
    companion object{//Companion Object es aquello que se puede compartir sin la necesidad de crear una nueva instancia
        // es decir si no estuviera el companion object, deberiamos crear una instancia de esta clase para
        //que se cree el arregloBEntrenador
        //Son las variables que se pueden obtener sin la necesidad de instanciar la clase
        val arregloBEntrenador = arrayListOf<BEntrenador>()
        init{
            arregloBEntrenador.add(BEntrenador(1,"Adrian","a@a.com"))
            arregloBEntrenador.add(BEntrenador(2,"Juan","b@b.com"))
            arregloBEntrenador.add(BEntrenador(3,"Mirian","c@c.com"))
        }
    }
}