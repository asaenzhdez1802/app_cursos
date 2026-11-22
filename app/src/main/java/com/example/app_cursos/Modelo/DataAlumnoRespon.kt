package com.example.app_cursos.Modelo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DataAlumnoRespon(
    @SerialName("curso_id") val cursoId: Int=0,
    @SerialName("matricula")val matricula: String="",
    @SerialName("correo_institucional") val correoInstitucional: String?=null,
    @SerialName("fecha_registro")val fechaRegistro: String="",
    @SerialName("exito")val exito: Boolean=false,
    @SerialName("mensaje")val mensaje: String=""
)
