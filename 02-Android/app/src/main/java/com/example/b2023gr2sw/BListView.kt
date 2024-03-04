package com.example.b2023gr2sw

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView

class BListView : AppCompatActivity() {
    val arreglo = BBaseDatosMemoria.arregloBEntrenador
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blist_view)

        val listView = findViewById<ListView>(R.id.lv_list_view)
        val adaptador = ArrayAdapter(this/*Contexto*/, android.R.layout.simple_list_item_1 /*Como se va a ver XML*/,arreglo/*Contenido*/)

        listView.adapter = adaptador
        adaptador.notifyDataSetChanged()// si se modifican los datos esta funcion notifica los cambios

        val botonAniadirListView = findViewById<Button>(R.id.btn_aniadir_list_view)
        botonAniadirListView.setOnClickListener{
            //click
        }


    }
}