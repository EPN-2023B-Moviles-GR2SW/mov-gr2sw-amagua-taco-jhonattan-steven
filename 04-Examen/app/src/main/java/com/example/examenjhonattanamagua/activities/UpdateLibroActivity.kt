package com.example.examenjhonattanamagua.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import com.example.examenjhonattanamagua.R
import com.example.examenjhonattanamagua.db.DB
import com.example.examenjhonattanamagua.dtos.LibroDto

class UpdateLibroActivity : AppCompatActivity() {
    // private val streamingServices = HardcodedStreamingServices.streamingServices
    private val spinnerValues = arrayListOf<String>("Si", "No")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_libro)

        loadDataInEditText(intent)

        val goBackButton = findViewById<ImageButton>(
            R.id.ib_go_back_update_libro_to_libro_list
        )

        goBackButton.setOnClickListener {
            finish()
        }

        val saveUpdatedData = findViewById<Button>(
            R.id.btn_update_libro
        )

        saveUpdatedData.setOnClickListener {
            updateLibro()
            finish()
        }
    }

    private fun updateLibro() {
        val inputTitulo = findViewById<EditText>(R.id.pt_update_title)
        val inputFechPublicacion = findViewById<EditText>(R.id.pt_libro_update_fecha_publicacion)
        val inputNumeroPaginas = findViewById<EditText>(R.id.pt_libro_update_numero_paginas)
        val inputPrecio = findViewById<EditText>(R.id.pt_libro_update_precio)

        val titulo = inputTitulo.text.toString()
        val fechaPublicacion = inputFechPublicacion.text.toString()
        val numeroPaginas = inputNumeroPaginas.text.toString().toInt()
        val precio = inputPrecio.text.toString().toDouble()

        val autorId = intent.getStringExtra("autorId").toString()
        val libroId = intent.getStringExtra("libroId").toString()


        val updatedLibro = LibroDto(
            titulo,
            autorId,
            fechaPublicacion,
            numeroPaginas,
            precio
        )

        DB.libro!!.update(libroId, updatedLibro)

        finish()
    }

    private fun loadDataInEditText(intent: Intent) {
        val autorName = intent.getStringExtra("autorName")
        val titulo = intent.getStringExtra("titulo")
        val fechaPublicacion = intent.getStringExtra("fechaPublicacion")
        val numeroPaginas = intent.getIntExtra("numeroPaginas", 0)
        val precio = intent.getDoubleExtra("precio", 0.0)

        val inputTitulo = findViewById<EditText>(R.id.pt_update_title)
        val inputFechaPublicacion = findViewById<EditText>(R.id.pt_libro_update_fecha_publicacion)
        val inputPrecio = findViewById<EditText>(R.id.pt_libro_update_precio)
        val inputNumeroPaginas = findViewById<EditText>(R.id.pt_libro_update_numero_paginas)

        val inputAutor = findViewById<EditText>(R.id.pt_autor_title)

        inputAutor.setText(autorName)
        inputTitulo.setText(titulo)
        inputFechaPublicacion.setText(fechaPublicacion)
        inputNumeroPaginas.setText(numeroPaginas.toString())
        inputPrecio.setText(precio.toString())
    }
}