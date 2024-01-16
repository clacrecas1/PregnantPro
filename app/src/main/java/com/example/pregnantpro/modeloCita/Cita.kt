package com.example.pregnantpro.modeloCita

import java.io.Serializable

data class Cita(
    val tipo: String,
    val fecha: String,
    val hora: String
):Serializable
