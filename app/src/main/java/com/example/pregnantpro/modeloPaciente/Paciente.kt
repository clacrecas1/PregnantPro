package com.example.pregnantpro.modeloPaciente

import com.example.pregnantpro.modeloCita.Cita
import java.io.Serializable

class Paciente (val id: String,val nombre: String, val apellido: String,val citas: List<Cita> = mutableListOf()
): Serializable {
    constructor() : this("", "", "") {
        // Puedes proporcionar valores predeterminados si es necesario
    }
}