package com.example.examenjhonattanamagua.activities

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.examenjhonattanamagua.R
import com.example.examenjhonattanamagua.db.DB
import com.example.examenjhonattanamagua.dtos.LibroDto


class CreateLibroActivity : AppCompatActivity() {
    private val spinnerValues = arrayListOf<String>("Si", "No")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_libro)

        val goBackButton = findViewById<ImageButton>(
            R.id.go_back_to_autores_list
        )

        goBackButton.setOnClickListener {
            finish()
        }

        val textLibroAutor = findViewById<EditText>(R.id.pt_libro_autor)

        val autorName = intent.getStringExtra("autorName").toString()
        textLibroAutor.setText(autorName)


        val saveNewLibroButton = findViewById<Button>(
            R.id.btn_new_libro
        )

        val autorId = intent.getStringExtra("autorId").toString()

        saveNewLibroButton.setOnClickListener {
            createLibro(autorId)
        }

    }

    private fun createLibro(autorId: String) {
        val inputTitulo = findViewById<EditText>(R.id.pt_libro_title)
        val inputFechaPublicacion = findViewById<EditText>(R.id.pt_libro_fecha_publicacion)
        val inputNumeroPaginas = findViewById<EditText>(R.id.pt_libro_numero_paginas)
        val inputPrecio = findViewById<EditText>(R.id.pt_libro_precio)

        val titulo = inputTitulo.text.toString()
        val fechaPublicacion = inputFechaPublicacion.text.toString()
        val numeroPaginas = inputNumeroPaginas.text.toString().toInt()
        val precio = inputPrecio.text.toString().toDouble()



        val newLibro = LibroDto(
            titulo,
            autorId,
            fechaPublicacion,
            numeroPaginas,
            precio
        )

        DB.libro!!.create(newLibro)

        finish()
    }
}