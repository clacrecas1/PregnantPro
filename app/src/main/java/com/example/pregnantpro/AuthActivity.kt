package com.example.pregnantpro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.example.pregnantpro.ginecologa.HomeGinecologaActivity

import com.example.pregnantpro.paciente.HomePacienteActivity
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth

import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore


class AuthActivity : AppCompatActivity() {


    //Nos creamos las variables que utilizaremos
    private lateinit var email: EditText
    private lateinit var passwd: EditText
    private lateinit var btnRegistro: Button
    private lateinit var btnAcceder: Button

    //Cremoas la variable que nos servirña para comunicarnos con Firebase
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        //Crear intent para abrir directamente el authactivity
        val i = Intent(this, AuthActivity::class.java)
        startActivity(i)

        btnRegistro = findViewById(R.id.btnRegistro)
        btnAcceder = findViewById(R.id.btnAcceder)
        email = findViewById(R.id.emailEditText)
        passwd = findViewById(R.id.passwdEditText)
        auth = Firebase.auth

        //Funcion donde haremos la logica de la autenticacion
        setup()

    }

    fun setup() {
        // Aquí pondremos la lógica de los botones de autenticación
        btnRegistro.setOnClickListener {
            // Comprobar que el correo electrónico y la contraseña no estén vacíos
            if (email.text.isNotBlank() && passwd.text.isNotBlank()) {
                // sí podemos registrar al usuario
                auth.createUserWithEmailAndPassword(
                    email.text.toString(),
                    passwd.text.toString()
                ).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // El registro se ha hecho de forma correcta
                        val myCol = FirebaseFirestore.getInstance().collection("pacientes")
                        myCol.document(email.text.toString()).set(mapOf("nombre" to ""))
                        Log.i("INFO", "El usuario se ha registrado correctamente")
                        email.text.clear()
                        passwd.text.clear()
                    } else {
                        // Si ha habido algún fallo que aparezca un Toast
                        //Toast.makeText(this,"Fallo en el registro",Toast.LENGTH_SHORT).show()
                        showAlert("Ha habido un fallo en el registro")
                    }
                }
            }
        }
        btnAcceder.setOnClickListener {
            btnAcceder.setOnClickListener {
                // Comprobar que el correo electrónico y la contraseña no estén vacíos
                if (email.text.isNotBlank() && passwd.text.isNotBlank()) {
                    // sí podemos acceder a la aplicacion
                    auth.signInWithEmailAndPassword(
                        email.text.toString(),
                        passwd.text.toString()
                    ).addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            // El registro se ha hecho de forma correcta
                            Log.i("INFO", "El usuario se se ha logueado correctamente")
                            showHome(email.text.toString())
                            email.text.clear()
                            passwd.text.clear()
                        } else {
                            // Si ha habido algún fallo que aparezca un Toast
                            //Toast.makeText(this,"Fallo en el registro",Toast.LENGTH_SHORT).show()
                            showAlert("Se ha producido un error autenticando al usuario")
                        }
                    }
                }
            }

        }


    }

    private fun showAlert(text: String) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
            .setMessage(text)
            .setPositiveButton("ACEPTAR", null)

        val dialogo: AlertDialog = builder.create()
        dialogo.show()
    }


    //inicializamos los email para acceder segun el tipo de usuario
    private fun showHome(email: String) {
        val myCol = FirebaseFirestore.getInstance().collection("pacientes")
        myCol.document(email).get().addOnSuccessListener {
            if (it.exists()) {
                startActivity(Intent(this, HomePacienteActivity::class.java))
            } else {
                startActivity(Intent(this, HomeGinecologaActivity::class.java))
            }
        }


    }
}




