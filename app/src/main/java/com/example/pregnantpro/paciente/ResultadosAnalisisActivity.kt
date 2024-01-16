package com.example.pregnantpro.paciente

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pregnantpro.R
import com.example.pregnantpro.modeloResultados.ResultadoAdapter
import com.example.pregnantpro.modeloResultados.ResultadoAnalisis
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore

class ResultadosAnalisisActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultados_analisis)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewResultados)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val resultados = listOf(
            ResultadoAnalisis(
                "Leucocitos",
                "Los leucocitos son células de defensa. Su presencia en la orina puede indicar una infección.",

            ),
            ResultadoAnalisis(
                "Glucosa",
                "La presencia de glucosa en la orina puede indicar diabetes o problemas en la absorción de glucosa.",

            ),
            // Agrega más resultados según tus necesidades
        )

        // Guardar cita en Firebase


        val adapter = ResultadoAdapter(resultados)
        recyclerView.adapter = adapter

        guardarResultadosEnFirestore(resultados)
    }

    private fun guardarResultadosEnFirestore(resultados: List<ResultadoAnalisis>) {
        val auth = Firebase.auth
        val email = auth.currentUser?.email

        if (email != null) {
            val db = FirebaseFirestore.getInstance()

            // Puedes cambiar "pacientes" por el nombre que desees para la colección de pacientes
            val pacientesCollection = db.collection("pacientes").document(email)
            val resultadosCollection = pacientesCollection.collection("resultadosAnalisis")

            for (resultado in resultados) {
                // Agregar cada resultado como un nuevo documento
                resultadosCollection.add(resultado)
                    .addOnSuccessListener { documentReference ->
                        // Puedes imprimir el ID del documento si lo necesitas
                        println("ResultadoAnalisis agregado con ID: ${documentReference.id}")
                    }
                    .addOnFailureListener { e ->
                        // Manejar errores si la operación de escritura falla
                        println("Error al agregar resultado de análisis: $e")
                    }
            }
        } else {
            // Manejar el caso en el que el usuario actual no tenga un correo electrónico válido
            println("Usuario no autenticado o correo electrónico no válido.")
        }
    }

}
