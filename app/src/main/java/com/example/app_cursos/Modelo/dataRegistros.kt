package com.example.app_cursos.Modelo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class dataRegistros(
    @SerialName("total_inscripciones")
    val totalInscripciones: Int,

    @SerialName("cursos")
    val cursos: List<dataRegistroCurso>
)