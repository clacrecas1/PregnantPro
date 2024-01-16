package com.example.pregnantpro.paciente

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import com.example.pregnantpro.R
import com.example.pregnantpro.modeloCita.Cita
import com.example.pregnantpro.modeloCita.TipoCita
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import java.util.Calendar

class PlanificacionCitasPacienteActivity : AppCompatActivity() {

    private val tiposCitas = listOf(
        TipoCita("Revisión", "Cita de revisión médica general"),
        TipoCita("Analíticas", "Realización de análisis de laboratorio"),
        TipoCita("Ecografía", "Ecografía para el seguimiento del embarazo"),
        TipoCita("Control Prenatal", "Control médico durante el embarazo"),
        TipoCita("Consulta Postparto", "Consulta médica después del parto")
        // Puedes agregar más tipos de cita según sea necesario
    )

    private lateinit var tiposAdapter: ArrayAdapter<TipoCita>
    private lateinit var etFecha: EditText
    private lateinit var etHora: EditText
    private lateinit var etiquetaInfo: TextView
    private lateinit var btnGuardarCita: Button
    private lateinit var btnVerMisCitas: Button

    private var selectedYear = 0
    private var selectedMonth = 0
    private var selectedDay = 0

    private val historialEcografias = mutableListOf<Cita>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_planificacion_citas_paciente)

        // Inicializar Firebase Auth
        val auth = Firebase.auth

        tiposAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, tiposCitas)
        tiposAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        val spinnerTipo = findViewById<Spinner>(R.id.spinnerTipo)
        spinnerTipo.adapter = tiposAdapter

        etFecha = findViewById(R.id.etFecha)
        etHora = findViewById(R.id.etHora)
        etiquetaInfo = findViewById(R.id.etiqueta_info)
        btnGuardarCita = findViewById(R.id.btnGuardarCita)
        btnVerMisCitas = findViewById(R.id.btnVerMisCitas)

        etFecha.setOnClickListener {
            mostrarSeleccionarFechaDialog()
        }

        btnGuardarCita.setOnClickListener {
            val tipoCita = tiposAdapter.getItem(spinnerTipo.selectedItemPosition) ?: TipoCita("", "")
            val fecha = "$selectedDay/${selectedMonth + 1}/$selectedYear" // Formato: dd/MM/yyyy
            val hora = etHora.text.toString()

            val nuevaCita = Cita(tipoCita.nombre, fecha, hora)

            // Agregar a historial solo si es una cita de ecografía
            if (tipoCita.nombre == "Ecografía") {
                historialEcografias.add(nuevaCita)
            }


            // Guardar cita en Firebase
            val email = auth.currentUser!!.email.toString()
            val citasCollection = FirebaseFirestore.getInstance().collection("pacientes")
                .document(email).collection("citas")

            citasCollection.add(nuevaCita).addOnSuccessListener {
                etiquetaInfo.text = "Cita guardada"
                btnVerMisCitas.visibility = View.VISIBLE
                btnGuardarCita.visibility = View.GONE
                btnVerMisCitas.setOnClickListener {
                    // Navegar a la actividad para ver citas
                    val intent = Intent(this, VerCitasActivity::class.java)
                    startActivity(intent)
                }
            }
        }

        btnVerMisCitas.setOnClickListener {
            val intent = Intent(this, VerCitasActivity::class.java)
            startActivity(intent)
        }
    }

    private fun mostrarSeleccionarFechaDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, year, month, dayOfMonth ->
                selectedYear = year
                selectedMonth = month
                selectedDay = dayOfMonth

                // Actualizar el campo de texto de la fecha
                etFecha.setText("$selectedDay/${selectedMonth + 1}/$selectedYear")
            },
            year,
            month,
            day
        )

        datePickerDialog.show()
    }
}