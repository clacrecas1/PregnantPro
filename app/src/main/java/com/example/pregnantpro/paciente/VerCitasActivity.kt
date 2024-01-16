package com.example.pregnantpro.paciente

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pregnantpro.R
import com.example.pregnantpro.modeloCita.Cita
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore

class VerCitasActivity : AppCompatActivity() {

    private lateinit var listaCitas: ListView
    private lateinit var tvTitulo: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_citas)

        listaCitas = findViewById(R.id.listaCitas)
        tvTitulo = findViewById(R.id.tvTitulo)



        // Puedes agregar lógica para recuperar y mostrar las citas almacenadas en Firebase aquí
        mostrarCitasDesdeFirebase()
    }

    private fun mostrarCitasDesdeFirebase() {
        val auth = Firebase.auth
        val email = auth.currentUser!!.email.toString()

        val citasCollection = Firebase.firestore.collection("pacientes")
            .document(email).collection("citas")

        citasCollection.get().addOnSuccessListener { result ->
            val citasList = mutableListOf<Cita>()

            for (document in result) {
                val tipo = document.getString("tipo") ?: ""
                val fecha = document.getString("fecha") ?: ""
                val hora = document.getString("hora") ?: ""

                val cita = Cita(tipo, fecha, hora)
                citasList.add(cita)
            }

            // Mostrar las citas en la lista
            mostrarCitasEnLista(citasList)
        }
    }


    private fun mostrarCitasEnLista(citas: List<Cita>) {
        val adapter = ArrayAdapter(this, R.layout.item_cita, R.id.tvTipoCita, citas)
        listaCitas.adapter = adapter

        // Lógica para manejar la selección de una cita en la lista
        listaCitas.setOnItemClickListener { _, _, position, _ ->
            val citaSeleccionada = citas[position]

            // Aquí puedes agregar lógica para manejar la selección de una cita
            // Puedes mostrar más detalles de la cita o realizar otras acciones
            // según sea necesario.
            mostrarDetallesCita(citaSeleccionada)
        }
    }

    private fun mostrarDetallesCita(cita: Cita) {
        // Ejemplo: Mostrar los detalles de la cita en un Toast
        val mensaje = "Tipo: ${cita.tipo}\nFecha: ${cita.fecha}\nHora: ${cita.hora}"
        showToast(mensaje)
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}