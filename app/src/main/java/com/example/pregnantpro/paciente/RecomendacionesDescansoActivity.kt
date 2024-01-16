package com.example.pregnantpro.paciente


import com.example.pregnantpro.R



import com.example.pregnantpro.modeloRecomendaciones.RecomendacionesDescansoAdapter
import com.example.pregnantpro.modeloRecomendaciones.RecomendacionesNutricionAdapter

// RecomendacionesDescansoActivity.kt
import android.net.Uri
import android.os.Bundle
import android.widget.MediaController
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RecomendacionesDescansoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recomendaciones_descanso)

        // Configurar el RecyclerView
        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewRecomendacionesDescanso)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Lista de recomendaciones con iconos personalizados
        val recomendacionesList: List<String> = listOf(
            "Duerme al menos 7-9 horas por noche para un óptimo descanso.",
            "Crea un ambiente propicio para el sueño en tu dormitorio.",

            // Agrega más recomendaciones según sea necesario
        )

        // Configurar el adaptador con la lista de recomendaciones
        val adapter = RecomendacionesDescansoAdapter(recomendacionesList)
        recyclerView.adapter = adapter

        // Configurar el video
        val videoPath = "android.resource://" + packageName + "/" + R.raw.descanso
        val videoView: VideoView = findViewById(R.id.videoViewDescanso)
        val mediaController = MediaController(this)
        mediaController.setAnchorView(videoView)
        videoView.setMediaController(mediaController)
        videoView.setVideoURI(Uri.parse(videoPath))
        videoView.start()
    }
}
