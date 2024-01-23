package com.example.examenjhonattanamagua.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Spinner
import com.example.examenjhonattanamagua.R
import com.example.examenjhonattanamagua.db.DB
import com.example.examenjhonattanamagua.dtos.AutorDto

class UpdateAutorActivity : AppCompatActivity() {
    private val spinnerValues = arrayListOf<String>("Si", "No")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_autor)

        loadDataInEditText()

        val goBackButton = findViewById<ImageButton>(R.id.btn_go_back)

        goBackButton.setOnClickListener {
            finish()
        }

        val saveUpdateDataButton = findViewById<Button>(R.id.btn_update_supermercado)

        saveUpdateDataButton.setOnClickListener {
            saveUpdateData()
        }
    }

    private fun loadDataInEditText() {
        val inputNombre = findViewById<EditText>(R.id.txt_autor_update_nombre)
        val inputNacionalidad = findViewById<EditText>(R.id.txt_autor_update_nacionalidad)
        val inputAnioNacimiento = findViewById<EditText>(R.id.txt_autor_update_anioNacimiento)

        val nacionalidad = intent.getStringExtra("nacionalidad")
        val nombre = intent.getStringExtra("nombre")
        val anioNacimiento = intent.getStringExtra("anioNacimiento")
        val tienePremioNobel =intent.getBooleanExtra("tienePremioNobel", false)

        inputNombre.setText(nombre)
        inputAnioNacimiento.setText(anioNacimiento.toString())
        inputNacionalidad.setText(nacionalidad)
        loadSpinner(tienePremioNobel)
    }

    private fun saveUpdateData() {
        val inputNombre = findViewById<EditText>(R.id.txt_autor_update_nombre)
        val inputNacionalidad = findViewById<EditText>(R.id.txt_autor_update_nacionalidad)
        val inputAnioNacimiento = findViewById<EditText>(R.id.txt_autor_update_anioNacimiento)
        val inputTieneNobel = findViewById<Spinner>(R.id.spinner_update_tiene_premio_nobel)

        val nombre = inputNombre.text.toString()
        val anioNacimiento = inputAnioNacimiento.text.toString().toInt()
        val nacionalidad = inputNacionalidad.text.toString()
        val tieneNobel = inputTieneNobel.selectedItem.toString() == "Si"

        val autorId = intent.getStringExtra("id")

        val changes = AutorDto(
            nombre = nombre,
            nacionalidad = nacionalidad,
            anioNacimiento = anioNacimiento,
            premioNobel = tieneNobel
        )

        DB.autor!!.update(autorId!!, changes)

        finish()
    }

    private fun loadSpinner(value: Boolean) {
        val spinnerTieneNobel = findViewById<Spinner>(
            R.id.spinner_update_tiene_premio_nobel
        )
        val arrayAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            spinnerValues
        )
        arrayAdapter.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )
        spinnerTieneNobel.adapter = arrayAdapter
        val selection = if (value) 0 else 1
        spinnerTieneNobel.setSelection(selection)
    }
}