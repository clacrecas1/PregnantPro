package com.example.pregnantpro.paciente

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pregnantpro.R
import com.example.pregnantpro.modeloResultados.ResultadoInfeccion
import com.example.pregnantpro.modeloResultados.ResultadoInfeccionAdapter
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore

class ResultadosInfeccionActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultados_infeccion)

        val resultadosInfeccion = obtenerResultadosInfeccion()

        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewResultadosInfeccion)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = ResultadoInfeccionAdapter(this, resultadosInfeccion)
        recyclerView.adapter = adapter

        guardarResultadosEnFirestore(resultadosInfeccion)


    }

    private fun obtenerResultadosInfeccion(): List<ResultadoInfeccion> {
        // Implementa la lógica para obtener la lista de resultados de infección
        // Puedes retornar una lista de objetos ResultadoInfeccion aquí
        return listOf(
            ResultadoInfeccion("Infección 1", "Resultados positivos indican la presencia de la infección Tipo A. Se recomienda tratamiento inmediato y seguimiento médico.", "01/01/2023", "Tipo A"),
            ResultadoInfeccion("Infección 2", "Niveles elevados de Tipo B detectados. Consulte a su médico para obtener asesoramiento sobre el tratamiento adecuado.", "02/01/2023", "Tipo B")

        )
    }

    private fun guardarResultadosEnFirestore(resultados: List<ResultadoInfeccion>) {
        val auth = Firebase.auth
        val email = auth.currentUser?.email

        if (email != null) {
            val db = FirebaseFirestore.getInstance()

            // Puedes cambiar "pacientes" por el nombre que desees para la colección de pacientes
            val pacientesCollection = db.collection("pacientes").document(email)
            val resultadosCollection = pacientesCollection.collection("resultadosInfeccion")

            for (resultado in resultados) {
                // Agregar cada resultado como un nuevo documento
                resultadosCollection.add(resultado)
                    .addOnSuccessListener { documentReference ->
                        // Puedes imprimir el ID del documento si lo necesitas
                        println("ResultadoInfeccion agregado con ID: ${documentReference.id}")
                    }
                    .addOnFailureListener { e ->
                        // Manejar errores si la operación de escritura falla
                        println("Error al agregar resultado de infeccion: $e")
                    }
            }
        } else {
            // Manejar el caso en el que el usuario actual no tenga un correo electrónico válido
            println("Usuario no autenticado o correo electrónico no válido.")
        }
    }


}