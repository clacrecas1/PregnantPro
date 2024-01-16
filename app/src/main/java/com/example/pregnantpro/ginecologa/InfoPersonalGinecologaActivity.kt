package com.example.pregnantpro.ginecologa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.pregnantpro.R
import com.google.firebase.Firebase
import com.google.firebase.Timestamp
import com.google.firebase.auth.auth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat


class InfoPersonalGinecologaActivity : BaseGinecologa() {

    private lateinit var telefono: EditText
    private lateinit var email: EditText
    private lateinit var nombre: EditText
    private lateinit var apellido: EditText
    private lateinit var fechanac: EditText
    private lateinit var consulta: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_personal_ginecologa)
        setSupportActionBar(findViewById(R.id.my_toolbar))

        telefono = findViewById(R.id.editTextPhone)
        email = findViewById(R.id.editTextTextEmailAddress)
        consulta = findViewById(R.id.editTextText3)
        nombre = findViewById(R.id.editTextText)
        apellido = findViewById(R.id.editTextText2)
        fechanac = findViewById(R.id.editTextDate)

        val user = Firebase.auth.currentUser
        user?.let { user ->
            email.setText(user.email)
            email.isEnabled = false
        }

        mostrarInfoPaciente()


    }

    private fun mostrarInfoPaciente(){
        val myCol = FirebaseFirestore.getInstance().collection("ginecologos")

        //acceder a la coleccion para descargar datos de la bd
        val myDoc = myCol.document(email.text.toString())
        myDoc.get().addOnSuccessListener {
            var nombre2 =
                if (it.get("nombre").toString() != "null") it.get("nombre").toString() else ""
            var apellido2 =
                if (it.get("apellidos").toString() != "null") it.get("apellidos").toString() else ""
            var telefono2 =
                if (it.get("telefono").toString() != "null") it.get("telefono").toString() else ""
            var consulta2 =
                if (it.get("consulta").toString() != "null") it.get("consulta").toString() else ""
            var fechanac2 = it.getTimestamp("fecha de nacimiento")

            nombre.setText(nombre2)
            apellido.setText(apellido2)
            telefono.setText(telefono2)
            consulta.setText(consulta2)

            if(fechanac2!=null) {
                //convertir timestamp a string
                var fecha = fechanac2?.toDate()
                var formato = SimpleDateFormat("dd/MM/yyyy")
                var fechastr = formato.format(fecha)
                fechanac.setText(fechastr)
            }

        }
    }

    fun onClickUpdate(v: View) {
        val e1 = Regex("^(\\+34|0034|34)?[67]\\d{8}\$")
        val ok1 = e1.matches(telefono.text.toString())
        if (!ok1) {
            // Error
            Log.e("ERROR", "El télefono introducido no es correcto")
            telefono.error = "Teléfono incorrecto"
        }

        //comprobar expresion regular de la fecha usando formato dd/mm/yyyy
        val e2 = "^(0[1-9]|[1-2][0-9]|3[0-1])/(0[1-9]|1[0-2])/(19|20)\\d\\d$".toRegex()
        var ok2 = e2.matches(fechanac.text.toString())
        ok2=true

        if (!ok2) {
            // Error
            Log.e("ERROR", "La fecha no es correcta")
            fechanac.error = "Formato de fecha incorrecto"
        }

        if (ok1 && ok2) {

            almacenarInfoBD()
        }
    }

    private fun almacenarInfoBD() {
        //coleccion: "pacientes"
        val myCol = FirebaseFirestore.getInstance().collection("ginecologos")

        //id del documento: email del paciente

        //convertir de string a Timestamp:
        var formato = SimpleDateFormat("dd/mm/yyyy")
        var fecha = formato.parse(fechanac.text.toString())
        var timestamp = Timestamp(fecha)

        var nuevaInfo = mapOf(

            "nombre" to nombre.text.toString(),
            "apellidos" to apellido.text.toString(),
            "telefono" to telefono.text.toString(),
            "fecha de nacimiento" to timestamp,
        )


        //guardarlo to en la base de datos
        myCol.document(email.text.toString()).set(nuevaInfo).addOnSuccessListener {
            showAlert("Datos actualizados")
        }
            .addOnFailureListener {
                showAlert("Error")

            }

    }
    private fun showAlert(text: String){
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder
            .setTitle("¡Listo!")
            .setMessage(text)
            .setPositiveButton("ACEPTAR", null)

        val dialogo: AlertDialog = builder.create()
        dialogo.show()

    }

}