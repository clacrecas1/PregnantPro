package com.example.pregnantpro.paciente

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pregnantpro.R
import com.example.pregnantpro.modeloResultados.ResultadoInfeccion
import com.example.pregnantpro.modeloResultados.ResultadoRenal
import com.example.pregnantpro.modeloResultados.ResultadoRenalAdapter
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore

class ResultadosRenalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultados_renal)


        val resultadosRenal = obtenerResultadosRenalEjemplo()

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ResultadoRenalAdapter(resultadosRenal)

        guardarResultadosEnFirestore(resultadosRenal)
    }

    private fun obtenerResultadosRenalEjemplo(): List<ResultadoRenal> {
        return listOf(
            ResultadoRenal("Creatinina", "La creatinina es un marcador común de la función renal. Niveles altos pueden indicar problemas en los riñones.", "01/01/2023"),
            ResultadoRenal("Nitrógeno ureico", "El nitrógeno ureico en sangre se evalúa para medir la función renal. Cambios pueden indicar problemas renales.", "02/01/2023"),
            ResultadoRenal("Filtrado glomerular", "El filtrado glomerular mide la eficiencia de los riñones. Un resultado bajo puede indicar problemas de filtración.", "03/01/2023"),
            ResultadoRenal("Ácido úrico", "El ácido úrico se evalúa para detectar problemas en los riñones y puede estar relacionado con la gota.", "04/01/2023")
        )
    }

    private fun guardarResultadosEnFirestore(resultados: List<ResultadoRenal>) {
        val auth = Firebase.auth
        val email = auth.currentUser?.email

        if (email != null) {
            val db = FirebaseFirestore.getInstance()

            // Puedes cambiar "pacientes" por el nombre que desees para la colección de pacientes
            val pacientesCollection = db.collection("pacientes").document(email)
            val resultadosCollection = pacientesCollection.collection("resultadosRenal")

            for (resultado in resultados) {
                // Agregar cada resultado como un nuevo documento
                resultadosCollection.add(resultado)
                    .addOnSuccessListener { documentReference ->
                        // Puedes imprimir el ID del documento si lo necesitas
                        println("ResultadoRenal agregado con ID: ${documentReference.id}")
                    }
                    .addOnFailureListener { e ->
                        // Manejar errores si la operación de escritura falla
                        println("Error al agregar resultado de renal: $e")
                    }
            }
        } else {
            // Manejar el caso en el que el usuario actual no tenga un correo electrónico válido
            println("Usuario no autenticado o correo electrónico no válido.")
        }
    }



}