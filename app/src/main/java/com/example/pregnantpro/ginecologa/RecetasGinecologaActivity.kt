package com.example.pregnantpro.ginecologa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.example.pregnantpro.R
import com.example.pregnantpro.modeloReceta.Receta
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore

class RecetasGinecologaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recetas_ginecologa)

        // Llamada de ejemplo para guardar recetas del ginecólogo
        val recetasGinecologo = listOf(
            Receta(
                titulo = "Examen de Papanicolaou",
                descripcion = "Realizar un examen de Papanicolaou para evaluar las células cervicales."
            ),
            Receta(
                titulo = "Ecografía Obstétrica",
                descripcion = "Realizar una ecografía obstétrica para monitorear el desarrollo fetal."
            ),
            Receta(titulo = "Antibiótico: Amoxicilina 500 mg", descripcion = "Tomar una tableta cada 8 horas después de las comidas."),
            Receta(
                titulo = " Analgésico: Paracetamol 500 mg",
                descripcion = "Tomar una tableta cada 6 horas según sea necesario para el dolor o fiebre."
            )
        )

        // Llamada para guardar las recetas en Firestore
        guardarRecetasGinecologoEnFirestore(recetasGinecologo)

        // Crear un adaptador y establecerlo en tu ListView
        val adapter = RecetasGinecologaAdapter(this, recetasGinecologo)
        findViewById<ListView>(R.id.listViewRecetasGinecologo).adapter = adapter
    }

    private fun guardarRecetasGinecologoEnFirestore(recetasGinecologo: List<Receta>) {
        val auth = Firebase.auth
        val email = auth.currentUser?.email

        if (email != null) {
            val db = FirebaseFirestore.getInstance()

            // Cambiar "ginecologos" por el nombre que desees para la colección de ginecólogos
            val ginecologosCollection = db.collection("ginecologos").document(email)
            val recetasGinecologoCollection = ginecologosCollection.collection("recetasGinecologo")

            for (recetaGinecologo in recetasGinecologo) {
                // Agregar cada receta como un nuevo documento
                recetasGinecologoCollection.add(recetaGinecologo)
                    .addOnSuccessListener { documentReference ->
                        // Puedes imprimir el ID del documento si lo necesitas
                        println("RecetaGinecologo agregada con ID: ${documentReference.id}")
                    }
                    .addOnFailureListener { e ->
                        // Manejar errores si la operación de escritura falla
                        println("Error al agregar receta del ginecólogo: $e")
                    }
            }
        } else {
            // Manejar el caso en el que el usuario actual no tenga un correo electrónico válido
            println("Usuario no autenticado o correo electrónico no válido.")
        }
    }
}