package com.example.pregnantpro.paciente

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pregnantpro.R
import com.example.pregnantpro.modeloResultados.ResultadoRenal
import com.example.pregnantpro.modeloResultados.ResultadoSangre
import com.example.pregnantpro.modeloResultados.ResultadoSangreAdapter
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore

class ResultadosSangreActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultados_sangre)

        val resultadosSangre = obtenerResultadosSangreEjemplo()

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ResultadoSangreAdapter(resultadosSangre)

        guardarResultadosEnFirestore(resultadosSangre)
    }

    private fun obtenerResultadosSangreEjemplo(): List<ResultadoSangre> {
        return listOf(
            ResultadoSangre("Hemoglobina", "La hemoglobina transporta oxígeno a los tejidos del cuerpo. Niveles normales son cruciales para la salud.", "01/01/2023"),
            ResultadoSangre("Glucosa", "La glucosa en sangre se mide para evaluar el riesgo de diabetes. Valores elevados pueden indicar problemas.", "02/01/2023"),
            ResultadoSangre("Colesterol", "El colesterol alto puede aumentar el riesgo de enfermedad cardíaca. Mantener niveles saludables es esencial.", "03/01/2023"),
            ResultadoSangre("Recuento de Plaquetas", "Las plaquetas ayudan en la coagulación de la sangre. Niveles bajos o altos pueden indicar problemas de salud.", "04/01/2023")
        )
    }

    private fun guardarResultadosEnFirestore(resultados: List<ResultadoSangre>) {
        val auth = Firebase.auth
        val email = auth.currentUser?.email

        if (email != null) {
            val db = FirebaseFirestore.getInstance()

            // Puedes cambiar "pacientes" por el nombre que desees para la colección de pacientes
            val pacientesCollection = db.collection("pacientes").document(email)
            val resultadosCollection = pacientesCollection.collection("resultadosAnalisisSangre")

            for (resultado in resultados) {
                // Agregar cada resultado como un nuevo documento
                resultadosCollection.add(resultado)
                    .addOnSuccessListener { documentReference ->
                        // Puedes imprimir el ID del documento si lo necesitas
                        println("ResultadosAnalisisSangre agregado con ID: ${documentReference.id}")
                    }
                    .addOnFailureListener { e ->
                        // Manejar errores si la operación de escritura falla
                        println("Error al agregar resultado de analisis sangre: $e")
                    }
            }
        } else {
            // Manejar el caso en el que el usuario actual no tenga un correo electrónico válido
            println("Usuario no autenticado o correo electrónico no válido.")
        }
    }
}

