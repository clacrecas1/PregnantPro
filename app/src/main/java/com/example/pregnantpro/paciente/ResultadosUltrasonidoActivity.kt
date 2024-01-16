package com.example.pregnantpro.paciente

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pregnantpro.R
import com.example.pregnantpro.modeloResultados.ResultadoSangre
import com.example.pregnantpro.modeloResultados.ResultadoUltrasonido
import com.example.pregnantpro.modeloResultados.ResultadoUltrasonidoAdapter
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore

class ResultadosUltrasonidoActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultados_ultrasonido)


        val resultadosUltrasonido = listOf(
            ResultadoUltrasonido("Ultrasonido 1", "Se realizó un ultrasonido para evaluar el desarrollo fetal y la salud del bebé. Los resultados muestran un crecimiento saludable y un estado positivo.", "01/01/2023", R.mipmap.ic_ultrasonido),
            ResultadoUltrasonido("Ultrasonido 2", "Se realizó un ultrasonido para evaluar el desarrollo fetal y la salud del bebé. Los resultados muestran un crecimiento saludable y un estado positivo.", "01/09/2023", R.mipmap.ic_ultrasonido),


            )

        guardarResultadosEnFirestore(resultadosUltrasonido)

        // Configurar RecyclerView
        val adapter = ResultadoUltrasonidoAdapter(resultadosUltrasonido)
        val rvResultadoUltrasonido: RecyclerView = findViewById(R.id.rvResultadoUltrasonido)
        rvResultadoUltrasonido.layoutManager = LinearLayoutManager(this)
        rvResultadoUltrasonido.adapter = adapter
    }


    private fun guardarResultadosEnFirestore(resultados: List<ResultadoUltrasonido>) {
        val auth = Firebase.auth
        val email = auth.currentUser?.email

        if (email != null) {
            val db = FirebaseFirestore.getInstance()

            // Puedes cambiar "pacientes" por el nombre que desees para la colección de pacientes
            val pacientesCollection = db.collection("pacientes").document(email)
            val resultadosCollection = pacientesCollection.collection("resultadosUltrasonido")

            for (resultado in resultados) {
                // Agregar cada resultado como un nuevo documento
                resultadosCollection.add(resultado)
                    .addOnSuccessListener { documentReference ->
                        // Puedes imprimir el ID del documento si lo necesitas
                        println("ResultadosUltrasonido agregado con ID: ${documentReference.id}")
                    }
                    .addOnFailureListener { e ->
                        // Manejar errores si la operación de escritura falla
                        println("Error al agregar resultado de ultrasonido: $e")
                    }
            }
        } else {
            // Manejar el caso en el que el usuario actual no tenga un correo electrónico válido
            println("Usuario no autenticado o correo electrónico no válido.")
        }
    }

}