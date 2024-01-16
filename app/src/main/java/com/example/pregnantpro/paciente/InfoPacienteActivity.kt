package com.example.pregnantpro.paciente

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.example.pregnantpro.R
import com.google.firebase.Firebase
import com.google.firebase.Timestamp
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat



class InfoPacienteActivity : AppCompatActivity() {
    private lateinit var nombre: EditText
    private lateinit var apellido: EditText
    private lateinit var email: EditText
    private lateinit var telefono: EditText
    private lateinit var fechaNacimiento: EditText
    private lateinit var numeroSeguridadSocial: EditText
    private lateinit var semanaActual: EditText
    private lateinit var fechaPartoPrevista: EditText

    private lateinit var btnAceptar: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_paciente)
        nombre = findViewById(R.id.editTextText)
        apellido = findViewById(R.id.editTextText2)

        telefono = findViewById(R.id.editTextPhone)
        fechaNacimiento = findViewById(R.id.editTextDate)
        numeroSeguridadSocial = findViewById(R.id.editTextNumber)
        semanaActual = findViewById(R.id.editTextNumber2)
        fechaPartoPrevista = findViewById(R.id.editTextDate2)

        btnAceptar = findViewById(R.id.btnAceptar)


        //Acceder y guardar la informacion del email del usuario
        email = findViewById(R.id.editTextTextEmailAddress)
        val user = Firebase.auth.currentUser
        user?.let { user ->
            email.setText(user.email)
            email.isEnabled = false
        }

        /*btnAceptar.setOnClickListener {
            startActivity(Intent(this, HomePacienteActivity::class.java))
        }*/


        mostrarInfoPaciente()


    }

    private fun mostrarInfoPaciente() {
        val myCol = FirebaseFirestore.getInstance().collection("pacientes")
        val userEmail = email.text.toString()
        val myDoc = myCol.document(userEmail)

        myDoc.get().addOnSuccessListener {
            var nombre2 =
                if (it.get("nombre").toString() != "null") it.get("nombre").toString() else ""
            var apellido2 =
                if (it.get("apellido").toString() != "null") it.get("apellido").toString() else ""
            var telefono2 =
                if (it.get("telefono").toString() != "null") it.get("telefono").toString() else ""
            var fechaNacimiento2 = it.getTimestamp("fechaNacimiento")
            var numeroSeguridadSocial2 =
                if (it.get("numeroSeguridadSocial")
                        .toString() != "null"
                ) it.get("numeroSeguridadSocial").toString() else ""
            var semanaActual2 =
                if (it.get("semanaActual").toString() != "null") it.get("semanaActual")
                    .toString() else ""
            var fechaPartoPrevista2 = it.getTimestamp("fechaPartoPrevista")

            nombre.setText(nombre2)
            apellido.setText(apellido2)
            telefono.setText(telefono2)
            numeroSeguridadSocial.setText(numeroSeguridadSocial2)
            semanaActual.setText(semanaActual2)


            if (fechaNacimiento2 != null) {
                var fecha1 = fechaNacimiento2.toDate()
                var formato = SimpleDateFormat("dd/MM/yyyy")
                var fechastr1 = formato.format(fecha1)
                fechaNacimiento.setText(fechastr1)
            } else {
                fechaNacimiento.setText("Fecha no disponible")
            }

            if (fechaPartoPrevista2 != null) {
                var fecha2 = fechaPartoPrevista2?.toDate()
                var formato = SimpleDateFormat("dd/MM/yyyy")
                var fechastr2 = formato.format(fecha2)
                fechaPartoPrevista.setText(fechastr2)
            } else {
                fechaPartoPrevista.setText("Fecha no disponible")
            }
        }
    }

    fun onClickAceptar(v: View) {
        val e1 = Regex("^(\\+34|0034|34)?[67]\\d{8}\$")
        val ok1 = e1.matches(telefono.text.toString())
        if (!ok1) {
            // Error
            Log.e("ERROR", "El télefono introducido no es correcto")
            telefono.error = "Teléfono incorrecto"
        }

        //comprobar expresion regular fecha:dd/MM/aaaa

        val f1 = "^\\d{2}/\\d{2}/\\d{4}\$".toRegex()
        var ok2 = f1.matches(fechaNacimiento.text.toString())
        var ok3 = f1.matches(fechaPartoPrevista.text.toString())
        ok2 = true
        if (!ok2) {
            // Error
            Log.e("ERROR", "El formato de la fecha no es correcto")
            fechaNacimiento.error = "Fecha incorrecto"
        }
        if (!ok3) {
            // Error
            Log.e("ERROR", "El formato de la fecha no es correcto")
            fechaPartoPrevista.error = "Fecha incorrecto"
        }


        if (ok1 && ok2 && ok3) {
            almacenarInfoBD()
        }


    }

    private fun almacenarInfoBD() {
        val myCol = FirebaseFirestore.getInstance().collection("pacientes")
        var formato = SimpleDateFormat("dd/MM/yyyy")
        var fechanac = formato.parse(fechaNacimiento.text.toString())//Date
        var timestamp1 = Timestamp(fechanac)
        var fechapart = formato.parse(fechaPartoPrevista.text.toString())
        var timestamp2 = Timestamp(fechapart)

        var nuevaInfo = mapOf(
            "nombre" to nombre.text.toString(),
            "apellido" to apellido.text.toString(),
            "telefono" to telefono.text.toString(),
            "numeroSeguridadSocial" to numeroSeguridadSocial.text.toString(),
            "semanaActual" to semanaActual.text.toString(),
            "fechaNacimiento" to timestamp1,
            "fechaPartoPrevista" to timestamp2

        )
        val userEmail = email.text.toString()
        myCol.document(userEmail).set(nuevaInfo)
            .addOnSuccessListener {
                showAlert("Datos actualizados")
            }
            .addOnFailureListener { e ->
                showAlert("Datos actualizados")
            }



    }


    private fun showAlert(text: String) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder
            .setTitle("¡Listo!")
            .setMessage(text)
            .setPositiveButton("ACEPTAR", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }






    fun onClickNumeroTelefono(v: View){
        val e1 = Regex("^(\\+34|0034|34)?[67]\\d{8}\$")
        if(!e1.matches(telefono.text.toString())){
            // Error
            Log.e("ERROR", "El télefono introducido no es correcto")
            telefono.error = "Teléfono incorrecto"
        }

    }

    fun onClikNumeroSeguridadSocial(v:View){
        val e2 = Regex("^\\d{12}$")
        if(!e2.matches(numeroSeguridadSocial.text.toString())){
            Log.e("ERROR", "El número de la seguridad social no es correcto debe tener 12 dígitos")
            numeroSeguridadSocial.error="Número Seguridad Social incorrecto"
        }
    }

    fun onClickFechaNacimiento(v:View){
        val e3 = Regex("^\\d{2}/\\d{2}/\\d{4}$")
        if(e3.matches(fechaNacimiento.text.toString())){
            try {
                val inputFormat = SimpleDateFormat("dd/MM/yyyy")
                val outputFormat = SimpleDateFormat("dd/MM/yyyy")
                val date = inputFormat.parse(fechaNacimiento.text.toString())
                val formattedDate = outputFormat.format(date)

                // Ahora 'formattedDate' contiene la fecha de nacimiento en el formato deseado
                // Puedes usar 'formattedDate' según tus necesidades, por ejemplo, actualizar un objeto de modelo o realizar otra acción.

                // En este ejemplo, actualizamos el texto del EditText con la fecha formateada
                fechaNacimiento.setText(formattedDate)
            } catch (e: Exception) {
                Log.e("ERROR", "Error al formatear la fecha: ${e.message}")
            }
        } else {
            // La fecha de nacimiento no sigue el formato correcto, puedes mostrar un mensaje o realizar otra acción si es necesario.
        }
    }
    fun onClickFechaParto(v:View){
        val e3 = Regex("^\\d{2}/\\d{2}/\\d{4}$")
        if(e3.matches(fechaPartoPrevista.text.toString())){
            try {
                val inputFormat = SimpleDateFormat("dd/MM/yyyy")
                val outputFormat = SimpleDateFormat("dd/MM/yyyy")
                val date = inputFormat.parse(fechaPartoPrevista.text.toString())
                val formattedDate = outputFormat.format(date)

                // Ahora 'formattedDate' contiene la fecha de nacimiento en el formato deseado
                // Puedes usar 'formattedDate' según tus necesidades, por ejemplo, actualizar un objeto de modelo o realizar otra acción.

                // En este ejemplo, actualizamos el texto del EditText con la fecha formateada
                fechaPartoPrevista.setText(formattedDate)
            } catch (e: Exception) {
                Log.e("ERROR", "Error al formatear la fecha: ${e.message}")
            }
        } else {
            // La fecha de nacimiento no sigue el formato correcto, puedes mostrar un mensaje o realizar otra acción si es necesario.
        }
    }


}
