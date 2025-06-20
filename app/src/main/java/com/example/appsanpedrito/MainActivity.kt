package com.example.appsanpedrito

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.button.MaterialButton
import com.google.android.material.button.MaterialButtonToggleGroup
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.MaterialAutoCompleteTextView

import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    // Declaraciones de vistas
    private lateinit var toggleGroup: MaterialButtonToggleGroup

    private lateinit var cardAlumno: View
    private lateinit var cardDocente: View

    private lateinit var promoDropdown: MaterialAutoCompleteTextView
    private lateinit var cursoDropdown: MaterialAutoCompleteTextView

    private lateinit var btnEnviarAlumno: View
    private lateinit var btnEnviarDocente: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // Cambiar el título y color del texto (si tu tema no lo controla)
        supportActionBar?.title = "APP SAN PEDRITO"

        // Referencias
        toggleGroup = findViewById(R.id.toggleGroup)
        cardAlumno = findViewById(R.id.cardAlumno)
        cardDocente = findViewById(R.id.cardDocente)

        promoDropdown = findViewById(R.id.promoDropdown)
        cursoDropdown = findViewById(R.id.cursoDropdown)

        btnEnviarAlumno = findViewById(R.id.btnEnviarAlumno)
        btnEnviarDocente = findViewById(R.id.btnEnviarDocente)

        // Opciones para los DropDown
        val promociones = listOf("2019- 1", "2020 - 2", "2021 - 1", "2022 - 1" , "2023 - 1" , "2023 - 2", "2024 - 1")
        val cursos = listOf("Dinamica 1", "Algoritmos", "Programacion", "Algoritmos Evolutivos", "Tesis I", "Aplicaciones moviles")

        val promoAdapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, promociones)
        val cursoAdapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, cursos)

        promoDropdown.setAdapter(promoAdapter)
        cursoDropdown.setAdapter(cursoAdapter)

        // Cambiar entre formularios
        toggleGroup.addOnButtonCheckedListener { _, checkedId, isChecked ->
            if (isChecked) {
                when (checkedId) {
                    R.id.btnAlumno -> {
                        cardAlumno.visibility = View.VISIBLE
                        cardDocente.visibility = View.GONE
                    }
                    R.id.btnDocente -> {
                        cardAlumno.visibility = View.GONE
                        cardDocente.visibility = View.VISIBLE
                    }
                }
            }
        }

        //COLOR DEL SEGMENT SIN USAR

        val alumnoBtn = findViewById<MaterialButton>(R.id.btnAlumno)
        val docenteBtn = findViewById<MaterialButton>(R.id.btnDocente)

        // Estado inicial: ningún botón seleccionado
        alumnoBtn.setBackgroundColor(ContextCompat.getColor(this, R.color.fondo))
        alumnoBtn.setTextColor(ContextCompat.getColor(this, android.R.color.white))

        docenteBtn.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
        docenteBtn.setTextColor(ContextCompat.getColor(this, android.R.color.black))


        val radioGroup = findViewById<RadioGroup>(R.id.alumnoVestimenta)
        val formal = findViewById<RadioButton>(R.id.alumnoFormal)
        val informal = findViewById<RadioButton>(R.id.alumnoInformal)

        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.alumnoFormal -> {
                    formal.setTextColor(ContextCompat.getColor(this, R.color.fondo2))
                    informal.setTextColor(ContextCompat.getColor(this, android.R.color.black))
                    formal.buttonTintList = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.fondo2))
                    informal.buttonTintList = ColorStateList.valueOf(ContextCompat.getColor(this, android.R.color.darker_gray))
                }
                R.id.alumnoInformal -> {
                    informal.setTextColor(ContextCompat.getColor(this, R.color.fondo2))
                    formal.setTextColor(ContextCompat.getColor(this, android.R.color.black))
                    informal.buttonTintList = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.fondo2))
                    formal.buttonTintList = ColorStateList.valueOf(ContextCompat.getColor(this, android.R.color.darker_gray))
                }
            }
        }


        // DOCENTE
        val docenteGroup = findViewById<RadioGroup>(R.id.docenteVestimenta)
        val docenteFormal = findViewById<RadioButton>(R.id.docenteFormal)
        val docenteInformal = findViewById<RadioButton>(R.id.docenteInformal)

        docenteGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.docenteFormal -> {
                    docenteFormal.setTextColor(ContextCompat.getColor(this, R.color.fondo2))
                    docenteInformal.setTextColor(ContextCompat.getColor(this, android.R.color.black))
                    docenteFormal.buttonTintList = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.fondo2))
                    docenteInformal.buttonTintList = ColorStateList.valueOf(ContextCompat.getColor(this, android.R.color.darker_gray))
                }
                R.id.docenteInformal -> {
                    docenteInformal.setTextColor(ContextCompat.getColor(this, R.color.fondo2))
                    docenteFormal.setTextColor(ContextCompat.getColor(this, android.R.color.black))
                    docenteInformal.buttonTintList = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.fondo2))
                    docenteFormal.buttonTintList = ColorStateList.valueOf(ContextCompat.getColor(this, android.R.color.darker_gray))
                }
            }
        }


        // Botón Enviar Alumno
        btnEnviarAlumno.setOnClickListener {
            Snackbar.make(it, "Formulario Alumno enviado", Snackbar.LENGTH_SHORT).show()
            // Limpiar campos del formulario de Alumno
            findViewById<TextInputEditText>(R.id.nombreAlumno).text?.clear()
            findViewById<TextInputEditText>(R.id.apellidoAlumno).text?.clear()
            findViewById<TextInputEditText>(R.id.codigoAlumno).text?.clear()
            findViewById<TextInputEditText>(R.id.celularAlumno).text?.clear()
            findViewById<AutoCompleteTextView>(R.id.promoDropdown).setText("")
            findViewById<RadioGroup>(R.id.alumnoVestimenta).clearCheck()

        }

        btnEnviarDocente.setOnClickListener {
            Snackbar.make(it, "Formulario Docente enviado", Snackbar.LENGTH_SHORT).show()
            // Limpiar campos del formulario de Docente
            findViewById<TextInputEditText>(R.id.nombreDocente).text?.clear()
            findViewById<TextInputEditText>(R.id.apellidoDocente).text?.clear()
            findViewById<TextInputEditText>(R.id.celularDocente).text?.clear()
            findViewById<AutoCompleteTextView>(R.id.cursoDropdown).setText("")

        }
//CAMBIO DE COLOR POR SEGMENTO
        toggleGroup.addOnButtonCheckedListener { group, checkedId, isChecked ->
            val alumnoBtn = findViewById<MaterialButton>(R.id.btnAlumno)
            val docenteBtn = findViewById<MaterialButton>(R.id.btnDocente)

            if (checkedId == R.id.btnAlumno && isChecked) {
                alumnoBtn.setBackgroundColor(ContextCompat.getColor(this, R.color.fondo))
                alumnoBtn.setTextColor(ContextCompat.getColor(this, android.R.color.white))

                docenteBtn.setBackgroundColor(ContextCompat.getColor(this, R.color.gray))
                docenteBtn.setTextColor(ContextCompat.getColor(this, android.R.color.black))

                cardAlumno.visibility = View.VISIBLE
                cardDocente.visibility = View.GONE

            } else if (checkedId == R.id.btnDocente && isChecked) {
                docenteBtn.setBackgroundColor(ContextCompat.getColor(this, R.color.fondo))
                docenteBtn.setTextColor(ContextCompat.getColor(this, android.R.color.white))

                alumnoBtn.setBackgroundColor(ContextCompat.getColor(this, R.color.gray))
                alumnoBtn.setTextColor(ContextCompat.getColor(this, android.R.color.black))

                cardAlumno.visibility = View.GONE
                cardDocente.visibility = View.VISIBLE
            }
        }

    }


    // Muestra el menú de opciones
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    // Acción cuando tocas una opción
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_inicio -> {
                Snackbar.make(findViewById(android.R.id.content), "Ya estás en la página de inicio", Snackbar.LENGTH_SHORT).show()
                true
            }
            R.id.menu_pagina -> {
                val intent = Intent(this, WebViewActivity::class.java)
                intent.putExtra("url", "https://andina.pe/agencia/noticia-ancash-chimbote-se-prepara-para-vivir-festividad-san-pedrito-2025-1034079.aspx#:~:text=La%20festividad%2C%20en%20honor%20a,en%20la%20bah%C3%ADa%20de%20Chimbote.")
                startActivity(intent)
                true
            }
            R.id.menu_miembros -> {
                val intent = Intent(this, WebViewActivity::class.java)
                intent.putExtra("url", "https://www.chimboteonline.com/threads/fiesta-patronal-de-san-pedrito.12/#google_vignette")
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}


