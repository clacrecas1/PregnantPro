package com.example.pregnantpro.paciente

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.MediaController
import android.widget.VideoView
import com.example.pregnantpro.R


import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pregnantpro.modeloRecomendaciones.RecomendacionesNutricionAdapter
import com.example.pregnantpro.modeloRecomendaciones.RecomendacionesVitaminasAdapter

// RecomendacionesVitaminasActivity.kt
class RecomendacionesVitaminasActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recomendaciones_vitaminas)

        // Configurar el RecyclerView
        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewRecomendacionesVitaminas)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Lista de recomendaciones con iconos personalizados
        val recomendacionesList: List<String> = listOf(
            "Consume alimentos ricos en vitamina C, como cítricos y fresas.",
            "Asegúrate de obtener suficiente vitamina D a través de la exposición al sol o suplementos.",

            // Agrega más recomendaciones según sea necesario
        )

        // Configurar el adaptador con la lista de recomendaciones
        val adapter = RecomendacionesVitaminasAdapter(recomendacionesList)
        recyclerView.adapter = adapter

        // Configurar el video
        val videoPath = "android.resource://" + packageName + "/" + R.raw.vitaminas
        val videoView: VideoView = findViewById(R.id.videoViewVitaminas)
        val mediaController = MediaController(this)
        mediaController.setAnchorView(videoView)
        videoView.setMediaController(mediaController)
        videoView.setVideoURI(Uri.parse(videoPath))
        videoView.start()
    }
}

