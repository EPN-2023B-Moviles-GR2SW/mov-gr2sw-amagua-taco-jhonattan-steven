package com.example.examenjhonattanamagua.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.examenjhonattanamagua.R
import com.example.examenjhonattanamagua.db.DB
import com.example.examenjhonattanamagua.dtos.AutorDto
import com.example.examenjhonattanamagua.models.Autor

class CreateAutorActivity : AppCompatActivity() {
    private var autors: ArrayList<Autor>? = null
    private val spinnerValues = arrayListOf<String>("Si", "No")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_autor)

        // database
        autors = DB.autor!!.getAll()

        val goBackButton = findViewById<ImageButton>(R.id.btn_go_back)

        goBackButton.setOnClickListener{
            finish()
        }

        loadSpinner()

        val saveNewStreamingServiceButton = findViewById<Button>(
            R.id.btn_new_autor
        )

        saveNewStreamingServiceButton.setOnClickListener {
            createAutor()
        }
    }

    private fun createAutor() {
        val inputNombre = findViewById<EditText>(R.id.txt_autor_nombre)
        val inputAnioNacimiento = findViewById<EditText>(R.id.txt_autor_anioNacimiento)
        val inputNacionalidad = findViewById<EditText>(R.id.txt_autor_nacionalidad)
        val inputPremioNobel = findViewById<Spinner>(R.id.spinner_tiene_premio_nobel)

        val nombre = inputNombre.text.toString()
        val anioNacimiento = inputAnioNacimiento.text.toString().toInt()
        val nacionalidad = inputNacionalidad.text.toString()
        val premioNobel = inputPremioNobel.selectedItem.toString() == "Si"

        val newAutor = AutorDto(
            nombre,
            anioNacimiento,
            nacionalidad,
            premioNobel
        )

        DB.autor!!.create(newAutor)

        finish()
    }

    private fun loadSpinner() {
        val spinnerIsSeriesFinished = findViewById<Spinner>(
            R.id.spinner_tiene_premio_nobel
        )

        val arrayAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            spinnerValues
        )

        arrayAdapter.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )

        spinnerIsSeriesFinished.adapter = arrayAdapter
    }
}