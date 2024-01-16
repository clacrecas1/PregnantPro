package com.example.pregnantpro.paciente

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pregnantpro.R

import android.content.Intent

import android.widget.Button


class RecomendacionesPacienteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recomendaciones_paciente)

        val btnNutricion: Button = findViewById(R.id.btnNutricion)
        val btnEjercicio: Button = findViewById(R.id.btnEjercicio)
        val btnDescanso: Button = findViewById(R.id.btnDescanso)
        val btnVitaminas: Button = findViewById(R.id.btnVitaminas)

        btnNutricion.setOnClickListener {
            startActivity(Intent(this, RecomendacionesNutricionActivity::class.java))
        }

        btnEjercicio.setOnClickListener {
            startActivity(Intent(this, RecomendacionesEjercicioActivity::class.java))
        }

        btnDescanso.setOnClickListener {
            startActivity(Intent(this, RecomendacionesDescansoActivity::class.java))
        }

        btnVitaminas.setOnClickListener {
            startActivity(Intent(this, RecomendacionesVitaminasActivity::class.java))
        }
    }
}
