package com.example.app_cursos.Modelo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class dataCursos(
    @SerialName("id")
    val id: Int,

    @SerialName("carrera_id")
    val carreraId: Int,

    @SerialName("nombre")
    val nombre: String,

    @SerialName("salon")
    val salon: String,

    @SerialName("url_imagen")
    val urlImagen: String? = null
)