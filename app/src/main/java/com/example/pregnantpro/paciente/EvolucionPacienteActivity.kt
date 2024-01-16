package com.example.pregnantpro.paciente

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.pregnantpro.R

class EvolucionPacienteActivity : AppCompatActivity() {

    private lateinit var btnAbrirVideo : Button
    private lateinit var btnGuiaTamaños : Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_evolucion_paciente)

        btnAbrirVideo = findViewById( R.id.btnAbrirVideo)
        btnGuiaTamaños = findViewById(R.id.btnGuiaTamaños)

        btnAbrirVideo.setOnClickListener {
            startActivity(Intent(this, VideoEvolucionPacienteMainActivity::class.java))
        }

        btnGuiaTamaños.setOnClickListener {
            startActivity(Intent(this, TamanoSemanasActivity::class.java))
        }




    }
}