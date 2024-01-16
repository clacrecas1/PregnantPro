package com.example.pregnantpro.paciente

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import androidx.viewpager2.widget.ViewPager2
import com.example.pregnantpro.R
import com.example.pregnantpro.modeloTamano.SemanasAdapter
import com.example.pregnantpro.modeloTamano.SemanasModel


class TamanoSemanasActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tamano_semanas)

        viewPager = findViewById(R.id.viewPager)

        val semanasList = mutableListOf<SemanasModel>()
        for (i in 4..40) {
            semanasList.add(SemanasModel(i))
        }

        val semanasAdapter = SemanasAdapter(semanasList)
        viewPager.adapter = semanasAdapter


    }
}