package com.example.app_cursos.Modelo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable//permite la clase con kotlin serialization
data class DataInscripcion(
    @SerialName("curso_id") val cursiId: Int = 0,
    @SerialName("matricula") val matricula: String = "",
    @SerialName("fecha_registro") val fechaRegistro: String = "",
    @SerialName("exito") val exito: Boolean = false,
    @SerialName("mensaje") val mensaje: String = ""
)