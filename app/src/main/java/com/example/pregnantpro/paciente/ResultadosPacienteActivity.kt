package com.example.pregnantpro.paciente

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import com.example.pregnantpro.R

class ResultadosPacienteActivity : AppCompatActivity() {

    private lateinit var botonOrina: Button
    private lateinit var botonInfeccion: Button
    private lateinit var botonSangre: Button
    private lateinit var botonRenal: Button
    private lateinit var botonUltrasonido: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultados_paciente)


        botonOrina = findViewById(R.id.btnOrina)
        botonInfeccion = findViewById(R.id.btnInfeccion)
        botonSangre = findViewById(R.id.btnSangre)
        botonRenal = findViewById(R.id.btnRenal)
        botonUltrasonido = findViewById(R.id.btnUltrasonido)



        botonOrina.setOnClickListener {
            startActivity(Intent(this, ResultadosAnalisisActivity::class.java))
        }

        botonInfeccion.setOnClickListener {
            startActivity(Intent(this, ResultadosInfeccionActivity::class.java))
        }

        botonSangre.setOnClickListener {
            startActivity(Intent(this, ResultadosSangreActivity::class.java))
        }

        botonRenal.setOnClickListener {
            startActivity(Intent(this, ResultadosRenalActivity::class.java))
        }

        botonUltrasonido.setOnClickListener {
            startActivity(Intent(this, ResultadosUltrasonidoActivity::class.java))
        }
    }
}
