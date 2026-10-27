package com.example.app_cursos.Modelo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class dataRegistroCurso(
    @SerialName("id")
    val id: Int,

    @SerialName("carrera_id")
    val carreraId: Int,

    @SerialName("nombre")
    val nombre: String,

    @SerialName("cupo_total")
    val cupoTotal: Int,

    @SerialName("url_imagen")
    val urlImagen: String? = null,

    @SerialName("inscritos")
    val inscritos: Int
)