package com.example.pregnantpro.paciente

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.MediaController
import android.widget.VideoView
import com.example.pregnantpro.R


import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pregnantpro.modeloRecomendaciones.RecomendacionesEjercicioAdapter
import com.example.pregnantpro.modeloRecomendaciones.RecomendacionesNutricionAdapter

class RecomendacionesEjercicioActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recomendaciones_ejercicio)

        // Configurar el RecyclerView
        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewRecomendacionesEjercicio)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Lista de recomendaciones con iconos personalizados
        val recomendacionesList: List<String> = listOf(
            "Realiza al menos 30 minutos de ejercicio cardiovascular al día",
            "Incorpora entrenamiento de fuerza al menos dos veces por semana.",


            // Agrega más recomendaciones según sea necesario
        )

        // Configurar el adaptador con la lista de recomendaciones
        val adapter = RecomendacionesEjercicioAdapter(recomendacionesList)
        recyclerView.adapter = adapter

        // Configurar el video
        val videoPath = "android.resource://" + packageName + "/" + R.raw.ejercicio
        val videoView: VideoView = findViewById(R.id.videoViewEjercicio)
        val mediaController = MediaController(this)
        mediaController.setAnchorView(videoView)
        videoView.setMediaController(mediaController)
        videoView.setVideoURI(Uri.parse(videoPath))
        videoView.start()
    }
}
