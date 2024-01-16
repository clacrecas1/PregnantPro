package com.example.pregnantpro.ginecologa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import com.example.pregnantpro.R
import com.example.pregnantpro.modeloPaciente.Paciente
import com.example.pregnantpro.paciente.ResultadosAnalisisActivity
import com.example.pregnantpro.paciente.ResultadosInfeccionActivity
import com.example.pregnantpro.paciente.ResultadosRenalActivity
import com.example.pregnantpro.paciente.ResultadosSangreActivity
import com.example.pregnantpro.paciente.ResultadosUltrasonidoActivity
import com.google.firebase.firestore.FirebaseFirestore

class ResultadosGinecologaActivity : BaseGinecologa() {

        private lateinit var spinnerPacientes: Spinner
        private lateinit var spinnerResultados: Spinner
        private lateinit var btnVerResultados: Button

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_resultados_ginecologa)
            setSupportActionBar(findViewById(R.id.my_toolbar))


            spinnerPacientes = findViewById(R.id.spinnerPacientes)
            spinnerResultados = findViewById(R.id.spinnerResultados)
            btnVerResultados = findViewById(R.id.btnVerResultados)

            btnVerResultados.setOnClickListener {
                val pacienteSeleccionado = spinnerPacientes.selectedItem as String
                val resultadoSeleccionado = spinnerResultados.selectedItem as String

                abrirActividadCorrespondiente(pacienteSeleccionado, resultadoSeleccionado)
            }

            muestraPacientes()
            muestraResultados()
        }

        private fun muestraPacientes() {
            val pacientes = mutableListOf<Paciente>()
            FirebaseFirestore.getInstance().collection("pacientes").get()
                .addOnSuccessListener { documentos ->
                    for (doc in documentos) {
                        val id = doc.id
                        val nombre = doc.getString("nombre") ?: ""
                        val apellido = doc.getString("apellido") ?: ""

                        pacientes.add(Paciente(id, nombre, apellido))
                    }

                    val nombresApellidos = pacientes.map { "${it.nombre} ${it.apellido}" }

                    val adapter =
                        ArrayAdapter(this, android.R.layout.simple_spinner_item, nombresApellidos)
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spinnerPacientes.adapter = adapter

                    spinnerPacientes.onItemSelectedListener =
                        object : AdapterView.OnItemSelectedListener {
                            override fun onItemSelected(
                                parentView: AdapterView<*>?,
                                selectedItemView: View?,
                                position: Int,
                                id: Long
                            ) {
                                // No es necesario hacer nada aquí, ya que solo necesitamos la selección cuando se presiona el botón
                            }

                            override fun onNothingSelected(parentView: AdapterView<*>?) {
                                // No se necesita acción específica cuando no hay nada seleccionado
                            }
                        }
                }
        }

        private fun muestraResultados() {
            val resultados = listOf("Analisis", "AnalisisSangre", "Infeccion", "Renal", "Ultrasonido")

            val adapterResultados =
                ArrayAdapter(this, android.R.layout.simple_spinner_item, resultados)
            adapterResultados.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerResultados.adapter = adapterResultados

            spinnerResultados.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parentView: AdapterView<*>?,
                        selectedItemView: View?,
                        position: Int,
                        id: Long
                    ) {
                        // No es necesario hacer nada aquí, ya que solo necesitamos la selección cuando se presiona el botón
                    }

                    override fun onNothingSelected(parentView: AdapterView<*>?) {
                        // No se necesita acción específica cuando no hay nada seleccionado
                    }
                }
        }

        private fun abrirActividadCorrespondiente(paciente: String, resultado: String) {
            when (resultado) {
                "Analisis" -> abrirActividad(ResultadosAnalisisActivity::class.java, paciente, "resultadosAnalisis")
                "AnalisisSangre" -> abrirActividad(ResultadosSangreActivity::class.java, paciente, "resultadosAnalisisSangre")
                "Infeccion" -> abrirActividad(ResultadosInfeccionActivity::class.java, paciente, "resultadosInfeccion")
                "Renal" -> abrirActividad(ResultadosRenalActivity::class.java, paciente, "resultadosRenal")
                "Ultrasonido" -> abrirActividad(ResultadosUltrasonidoActivity::class.java, paciente, "resultadosUltrasonido")
            }
        }

        private fun abrirActividad(
            actividad: Class<*>,
            paciente: String,
            tipoResultado: String
        ) {
            val intent = Intent(this, actividad)
            intent.putExtra("paciente", paciente)
            intent.putExtra("tipoResultado", tipoResultado)
            startActivity(intent)
        }
    }