package com.example.pregnantpro.paciente

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.VideoView
import com.example.pregnantpro.R
import android.widget.MediaController

class VideoEvolucionPacienteMainActivity : AppCompatActivity() {
    private lateinit var videoViewBebe: VideoView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_evolucion_paciente_main)

        videoViewBebe = findViewById(R.id.videoViewBebe)

        // Establecer la ruta del video
        val videoPath = "android.resource://" + packageName + "/" + R.raw.bebe

        // Configurar el reproductor de medios
        val mediaController = MediaController(this)
        mediaController.setAnchorView(videoViewBebe)
        videoViewBebe.setMediaController(mediaController)

        // Establecer la fuente del video
        videoViewBebe.setVideoURI(Uri.parse(videoPath))

        // Iniciar la reproducción del video
        videoViewBebe.start()
    }




    }

