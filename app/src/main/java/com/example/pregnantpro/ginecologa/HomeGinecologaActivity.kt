package com.example.pregnantpro.ginecologa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import com.example.pregnantpro.AuthActivity
import com.example.pregnantpro.R

class HomeGinecologaActivity : AppCompatActivity() {

    private lateinit var btnVolver: ImageButton
    private lateinit var btnInfoPersonalGinecologa: Button
    private lateinit var btnResultadosGinecologa: Button
    private lateinit var btnRecetasGine: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_ginecologa)

        btnVolver = findViewById(R.id.btnVolver)
        btnVolver.setOnClickListener {
            val intent = Intent(this, AuthActivity::class.java)
            startActivity(intent)
        }

        btnInfoPersonalGinecologa = findViewById(R.id.btnInformacionPersonalGinecologa)
        btnInfoPersonalGinecologa.setOnClickListener {
            val intent = Intent(this, InfoPersonalGinecologaActivity::class.java)
            startActivity(intent)
        }

        btnResultadosGinecologa = findViewById(R.id.btnResultadosGinecologa)
        btnResultadosGinecologa.setOnClickListener {
            val intent = Intent(this, ResultadosGinecologaActivity::class.java)
            startActivity(intent)
        }

        btnRecetasGine = findViewById(R.id.btnRecetasGine)
        btnRecetasGine.setOnClickListener {
            val intent = Intent(this, RecetasGinecologaActivity::class.java)
            startActivity(intent)
        }
    }
}
