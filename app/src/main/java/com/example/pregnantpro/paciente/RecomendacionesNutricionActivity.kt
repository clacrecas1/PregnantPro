package com.example.pregnantpro.paciente

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.MediaController
import android.widget.TextView
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pregnantpro.R
import com.example.pregnantpro.modeloRecomendaciones.RecomendacionesNutricionAdapter

class RecomendacionesNutricionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recomendaciones_nutricion)

        // Configurar el RecyclerView
        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewRecomendacionesNutricion)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Lista de recomendaciones con iconos personalizados
        val recomendacionesList: List<String> = listOf(
            "Consume al menos 5 porciones de frutas y verduras al día.",
            "Bebe suficiente agua para mantener una hidratación adecuada.",


            // Agrega más recomendaciones según sea necesario
        )

        // Configurar el adaptador con la lista de recomendaciones
        val adapter = RecomendacionesNutricionAdapter(recomendacionesList)
        recyclerView.adapter = adapter

        val videoPath = "android.resource://" + packageName + "/" + R.raw.nutricion
        val videoView: VideoView = findViewById(R.id.videoViewNutricion)
        val mediaController = MediaController(this)
        mediaController.setAnchorView(videoView)
        videoView.setMediaController(mediaController)
        videoView.setVideoURI(Uri.parse(videoPath))
        videoView.start()
    }
}
