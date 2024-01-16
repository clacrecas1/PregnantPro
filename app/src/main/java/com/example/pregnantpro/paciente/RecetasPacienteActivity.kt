package com.example.pregnantpro.paciente

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pregnantpro.R
import com.example.pregnantpro.ginecologa.RecetasGinecologaActivity

class RecetasPacienteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recetas_paciente)

        // Puedes abrir RecetasGinecologoActivity directamente
        abrirRecetasGinecologo()
    }

    private fun abrirRecetasGinecologo() {
        val intent = Intent(this, RecetasGinecologaActivity::class.java)
        startActivity(intent)
        finish() // Opcional: cierra la actividad actual si no deseas volver atrás
    }
}