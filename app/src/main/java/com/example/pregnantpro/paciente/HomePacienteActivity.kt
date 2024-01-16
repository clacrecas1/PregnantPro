package com.example.pregnantpro.paciente

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import com.example.pregnantpro.AuthActivity
import com.example.pregnantpro.R

class HomePacienteActivity : AppCompatActivity() {

    //Defincion botones
    private lateinit var btnEcografias: Button
    private lateinit var btnPlanificacionCitas : Button
    private lateinit var btnEvolucion: Button
    private lateinit var btnRecomendaciones: Button
    private lateinit var btnInformacionPersonal : Button
    private lateinit var btnResultados : Button
    private lateinit var btnRecetas : Button

    private lateinit var imageBtnCerrarSesion: ImageButton



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_paciente)

        btnEcografias = findViewById(R.id.btnEcografias)
        btnPlanificacionCitas = findViewById(R.id.btnRecetasGine)
        btnEvolucion = findViewById(R.id.btnResultadosGinecologa)
        btnRecomendaciones = findViewById(R.id.btnRecomendaciones)
        btnRecetas = findViewById(R.id.btnRecetas)


        btnInformacionPersonal = findViewById(R.id.btnInformacionPersonalGinecologa)
        btnResultados = findViewById(R.id.btnResultados)
        imageBtnCerrarSesion = findViewById(R.id.btnVolver)

        btnInformacionPersonal.setOnClickListener {
            startActivity(Intent(this, InfoPacienteActivity::class.java))

        }
        btnResultados.setOnClickListener {
            startActivity(Intent(this, ResultadosPacienteActivity::class.java))
        }


        btnEvolucion.setOnClickListener {
            startActivity(Intent(this, EvolucionPacienteActivity::class.java))
        }

        imageBtnCerrarSesion.setOnClickListener {
            startActivity(Intent(this, AuthActivity::class.java))
        }

        btnPlanificacionCitas.setOnClickListener {
            startActivity(Intent(this, PlanificacionCitasPacienteActivity::class.java))
        }

        btnEcografias.setOnClickListener {
            startActivity(Intent(this, EcografiasInfo1Activity::class.java))
        }

        btnRecomendaciones.setOnClickListener {
            startActivity(Intent(this, RecomendacionesPacienteActivity::class.java))
        }
        btnRecetas.setOnClickListener {
            startActivity(Intent(this, RecetasPacienteActivity::class.java))
        }


    }



}